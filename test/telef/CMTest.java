package telef;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;

public class CMTest {
    private ContactManager manager;

    @Before
    public void setUp() {
        manager = new ContactManager("test_contacts.json");
        new File("test_contacts.json").delete();    }

    @Test
    public void testAddContact() {
        Contact contact = new Contact("Teszt név", "kutyus", "BME utca 99", "+36 20 123 4567", "+36 20 123 4567");
        manager.addContact(contact);
        assertFalse("Üres, pedig nem kéne.", manager.getContacts().isEmpty());
        assertEquals("Csak egy kontaktnak kellene szerepelnie benne.", 1, manager.getContacts().size());
        manager.deleteContact(0);
    }

    @Test
    public void testDeleteContact() {
        Contact contact = new Contact("Teszt név", "kutyus", "BME utca 99", "+36 20 123 4567", "+36 20 123 4567");
        manager.addContact(contact);
        assertEquals("Nem 1 kontakt van.", 1, manager.getContacts().size());
        manager.deleteContact(0);
        assertEquals("Nem üres",  0,  manager.getContacts().size());
    }

    @Test
    public void testEditContactSuccess() {
        Contact contact = new Contact("Teszt név", "kutyus", "BME utca 99", "+36 20 123 4567", "+36 20 123 4567");
        manager.addContact(contact);
        manager.editContact(0, "Változott", "Ez is", "CORVINUS :(", "+36 20 765 4321", "+36 20 765 4321");
        Contact edited = manager.getContacts().get(0);
        assertEquals("Változott", edited.getName());
        assertEquals("Ez is", edited.getNickname());
        assertEquals("CORVINUS :(", edited.getAddress());
        assertEquals("+36 20 765 4321", edited.getWorkPhone());
        assertEquals("+36 20 765 4321", edited.getPrivatePhone());
        manager.deleteContact(0);
    }

    @Test
    public void testEditContactInvalidIndex() {
        Contact contact = new Contact("Teszt név", "kutyus", "BME utca 99", "+36 20 123 4567", "+36 20 123 4567");
        manager.addContact(contact);
        manager.editContact(1, "Rossz index", "", "", "", "");
        assertEquals(1, manager.getContacts().size()); // Méret változatlan, nincs új kontakt hozzáadva
        Contact original = manager.getContacts().get(0);
        assertNotEquals("Rossz index", original.getName());
        manager.deleteContact(0);
    }



    @Test
    public void testListContacts() {
        // Ellenőrizd, hogy a kontaktlista üres-e
        assertTrue("Nem üres a lista", manager.getContacts().isEmpty());


        // Átirányítjuk a kimenetet, hogy ellenőrizhessük
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Metódus meghívása
        manager.listContacts();

        // Ellenőrizzük, hogy az elvárt üzenet megjelent-e
        String output = outContent.toString();
        assertTrue("Nem jelenik meg, hogy nincsenek kontaktok.", output.contains("Nincsenek kontaktok."));
    }



}
