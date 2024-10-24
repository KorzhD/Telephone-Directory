package org.telephone_directory;


import java.sql.SQLException;
import java.util.Scanner;

public class TelephoneDirectoryUtil {
    private String firstName;
    private String lastName;
    private String fullName;
    private String number;
    private TelephoneDirectoryService telephoneDirectoryService = TelephoneDirectoryService.getInstance();

    private Scanner sc = new Scanner(System.in);

    public void getCommands() throws SQLException {
        System.out.println("Список команд: \n " +
                "1. Додати контакт \n " +
                "2. Відкрити телефонну книгу \n " +
                "3. Знайти контакт за ім'ям \n " +
                "4. Знайти контакт за номером \n" +
                " 5. Видалити контакт за номером \n" +
                " 6. Видалити контакт за ім'ям");
        System.out.print("Команда: ");
        switchCommand(sc.nextLine());
    }
    public void switchCommand(String command) throws SQLException {
        switch (command) {
            case("1"):
                System.out.print("Введіть ім'я: ");
                firstName = sc.nextLine();
                System.out.print("Введіть фамілію: ");
                lastName = sc.nextLine();
                System.out.print("Введіть номер: ");
                number = sc.nextLine();
                telephoneDirectoryService.add(firstName, lastName, number);
                break;
            case("2"):
                telephoneDirectoryService.getAllContacts();
                System.out.println();
                break;
            case("3"):
                System.out.print("Введіть ім'я: ");
                fullName = sc.nextLine();
                telephoneDirectoryService.searchByName(fullName);
                break;
            case("4"):
                System.out.print("Введіть номер: ");
                number = sc.nextLine();
                telephoneDirectoryService.searchByNumber(number);
                break;
            case("5"):
                System.out.print("Введіть номер контакта, який бажаєте видалити: ");
                number = sc.nextLine();
                telephoneDirectoryService.deleteContactByNumber(number);
                break;
            case("6"):
                System.out.print("Введіть ім'я: ");
                firstName = sc.nextLine();
                System.out.print("Введіть прізвище: ");
                lastName = sc.nextLine();
                telephoneDirectoryService.deleteContactByName(firstName, lastName);
                break;

            default:
                System.out.println("Невірно набрана команда");
                break;
        }
        getCommands();
    }
}
