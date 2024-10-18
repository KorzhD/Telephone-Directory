package org.telephone_directory;

import org.telephone_directory.exception.DirectoryException;
import org.telephone_directory.exception.NameException;
import org.telephone_directory.exception.NumberException;

import java.util.Scanner;

public class TelephoneDirectoryManager {
    private static String firstName;
    private static String lastName;
    private static String fullName;
    private static String number;
    private static TelephoneDirectory telephoneDirectory = TelephoneDirectory.getInstance();

    private static Scanner sc = new Scanner(System.in);

    public static void getCommands() throws NameException, NumberException, DirectoryException {
        System.out.println("\n Список команд: \n " +
                "1. Додати контакт \n " +
                "2. Відкрити телефонну книгу \n " +
                "3. Знайти контакт за ім'ям \n " +
                "4. Знайти контакт за номером \n" +
                " 5. Видалити контакт за номером \n" +
                " 6. Видалити контакт за ім'ям");
        System.out.print("Команда: ");
        switchCommand(sc.nextInt());
    }
    public static void switchCommand(int command) throws NameException, NumberException, DirectoryException {
        String text = sc.nextLine();
        switch (command) {
            case(1):
                System.out.print("Введіть ім'я: ");
                firstName = sc.nextLine();
                System.out.print("Введіть фамілію: ");
                lastName = sc.nextLine();
                System.out.print("Введіть номер: ");
                number = sc.nextLine();
                telephoneDirectory.add(firstName, lastName, number);
                System.out.println("\n Контакт був доданий до Телефонного Довідника");
                break;
            case(2):
                telephoneDirectory.getAllContacts();
                System.out.println();
                break;
            case(3):
                System.out.print("Введіть повне ім'я: ");
                fullName = sc.nextLine();
                System.out.println("\n За цим ім'ям знайдено номер: " + telephoneDirectory.searchByName(fullName) + "\n");
                break;
            case(4):
                System.out.print("Введіть номер: ");
                number = sc.nextLine();
                System.out.println("\n За цим номером був знайдений контакт: " + telephoneDirectory.searchByNumber(number) + "\n");
                break;
            case(5):
                System.out.print("Введіть номер контакта, який бажаєте видалити: ");
                number = sc.nextLine();
                telephoneDirectory.deleteContactByNumber(number);
                System.out.println("\n Ви успішно удалили контакт \n");
                break;
            case(6):
                System.out.print("Введіть повне ім'я контакта, який бажаєте видалити: ");
                fullName = sc.nextLine();
                telephoneDirectory.deleteContactByName(fullName);
                System.out.println("\n Ви успішно удалили контакт");
                break;

        }
        getCommands();
    }
}
