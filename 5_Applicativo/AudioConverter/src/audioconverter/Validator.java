package audioconverter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class Validator {
    
    private static String pathExe = "C:\\Users\\matvej.rossi\\ffmpeg\\ffmpeg-master-latest-win64-gpl\\bin\\";
    
    
    private static boolean isValidConverter(Converter converter) {
        
        if (converter.getFormat().equals("Invalid format")) {
            System.out.println("Error: format not valid.");
            return false;
        }
        if (converter.getPath() == null) {
            System.out.println("Error: destination not valid.");
            return false;
        }
        if (converter.getBitrate() == -1) {
            System.out.println("Error: bitrate not valid.");
            return false;
        }
        return true;
    }
    
    
    //Serve a settare un eventuale path diversa per l'utente da quella di default 
    public void setPathExe(String pathExe){
        File path = new File(pathExe);
        if(!path.isDirectory()){
            this.pathExe = null;
        }
        if(!path.exists()){
            this.pathExe = null;
        }else{
            this.pathExe = pathExe;
        }
    }
    
    
    private static String buildFFMPEGCommand(Converter converter) {
        String inputFilePath = converter.getPath() + "/" + converter.getName();  // Nome del file di input
        String outputFilePath = converter.getOutputPath() + "/" + converter.getOutputName(); // Percorso di output
        double outBitrate = converter.getOutputBitrate();  // Qualità del file in output

        // Costruisce la stringa per FFMPEG.
        return "ffmpeg -i " + inputFilePath + " -q:a " + (int) outBitrate + " " + outputFilePath;
    }
    
    private static void executeCommand(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);
            processBuilder.directory(new File(pathExe));
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
            System.out.println("Conversione completata.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Errore nella conversione.");
        }
    }
    
    public static void convertAudio(Converter converter) {
        if (!isValidConverter(converter)) {
            return;  //Esci se converter non valido.
        }

        // Costruisci il comando FFMPEG
        String command = buildFFMPEGCommand(converter);

        // Esegui la conversione
        executeCommand(command);
    }
}