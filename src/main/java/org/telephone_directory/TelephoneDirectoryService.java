package org.telephone_directory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TelephoneDirectoryService {
    private static TelephoneDirectoryService instance;
    private final HashMap<String, String> NUMBERS_DIRECTORY = new HashMap<>();

    private final String numberFormat = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
    ;
    private TelephoneDirectoryDBService telephoneDirectoryService = new TelephoneDirectoryDBService();

    private TelephoneDirectoryService() {
    }

    public static TelephoneDirectoryService getInstance() {
        if (instance == null) {
            instance = new TelephoneDirectoryService();
        }
        return instance;
    }

    public void add(String firstName, String lastName, String phoneNumber) throws SQLException {
        String corrFirstName = firstName.replaceAll("\\d", "");
        String corrLastName = lastName.replaceAll("\\d", "");
        if (corrFirstName.isBlank()) {
            System.out.println("Введіть корректне ім'я");
            return;
        }
        if (corrLastName.isBlank()) {
            System.out.println("Введіть корректне прізвище");
            return;
        }

        if (!(phoneNumber.matches(numberFormat))) {
            System.out.println("Неправильний формат. Номер має починатись з +380 та містити в собі 10 цифр");
            return;
        }
        if (telephoneDirectoryService.findByName(firstName, lastName) !=null) {
            telephoneDirectoryService.addContact(corrFirstName, corrLastName, phoneNumber);
            System.out.println("Дотатковий номер контакта був доданий до Телефонного Довідника");
        }
        else {
            if (telephoneDirectoryService.addContact(corrFirstName, corrLastName, phoneNumber)) {
                System.out.println("\n Контакт був доданий до Телефонного Довідника \n");
            }
            else {
                System.out.println("Введений номер зайнятий");
            }

        }

    }

    public void searchByName(String name) throws SQLException {
        try {
            String firstName = name.substring(0, name.indexOf(" "));
            String lastName = name.substring(name.indexOf(" ") + 1, name.length());
            Contact contact = telephoneDirectoryService.findByName(firstName, lastName);
            if (contact != null) {
                System.out.println("За цим ім'ям знайдено контакт: " + contact.getFirstName()
                        + " " + contact.getLastName()
                        + " " + contact.getNumber());
            } else {
                System.out.println("Такого контакта немає в телефонній книзі");
            }
        } catch (Exception e) {
            System.out.println("Невірно набране ім'я");
        }
    }

    public void searchByNumber(String number) throws SQLException {
        Contact contact = telephoneDirectoryService.findByNumber(number);
        if (contact != null) {
            System.out.println("За цим номером знайдено контакт: " + contact.getFirstName()
                    + " " + contact.getLastName()
                    + " " + contact.getNumber());
        }
        else {
            System.out.println("Такого контакта немає в телефонній книзі");
        }

    }


    public void getAllContacts() throws SQLException {
        List<Contact> list = telephoneDirectoryService.getAllContacts();
        if (list.isEmpty()) {
            System.out.println("Контактна книга - пуста");
        } else {

            list.forEach(contact -> System.out.println(contact.getFirstName()
                    + " " + contact.getLastName()
                    + " " + contact.getNumber() + "\n ---------------------"));

        }
    }

    public void deleteContactByName(String firstName, String lastName) throws SQLException {
        if (telephoneDirectoryService.deleteByName(firstName, lastName)) {
            System.out.println("Контакт видалено успішно");
        } else {
            System.out.println("Невірно набраний контакт");
        }
    }

    public void deleteContactByNumber(String number) throws SQLException {

        if(telephoneDirectoryService.deleteByNumber(number)) {
            System.out.println("Контакт видалено успішно");
        } else {
            System.out.println("Невірно введений номер");
        }
    }

}
