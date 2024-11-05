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
        System.out.println("\n Список команд: \n " +
                "1. Додати контакт \n " +
                "2. Відкрити телефонну книгу \n " +
                "3. Знайти контакт за повним ім'ям \n " +
                "4. Знайти контакт за номером \n" +
                " 5. Видалити контакт за номером \n" +
                " 6. Видалити контакт за повним ім'ям \n" +
                " 7. Знайти контакти по прізвищу \n" +
                " 8. Змінити номер контакта ");
        System.out.print("Команда: ");
        switchCommand(sc.nextLine());
    }
    public void switchCommand(String command) throws SQLException {
        switch (command) {
            case("1"):
                System.out.print("Введіть ім'я: ");
                firstName = sc.nextLine();
                System.out.print("Введіть прізвище: ");
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
                System.out.print("Введіть повне ім'я: ");
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
            case("7"):
                System.out.print("Введіть прізвище: ");
                lastName = sc.nextLine();
                telephoneDirectoryService.findByLastName(lastName);
                break;
            case("8"):
                System.out.print("Введіть ім'я: ");
                firstName = sc.nextLine();
                System.out.print("Введіть пірзвище: ");
                lastName = sc.nextLine();
                System.out.print("Введіть новий номер: ");
                number = sc.nextLine();
                telephoneDirectoryService.changeNumber(firstName, lastName, number);
                break;
            default:
                System.out.println("Невірно набрана команда");
                break;
        }
        getCommands();
    }
}
