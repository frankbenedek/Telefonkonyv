package telef;
import java.util.Scanner;


public class Main {
    private static ContactManager manager = new ContactManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        manager.loadContacts();
        boolean exit = false;
        while (!exit) {
            showMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addContact();
                    break;
                case "2":
                    deleteContact();
                    break;
                case "3":
                    listContacts();
                    break;
                case "4":
                    searchContacts();
                    break;
                case "5":
                    System.out.println("Kilépés...");
                    exit = true;
                    break;
                default:
                    System.out.println("Érvénytelen választás.");
                    break;
            }
        }
        scanner.close();
    }
    // Menü kiíratása
    private static void showMenu() {
        System.out.println("\n\t\t\t\t  ===========================");
        System.out.println("\t\t\t\t||\t  Telefonkönyv Menü:     ||");
        System.out.println("\t\t\t\t||---------------------------||");
        System.out.println("\t\t\t\t|| 1. Kontakt hozzáadása     ||");
        System.out.println("\t\t\t\t|| 2. Kontakt törlése        ||");
        System.out.println("\t\t\t\t|| 3. Kontaktok listázása    ||");
        System.out.println("\t\t\t\t|| 4. Kontakt keresése       ||");
        System.out.println("\t\t\t\t|| 5. Kilépés                ||");
        System.out.println("\t\t\t\t  ===========================");
        System.out.print("Parancs> ");
    }

    /* public static void showOptions(){ // Végül sehol nem lett használva, de a program bőbítésénél jól jöhet.
        System.out.println("\t\t\t  =====================================");
        System.out.println("\t\t\t||    \tBack\t\t||\t\tExit\t   ||");
        System.out.println("\t\t\t  =====================================");
    }*/
    public static void showOptions2(){
        System.out.println("\t  ==================================================================================================");
        System.out.println("\t||    \tEdit\t    ||    \tDelete\t    ||    \tvCard\t    ||    \tBack\t    ||    \tExit\t    ||");
        System.out.println("\t  ==================================================================================================");
    }
    public static void showOptions3(){
        System.out.println("\t\t\t  =====================================");
        System.out.println("\t\t\t||    \tSorszám\t\t||\t\tExit\t   ||");
        System.out.println("\t\t\t  =====================================");
    }
    public static void showOptions4(){
        System.out.println("\t  ==========================================================");
        System.out.println("\t||    \tSorszám\t    ||    \tBack\t    ||    \tExit\t    ||");
        System.out.println("\t  ==========================================================");
    }

    // Kontakt hozzáadása
    private static void addContact() {
        // Adatok bekérése
        System.out.print("Név: ");
        String name = scanner.nextLine();
        System.out.print("Becenév: ");
        String nickname = scanner.nextLine();
        System.out.print("Cím: ");
        String address = scanner.nextLine();
        System.out.print("Munkahelyi szám: ");
        String workPhone = scanner.nextLine();
        System.out.print("Privát szám: ");
        String privatePhone = scanner.nextLine();
        // Kontakt létrehozása a megadott adatokból
        Contact contact = new Contact(name, nickname, address, workPhone, privatePhone);
        manager.addContact(contact);
        System.out.println("Kontakt hozzáadva.");
    }
    // Kontakt törlése
    private static void deleteContact() {
        // Kontaktok listázása
        manager.listContacts();
        showOptions3();
        System.out.print("Parancs> ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("exit")) {
            return; // Visszatérés a főmenühöz
        }
        // Törlés
        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < manager.getContacts().size()) {
                manager.deleteContact(index);
            } else {
                System.out.println("Érvénytelen sorszám! Kérjük, próbáld újra.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Érvénytelen bemenet! Kérjük, adj meg egy érvényes sorszámot vagy írd be, hogy 'exit'.");
        }
    }
    // Kontaktok listázása
    private static void listContacts() {
        while (true) {
            manager.listContacts();
            showOptions3();
            System.out.print("Parancs> ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                return;  // Teljes kilépés a főmenübe
            }

            int index;
            try {
                index = Integer.parseInt(command) - 1;
                if (index < 0 || index >= manager.getContacts().size()) {
                    System.out.println("Érvénytelen sorszám!");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Érvénytelen parancs!");
                break;
            }

            while (true) {
                showOptions2();
                System.out.print("Parancs> ");
                String action = scanner.nextLine().trim();

                if (action.equalsIgnoreCase("exit")) {
                    return;  // Teljes kilépés a főmenübe
                } else if (action.equalsIgnoreCase("back")) {
                    break;  // Visszalépés
                }

                if (action.equalsIgnoreCase("edit")) {
                    editContact(index);  // Szerkesztés hívása
                    break;
                } else if (action.equalsIgnoreCase("delete")) {
                    manager.deleteContact(index);  // Törlés hívása
                    break;
                } else if (action.equalsIgnoreCase("vcard")) {
                    manager.exportVCard(index);
                    break;
                } else {
                    System.out.println("Érvénytelen parancs.");
                }
            }
        }
    }

    // Kontakt szerkesztése
    private static void editContact(int index) {
        // Ój adatok bekérése
        System.out.print("Új név (hagyja üresen, ha nem változtat): ");
        String name = scanner.nextLine();
        System.out.print("Új becenév (hagyja üresen, ha nem változtat): ");
        String nickname = scanner.nextLine();
        System.out.print("Új cím (hagyja üresen, ha nem változtat): ");
        String address = scanner.nextLine();
        System.out.print("Új munkahelyi szám (hagyja üresen, ha nem változtat): ");
        String workPhone = scanner.nextLine();
        System.out.print("Új privát szám (hagyja üresen, ha nem változtat): ");
        String privatePhone = scanner.nextLine();
        // A kontakt megváltoztatása
        manager.editContact(index, name, nickname, address, workPhone, privatePhone);
        System.out.println("Kontakt szerkesztve.");
    }
    // Keresés a kontaktok között
    private static void searchContacts() {
        while (true) {
            System.out.print("Keresési kritérium (név, becenév vagy cím): ");
            String searchQuery = scanner.nextLine().trim();

            if (searchQuery.equalsIgnoreCase("exit")) {
                return; // Vissza a főmenübe
            }

            manager.searchContacts(searchQuery); // A keresés meghívása

            while (true) {
                showOptions4();
                System.out.print("Parancs> ");
                String command = scanner.nextLine().trim();

                if (command.equalsIgnoreCase("exit")) {
                    return; // Vissza a főmenübe
                } else if (command.equalsIgnoreCase("back")) {
                    break; // Kilépés csak ebből a belső ciklusból
                }

                try {
                    int index = Integer.parseInt(command) - 1;
                    if (index >= 0 && index < manager.getContacts().size()) {
                        showOptions2();
                        System.out.print("Parancs> ");
                        String action = scanner.nextLine().trim();

                        if (action.equalsIgnoreCase("edit")) {
                            editContact(index);
                        } else if (action.equalsIgnoreCase("delete")) {
                            manager.deleteContact(index);
                        } else if (action.equalsIgnoreCase("vcard")) {
                            manager.exportVCard(index);
                            break;
                        } else if (action.equalsIgnoreCase("back")){
                            break;

                        }  else if (action.equalsIgnoreCase("exit")){
                            return;
                        } else {
                            System.out.println("Érvénytelen parancs. Próbáld újra.");
                        }
                    } else {
                        System.out.println("Érvénytelen sorszám!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Kérjük, érvényes számot adj meg!");
                }
            }
        }
    }
}
