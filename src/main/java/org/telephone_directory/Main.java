package org.telephone_directory;


import org.telephone_directory.exception.DirectoryException;
import org.telephone_directory.exception.NameException;
import org.telephone_directory.exception.NumberException;

public class Main {
    public static void main(String[] args) throws NumberException, NameException, DirectoryException {
        TelephoneDirectoryManager.getCommands();

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