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
    private final String numberFormat = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
    private SessionFactory sessionFactory = new Configuration()
            .configure().addAnnotatedClass(Contact.class).buildSessionFactory();

    public boolean addContact(String firstName, String lastName, String number) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(Contact.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .number(number)
                    .build());
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Contact> getAllContacts() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Contact> query = builder.createQuery(Contact.class);
            Root<Contact> contacts = query.from(Contact.class);
            query.select(contacts);
            List<Contact> list = session.createQuery(query).getResultList();
            return list;

        }
    }

    public Contact findByFullName(String firstName, String lastName) {
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
            Contact contact = findByNumber(number);
            session.beginTransaction();
            session.delete(contact);
            session.getTransaction().commit();
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean deleteByName(String firstName, String lastName) {
        try (Session session = sessionFactory.openSession()) {
            Contact contact = findByFullName(firstName, lastName);
            session.beginTransaction();
            session.delete(contact);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Contact> findByLastName(String lastName) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Contact> query = builder.createQuery(Contact.class);
            Root<Contact> contact = query.from(Contact.class);
            query.select(contact).where(builder.equal(contact.get(Contact_.LAST_NAME), lastName));
            List<Contact> list = session.createQuery(query).getResultList();
            return list;

        }
    }
    public boolean changeNumber(String firstName, String lastName, String newNumber) {
        try (Session session = sessionFactory.openSession()) {
            Contact contact = findByFullName(firstName, lastName);
            if(contact != null && newNumber.matches(numberFormat)) {
                contact.setNumber(newNumber);
                session.beginTransaction();
                deleteByName(firstName, lastName);
                session.save(contact);
                session.getTransaction().commit();
                return true;
            } else return false;
        } catch (Exception e) {
            return false;
        }
    }

}
