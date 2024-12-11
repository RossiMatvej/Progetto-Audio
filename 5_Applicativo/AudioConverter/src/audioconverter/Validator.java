package audioconverter;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class Validator {
    
    private static String pathExe = System.getenv("ffmpegPath");
    
    
    private static boolean isValidConverter(Converter converter) {
        
        if (converter.getFormat().equals("Invalid format")) {
            System.out.println("Error: format not valid.");
            return false;
        }
        if(converter.getOutputFormat().equals("Invalid format")){
            System.out.println("Error: Output format is not valid.");
            return false;
        }
        
        if (converter.getPath().equals("Not found")) {
            System.out.println("Error: destination not valid.");
            return false;
        }
        if (converter.getBitrate() == -1) {
            System.out.println("Error: bitrate not valid.");
            return false;
        }
        return true;
    }
    
    
    //Serve a settare un eventuale path diversa per l'utente.
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
    
    public String getPathExe(){
        return this.pathExe;
    }
    
    
    private static String buildFFMPEGCommand(Converter converter) {
        String inputFilePath = converter.getPath() + converter.getName();  // Nome del file di input
        String outputFilePath = converter.getOutputPath() + "\\" + converter.getOutputName(); // Percorso di output
        double outBitrate = converter.getBitrate();  // Qualità del file in output

        // Costruisce la stringa per FFMPEG.
        //return pathExe + "\\ffmpeg.exe -i " + inputFilePath + " -q:a " + (int) outBitrate + " " + outputFilePath;
        return "ffmpeg -i " + inputFilePath + " -q:a " + (int) outBitrate + " " + outputFilePath;
    }
    
    private static void executeCommand(String command) {
        try {
            //ProcessBuilder processBuilder = new ProcessBuilder(command);
            //ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            //ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe" , "file.txt");
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);
            //processBuilder.redirectInput();
            //processBuilder.inheritIO();
            processBuilder.directory(new File(pathExe));
            Process process = processBuilder.start();
            System.out.println(process.pid());
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            //reader.close();
            process.waitFor();
            System.out.println("Conversion succeded.");
        } catch (IOException | InterruptedException e) {
            //e.getMessage();
            //e.printStackTrace();
            System.out.println("\nConversion failed. --> " + e.getMessage());
        }
    }
    
//    private static void executeCommand(String command) {
//        try {
//
//        // print a message
//        System.out.println("Esecuzione del comando...");
//
//        // create a process and execute calc.exe
//        Process process = Runtime.getRuntime().exec(command);
//        process.waitFor();
//
//        // print another message
//        System.out.println("Comando eseguito.");
//
//        } catch (IOException | InterruptedException ex) {
//            System.out.println("Conversion Failed. --> " + ex.getMessage());
//        }
//    }
    
    public static void convertAudio(Converter converter) {
        if (!isValidConverter(converter)) {
            return;  //Esce se il converter non è valido.
        }

        // Costruisce comando FFMPEG
        String command = buildFFMPEGCommand(converter);

        // Esegue la conversione
        executeCommand(command);
    }
}