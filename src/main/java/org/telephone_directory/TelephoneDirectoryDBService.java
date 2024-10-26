package org.telephone_directory;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TelephoneDirectoryDBService {
    private SessionFactory sessionFactory = new Configuration()
            .configure().addAnnotatedClass(Contact.class).buildSessionFactory();

    public boolean addContact(String firstName, String lastName, String number) {
        try (Session session = sessionFactory.openSession()) {
        try {
            session.beginTransaction();
            session.save(Contact.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .number(number)
                    .build());
            session.getTransaction().commit();
            return true;
        } catch(Exception e) {
            return false;
        }
        }
    }

    public List<Contact> getAllContacts() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Contact> query = builder.createQuery(Contact.class);
            Root<Contact> root = query.from(Contact.class);
            query.select(root);
            List<Contact> list = session.createQuery(query).getResultList();
            return list;

        }
    }
    public Contact findByName(String firstName, String lastName) {
        try (Session session = sessionFactory.openSession()) {
            List<Contact> list = getAllContacts();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getFirstName().equals(firstName)) {
                    if (list.get(i).getLastName().equals(lastName)) {
                        return list.get(i);
                    }
                }
            }
            return null;
        }
    }
    public Contact findByNumber(String number) {
        try (Session session = sessionFactory.openSession()) {

            return session.get(Contact.class, number);
        }
    }
    public boolean deleteByNumber(String number) {
        try (Session session = sessionFactory.openSession()) {
            try {
                Contact contact = findByNumber(number);
                session.beginTransaction();
                session.delete(contact);
                session.getTransaction().commit();
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
    }
    public boolean deleteByName(String firstName, String lastName) {
        try (Session session = sessionFactory.openSession()) {
            try {
                Contact contact = findByName(firstName, lastName);
                session.beginTransaction();
                session.delete(contact);
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
}
