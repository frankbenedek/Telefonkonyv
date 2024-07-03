package telef;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class ContactManager {
    private List<Contact> contacts;
    private static String FILENAME = "contacts.json";
    private Gson gson;
    // Alapértelmezett konstruktor
    public ContactManager() {
        this("contacts.json");
    }

    // Tesztkonstruktor, amely lehetővé teszi a fájlnév módosítását
    public ContactManager(String filename) {
        FILENAME = filename;
        this.contacts = new ArrayList<>();
        this.gson = new Gson();
        loadContacts();
    }
    // Kontakt hozzáadása
    public void addContact(Contact contact) {
        contacts.add(contact);
        saveContacts();
    }
    // Kontakt törlése
    public void deleteContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            contacts.remove(index);
            System.out.println("Kontakt törölve.");
            saveContacts();
        } else {
            System.out.println("Érvénytelen index.");
        }
    }
    // Kontaktok listázása
    public void listContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Nincsenek kontaktok.");
            return;
        }
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println("\t\t\t\t  ===========================\n\t\t\t\t||\t\t\t   " + (i + 1) + ".\t\t\t  ||\n" + "\t\t\t\t  ===========================\n\t\t\t" +contacts.get(i));
        }
    }
    // Kontakt szerkesztése
    public void editContact(int index, String name, String nickname, String address, String workPhone, String privatePhone) {
        if (index >= 0 && index < contacts.size()) {
            Contact contact = contacts.get(index);
            if (!name.isEmpty()) contact.setName(name);
            if (!nickname.isEmpty()) contact.setNickname(nickname);
            if (!address.isEmpty()) contact.setAddress(address);
            if (!workPhone.isEmpty()) contact.setWorkPhone(workPhone);
            if (!privatePhone.isEmpty()) contact.setPrivatePhone(privatePhone);
            saveContacts();
        } else {
            System.out.println("Érvénytelen index.");
        }
    }
    // Keresés a kontaktok között
    public void searchContacts(String query) {
        boolean found = false;
        int index = 1;
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(query.toLowerCase()) ||
                    contact.getNickname().toLowerCase().contains(query.toLowerCase()) ||
                    contact.getAddress().toLowerCase().contains(query.toLowerCase())) {

                System.out.println("\t\t\t\t  ===========================\n\t\t\t\t||\t\t\t   " + index + ".\t\t\t  ||\n" + "\t\t\t\t  ===========================\n\t\t\t" +contact);

                found = true;
                index++;
            }
        }
        if (!found) {
            System.out.println("Nincs találat.");
        }
    }
    // Kontaktok mentése
    private void saveContacts() {
        try (FileWriter writer = new FileWriter(FILENAME)) {
            gson.toJson(contacts, writer);
        } catch (IOException e) {
            System.out.println("Hiba történt az adatok mentése közben: " + e.getMessage());
        }
    }
    // Kontaktok betöltése
    public void loadContacts() {
        try (FileReader reader = new FileReader(FILENAME)) {
            Type type = new TypeToken<List<Contact>>() {}.getType();
            contacts = gson.fromJson(reader, type);
            if (contacts == null) {
                contacts = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Hiba történt az adatok betöltése közben: " + e.getMessage());
        }
    }
    public void exportVCard(int index) {
        if (index >= 0 && index < contacts.size()) {
            Contact contact = contacts.get(index);
            String filename = contact.getName().replaceAll(" ", "_") + ".vcf"; // Az állománynév létrehozása a név alapján
            try (FileWriter writer = new FileWriter(filename)) {
                writer.write("BEGIN:VCARD\n");
                writer.write("VERSION:4.0\n");
                writer.write("FN:" + contact.getName() + "\n");  // Teljes név használata
                writer.write("NICKNAME:" + contact.getNickname() + "\n");  // Becenév
                writer.write("TEL;TYPE=work,voice:" + contact.getWorkPhone() + "\n");  // Munkahelyi telefon
                writer.write("TEL;TYPE=cell,voice:" + contact.getPrivatePhone() + "\n");  // Privát telefon
                writer.write("ADR;TYPE=home:" + contact.getAddress().replaceAll("\n", ",") + ";;;\n");  // Cím, cserélve az újsor karaktereket vesszőkre a vCard formátumhoz
                writer.write("END:VCARD\n");
                System.out.println("Kontakt exportálva.");
            } catch (IOException e) {
                System.out.println("Error writing vCard: " + e.getMessage());
            }
        }

    }

    // Getterek
    public List<Contact> getContacts() {return contacts;}


}

