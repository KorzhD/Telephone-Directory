package org.telephone_directory;


public class Main {
    public static void main(String[] args) throws NumberException, NameException, DirectoryException {
        /* Methods:
        getInstance()
        add(String firstName, String lastName, String phone_number)
        searchByName(String name)
        searchByNumber(String number)
        getAllContacts()
        deleteContactByName(String name)
        deleteContactByNumber(String number)
         */

        Telephone_Directory telephoneDirectory = Telephone_Directory.getInstance();
        telephoneDirectory.add("Johan", "Liberty", "+380662563728");
        telephoneDirectory.add("Anna", "Libert", "+380662898998");
        telephoneDirectory.add("Ray", "Bredbery", "+380789452148");
     //   telephoneDirectory.add("Alexander", "Tkachuk", "+380789452148"); // Number is already used
      //  telephoneDirectory.add("Illa", "Tkachuk", "+380374458797798"); // Number is too long
        telephoneDirectory.searchByNumber("+380789452148");










    }
}