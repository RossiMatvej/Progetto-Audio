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
        System.out.println("-- TEST NAME EMPTY --");
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
     * "Invalid format" se il formato è inesistente.
     */
    @Test
    public void testEmptyFormat() {
        System.out.println("-- TEST EMPTY FORMAT --");
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
        System.out.println("-- TEST CORRECT FORMAT --");
        Converter instance = new Converter();
        String name = "cat_meow.mp3";
        String expected = "MP3";
        instance.setFormat(name);
        //System.out.println(instance.getName());
        String returnFormat = instance.getFormat();
        
        
        //Formato dovrà essere uguale a "MP3", visto che è corretto.
        assertEquals(expected,returnFormat);
        
    }
    
    /**
     * Test che controlla se il setPath() funzioni in modo 
     * da controllare se una path non è una path.
     */
    @Test
    public void testEmptyPath(){
        System.out.println("-- TEST EMPTY PATH");
        Converter instance = new Converter();
        String path = "ciao";
        String expected = "Not found";
        instance.setPath(path);
        String returnPath = instance.getPath();
        
        //Path dovrebbe essere "Not found"
        assertEquals(expected,returnPath);
    }
    
    /**
     * Test che controlla se il setPath() restituisce
     * che la path sia corretta.
     */
    @Test
    public void testCorrectPath(){
        System.out.println("-- TEST CORRECT PATH");
        Converter instance = new Converter();
        instance.setName("test.mp3");
        //String path = "C:/Users/matvej.rossi/";
        String path = "C:/Users/matve/";
        String expected = path;
        //SETPATH --> Controlla anche che il file in input sia nella directory!
        //SETPATH --> Formato e Nome devono essere settati
        instance.setPath(path);
        String returnPath = instance.getPath();
        
        //Path dovrebbe essere "Not found"
        assertEquals(expected,returnPath);
    }
    
    
}
