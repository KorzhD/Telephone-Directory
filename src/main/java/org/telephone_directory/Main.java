package org.telephone_directory;


import org.telephone_directory.exception.DirectoryException;
import org.telephone_directory.exception.NameException;
import org.telephone_directory.exception.NumberException;

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

        TelephoneDirectory telephoneDirectory = TelephoneDirectory.getInstance();
        telephoneDirectory.add("Johan", "Liberty", "+380662563728");
        telephoneDirectory.add("Anna", "Libert", "+380662898998");
        telephoneDirectory.add("Ray", "Bredbery", "+380789452148");
     //   telephoneDirectory.add("Alexander", "Tkachuk", "+380789452148"); // Number is already used
      //  telephoneDirectory.add("Illa", "Tkachuk", "+380374458797798"); // Number is too long
        telephoneDirectory.searchByNumber("+380789452148");
    }
}


/*
* Список комманд
* 1 - Добавить номер -> введите имя -> введите фамилию ->
* 2 - Поменять номер
* 3 - Удалить номер
* 4 - Показать телефонную книгу
* Команда: 1
*
* HAPPY PATH
*
* Введите имя: Степан
* Введите фамилию: Бортюк
* Введите номер: +380675425678
* Номер успешно добавлен в вашу телефоную книгу
*
*
* Список комманд
* 1 - Добавить номер
* 2 - Поменять номер
* 3 - Удалить номер
* 4 - Показать телефонную книгу
*/