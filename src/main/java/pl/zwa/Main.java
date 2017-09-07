package pl.zwa;

import pl.zwa.models.ContactModel;
import pl.zwa.models.dao.ContactDao;
import pl.zwa.models.dao.impl.ContactDaoImpl;

import java.util.Scanner;

//pod MVC
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String response;
        ContactDao contactDao = new ContactDaoImpl();

        do {
            System.out.println("Wpisz: ");
            System.out.println("1 - aby dodać nowy kontakt (numer, imie, nazwisko)");
            System.out.println("2 - aby usunąć kontakt ");
            System.out.println("3 - aby wyświetlić wszystkie kontakty ");
            System.out.println("4 - Wyświetlanie imienia i nazwiska po podaniu numeru");
            System.out.println("Exit - aby wyjść");

            System.out.print("Odpowiedź: ");
            response = scanner.nextLine();

            switch (response) {
                case "1": {
                    ContactModel model = new ContactModel(scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
                    contactDao.addContact(model);
                    break;
                }
                case "2": {
                    System.out.println("Podaj numer który chcesz usunąć: ");
                    contactDao.removeContact(scanner.nextLine());
                    break;
                }
                case "3": {
                    for (ContactModel contactModel : contactDao.getAllContacts()) {
                        System.out.println(contactModel.toString());

                    }break;
                }
                case "4": {
                    System.out.println("Podaj numer telefonu: ");
                    System.out.println(contactDao.getContactByNumber(scanner.nextLine()).toString());
                    break;
                    }
                }

        }while (!response.equalsIgnoreCase("exit"));

    }

}
