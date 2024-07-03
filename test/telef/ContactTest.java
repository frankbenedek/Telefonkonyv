package telef;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ContactTest {
    private Contact contact;

    @Before
    public void setUp() {
        // Létrehozunk egy Contact példányt tesztelés céljából
        contact = new Contact("Kalapos József", "Józsi", "Kalapos utca 12", "+36 20 123 4567", "+36 20 123 4567");
    }

    @Test
    public void testGetters() {
        assertEquals("Kalapos József", contact.getName());
        assertEquals("Józsi", contact.getNickname());
        assertEquals("Kalapos utca 12", contact.getAddress());
        assertEquals("+36 20 123 4567", contact.getWorkPhone());
        assertEquals("+36 20 123 4567", contact.getPrivatePhone());
    }

    @Test
    public void testSetters() {
        contact.setName("Sapkás József");
        contact.setNickname("Jozsó");
        contact.setAddress("Sapkás utca 21");
        contact.setWorkPhone("+36 20 765 4321");
        contact.setPrivatePhone("+36 20 765 4321");

        assertEquals("Sapkás József", contact.getName());
        assertEquals("Jozsó", contact.getNickname());
        assertEquals("Sapkás utca 21", contact.getAddress());
        assertEquals("+36 20 765 4321", contact.getWorkPhone());
        assertEquals("+36 20 765 4321", contact.getPrivatePhone());
    }

    @Test
    public void testCalculateMaxWidth() {
        // Teszteli a szélesség kiszámítását az aktuális adatok alapján
        int expectedWidth = Math.max(
                Math.max(
                        Math.max("Munkahelyi szám:  ".length() + contact.getName().length(),
                                "Munkahelyi szám:  ".length() + contact.getNickname().length()),
                        Math.max("Munkahelyi szám:  ".length() + contact.getAddress().length(),
                                "Munkahelyi szám:  ".length() + contact.getWorkPhone().length())
                ),
                "Munkahelyi szám:  ".length() + contact.getPrivatePhone().length()
        ) + "||".length() + 1;

        assertEquals(expectedWidth, contact.calculateMaxWidth());
    }

    @Test
    public void testToStringFormat() {
        String expectedOutput = "\n\t\t\t|| Név: Kalapos József" + contact.getSpacesForAlignment("Név: ", contact.getName(), contact.calculateMaxWidth()) + "||" +
                "\n\t\t\t|| Becenév: Józsi" + contact.getSpacesForAlignment("Becenév: ", contact.getNickname(), contact.calculateMaxWidth()) + "||" +
                "\n\t\t\t|| Cím: Kalapos utca 12" + contact.getSpacesForAlignment("Cím: ", contact.getAddress(), contact.calculateMaxWidth()) + "||" +
                "\n\t\t\t|| Munkahelyi szám: +36 20 123 4567" + contact.getSpacesForAlignment("Munkahelyi szám: ", contact.getWorkPhone(), contact.calculateMaxWidth()) + "||" +
                "\n\t\t\t|| Privát szám: +36 20 123 4567" + contact.getSpacesForAlignment("Privát szám: ", contact.getPrivatePhone(), contact.calculateMaxWidth()) + "||" + "\n";

        assertEquals(expectedOutput, contact.toString());
    }
}
