package telef;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("5\n".getBytes());

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }

    @Test
    public void testMainExit() {
        Main.main(new String[]{});
        assertTrue("Ki kéne írnia, hogy 'Kilépés...'", outContent.toString().contains("Kilépés..."));
    }
}
