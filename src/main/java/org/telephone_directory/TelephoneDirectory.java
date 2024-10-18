package org.telephone_directory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.telephone_directory.exception.DirectoryException;
import org.telephone_directory.exception.NameException;
import org.telephone_directory.exception.NumberException;

public class TelephoneDirectory {
    private static TelephoneDirectory instance;
    private static final HashMap<String, String> NUMBERS_DIRECTORY = new HashMap<>();

    private TelephoneDirectory() {
    }

    public static TelephoneDirectory getInstance() {
        if (instance == null) {
            instance = new TelephoneDirectory();
        }
        return instance;
    }
    public void add(String firstName, String lastName, String phoneNumber) throws NumberException, NameException {
        if (firstName.isBlank()) {
            throw new NameException("Incorrect name");
        }
        if (lastName.isBlank()) {
            throw new NameException("Incorrect last name");
        }
        // todo change to regular expression
        if (!(phoneNumber.subSequence(0, 4).equals("+380")) || !(phoneNumber.length() == 13)) {
            throw new NumberException("Wrong number format. The number must begin with +380 and and contain 12 numbers");
        }
        if (NUMBERS_DIRECTORY.containsValue(phoneNumber)) {
            throw new NumberException("This number is already used");
        } else {
            String fullName = firstName + " " + lastName;
            NUMBERS_DIRECTORY.put(fullName, phoneNumber);
        }
    }

    public String searchByName(String name) throws DirectoryException {
        if (NUMBERS_DIRECTORY.containsKey(name)) {
            return NUMBERS_DIRECTORY.get(name);
        } else {
            throw new DirectoryException("Invalid name or this person is not in the directory");
        }
    }

    public String searchByNumber(String number) throws DirectoryException {
        if (NUMBERS_DIRECTORY.containsValue(number)) {
            for (Map.Entry<String, String> entry : NUMBERS_DIRECTORY.entrySet()) {
                if (entry.getValue().equals(number)) {
                    String name = entry.getKey();
                    return name;
                }
            }
        } else
            throw new DirectoryException("Invalid number or this person is not in the directory");
            return null;
        }


    public void getAllContacts() {
        if(NUMBERS_DIRECTORY.isEmpty()) System.out.println("Контактна книга - пуста");
        int i = 1;
        Set<String> setNames = NUMBERS_DIRECTORY.keySet();
        for (String name : setNames) {
            System.out.println("\n" + i + ". Ім'я: " + name + "\n Номер: " + NUMBERS_DIRECTORY.get(name));
                i++;
            }
    }

    public void deleteContactByName(String name) throws NameException {
        if(NUMBERS_DIRECTORY.containsKey(name)) {
            NUMBERS_DIRECTORY.remove(name);
        }
        else throw new NameException("Can't delete this contact \n Check the name");
    }
    public void deleteContactByNumber(String number) throws NumberException {
        if (NUMBERS_DIRECTORY.containsValue(number)) {
            Set<String> setNames = NUMBERS_DIRECTORY.keySet();
            for(String name : setNames) {
                if(NUMBERS_DIRECTORY.get(name).equals(number)) {
                    NUMBERS_DIRECTORY.remove(name);
                    break;
                }
            }
        }
        else throw new NumberException("Can't delete this contact \n Check the number");
    }

}
