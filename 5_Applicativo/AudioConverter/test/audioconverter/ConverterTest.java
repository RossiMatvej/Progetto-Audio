package audioconverter;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 *
 * @author matvej.rossi
 */
public class ConverterTest {
    
    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            System.out.println(description.getDisplayName() + " è passato!");
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println(description.getDisplayName() + " è fallito!");
        }
    };
    

    /**
     * Testa se il nome è vuoto ritorna il nome "Undefined"
     */
    @Test
    public void testNameEmpty() {
        System.out.println("-- TEST NAME EMPTY -- ");
        Converter instance = new Converter();
        String name = "      ";
        String expected = "Undefined";
        instance.setName(name);
        String returnName = instance.getName();
        
        //Nome dovrà essere uguale a "Undefined";
        assertEquals(expected,returnName);

    }

    /**
     * Test che controlla il funzionamento del setFormat(), in modo da ritornare
     * "Invalid format" se il formato è una stringa vuota.
     */
    @Test
    public void testEmptyFormat() {
        System.out.println("-- TEST EMPTY FORMAT -- ");
        Converter instance = new Converter();
        String name = "file";
        String expected = "Invalid format";
        instance.setFormat(name);
        String returnFormat = instance.getFormat();
        
        //Formato dovrà essere uguale a "Invalid format"
        assertEquals(expected,returnFormat);
        
    }
    
    /**
     * Test che controlla il funzionamento del setFormat(), in modo da ritornare
     * il formato MP3 come formato corretto.
     */
    @Test
    public void testCorrectFormat() {
        System.out.println("-- TEST CORRECT FORMAT -- ");
        Converter instance = new Converter();
        String name = "file.mp3";
        String expected = "MP3";
        instance.setFormat(name);
        //System.out.println(instance.getName());
        String returnFormat = instance.getFormat();
        
        
        //Formato dovrà essere uguale a "MP3", visto che è corretto.
        assertEquals(expected,returnFormat);
        
    }
    
}
