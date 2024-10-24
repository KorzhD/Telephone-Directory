package org.telephone_directory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TelephoneDirectoryService {
    private static TelephoneDirectoryService instance;
    private final HashMap<String, String> NUMBERS_DIRECTORY = new HashMap<>();

    private final String numberFormat = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";

    private TelephoneDirectoryService() {
    }

    public static TelephoneDirectoryService getInstance() {
        if (instance == null) {
            instance = new TelephoneDirectoryService();
        }
        return instance;
    }

    public void add(String firstName, String lastName, String phoneNumber) throws SQLException {
        if (firstName.isBlank()) {
            System.out.println("Введіть корректне ім'я");
            return;
        }
        if (lastName.isBlank()) {
            System.out.println("Введіть корректне прізвище");
            return;
        }

        if (!(phoneNumber.matches(numberFormat))) {
            System.out.println("Неправильний формат. Номер має починатись з +380 та містити в собі 10 цифр");
            return;
        }
        if (NUMBERS_DIRECTORY.containsValue(phoneNumber)) {
            System.out.println("Цей номер вже зайнятий іншим контактом");
            return;
        }
        String fullName = firstName + " " + lastName;
        if (NUMBERS_DIRECTORY.containsKey(fullName)) {
            String secondNumber = fullName + " " + 2;
            NUMBERS_DIRECTORY.put(secondNumber, phoneNumber);
            System.out.println("\n Другий номер контакта був доданий до Телефонного Довідника");
        } else {
           TelephoneDirectoryDBManager.addContact(firstName, lastName, phoneNumber);
            System.out.println("\n Контакт був доданий до Телефонного Довідника \n");
        }

    }

    public void searchByName(String name) throws SQLException {
       TelephoneDirectoryDBManager.searchByName(name);
    }

    public void searchByNumber(String number) throws SQLException {
        TelephoneDirectoryDBManager.searchByNumber(number);
    }


    public void getAllContacts() throws SQLException {
     //   if (NUMBERS_DIRECTORY.isEmpty()) System.out.println("Контактна книга - пуста");
        TelephoneDirectoryDBManager.getContacts();

       // int i = 1;
        // Set<String> setNames = NUMBERS_DIRECTORY.keySet();
        // for (String name : setNames) {
        //    System.out.println("\n" + i + ". Ім'я: " + name + "\n Номер: " + NUMBERS_DIRECTORY.get(name));
        //    i++;
       // }
    }

    public void deleteContactByName(String firstName, String lastName) throws SQLException {
        TelephoneDirectoryDBManager.deleteByName(firstName, lastName);
    }

    public void deleteContactByNumber(String number) throws SQLException {
        TelephoneDirectoryDBManager.deleteByNumber(number);
    }

}
