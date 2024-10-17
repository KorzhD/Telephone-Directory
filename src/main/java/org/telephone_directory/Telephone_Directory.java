package org.telephone_directory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Telephone_Directory {
    private static volatile Telephone_Directory instance;
    private final HashMap<String, String> numbers_directory = new HashMap<>();

    private Telephone_Directory() {
    }

    public static Telephone_Directory getInstance() {
        if (instance == null) {
            synchronized (Telephone_Directory.class) {
                if (instance == null)
                    instance = new Telephone_Directory();
            }
        }
        return instance;
    }

    public void add(String firstName, String lastName, String phone_number) throws NumberException, NameException {
        if (firstName.equals(null) || firstName.equals("")) {
            throw new NameException("Incorrect name");
        }
        if (lastName.equals(null) || lastName.equals("")) {
            throw new NameException("Incorrect last name");
        }
        if (!(phone_number.subSequence(0, 4).equals("+380")) || !(phone_number.length() == 13)) {
            throw new NumberException("Wrong number format. The number must begin with +380 and and contain 12 numbers");
        }
        if (numbers_directory.containsValue(phone_number)) {
            throw new NumberException("This number is already used");
        } else {
            String fullName = firstName + " " + lastName;
            numbers_directory.put(fullName, phone_number);
        }
    }

    public String searchByName(String name) throws DirectoryException {
        if (numbers_directory.containsKey(name)) {
            return numbers_directory.get(name);
        } else {
            throw new DirectoryException("Invalid name or this person is not in the directory");
        }
    }

    public String searchByNumber(String number) throws DirectoryException {
        if (numbers_directory.containsValue(number)) {
            for (Map.Entry<String, String> entry : numbers_directory.entrySet()) {
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
        int i = 1;
        Set<String> setNames = numbers_directory.keySet();
        for (String name : setNames) {
            System.out.println(i + ". Name: " + name + "\n Number: " + numbers_directory.get(name) + "\n");
                i++;
            }
    }

    public void deleteContactByName(String name) throws NameException {
        if(numbers_directory.containsKey(name)) {
            numbers_directory.remove(name);
        }
        else throw new NameException("Can't delete this contact \n Check the name");
    }
    public void deleteContactByNumber(String number) throws NumberException {
        if (numbers_directory.containsValue(number)) {
            Set<String> setNames = numbers_directory.keySet();
            for(String name : setNames) {
                if(numbers_directory.get(name).equals(number)) {
                    numbers_directory.remove(name);
                    break;
                }
            }
        }
        else throw new NumberException("Can't delete this contact \n Check the name");
    }

}
