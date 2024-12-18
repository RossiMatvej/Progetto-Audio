package audioconverter;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


class Validator {
    
    private static String pathExe = System.getenv("ffmpegPath");
    
    
    private static boolean isValidConverter(Converter converter) {
        
        try{
            if (converter.getFormat().equals("Invalid format")) {
                System.out.println("Error: format not valid.");
                return false;
            }
            if(converter.getOutputFormat().equals("Invalid format")){
                System.out.println("Error: Output format is not valid.");
                return false;
            }
        
            if (converter.getPath().equals("Not found")) {
            System.out.println("Error: input path not valid.");
            return false;
            }
            if(converter.getPath().equals("Not exists")){
                System.out.println("Error: input file not exists in path directory");
                return false;
            }
        }catch(NullPointerException ex){
            System.out.println("Error: input path was not found.");
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
    
    
    private static String buildFFMPEGCommand(Converter converter, boolean overwrite) {
        String inputFilePath = "\"" + converter.getPath() + converter.getName() + "\"";  // Nome del file di input
        String outputFilePath = converter.getOutputPath() + "\\" + converter.getOutputName(); // Percorso di output
        double outBitrate = converter.getBitrate();  // Qualità del file in output

        // Costruisce la stringa per FFMPEG.
        //Si potrebbe usare -b:a per il Bitrate fisso.
        if(overwrite){
            System.out.println("Overwriting the output file if necessary.");
            return pathExe + "\\ffmpeg.exe -i " + inputFilePath + " -q:a " + (int) outBitrate + " -y " + "\""+ outputFilePath + "\"";
        }else{
            return pathExe + "\\ffmpeg.exe -i " + inputFilePath + " -q:a " + (int) outBitrate + " " + "\""+ outputFilePath + "\"";
        }
        
        
    }
    
    private static void executeCommand(String command, Converter converter) {
        boolean checkEx = true;
        boolean checkOut = true;
        String name = converter.getName();
        String nameOutput = converter.getOutputName();
        
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);
            
            if (pathExe != null) {
                processBuilder.directory(new File(pathExe));
            }else{
                checkEx = false;
            }
            
            if(checkEx){
                Process process = processBuilder.start();
                //System.out.println("Working directory: " + System.getProperty("user.dir"));
                process.waitFor();
                InputStream inputStream = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    if(line.toLowerCase().contains("error")){
                        System.out.println(line);
                        checkOut = false;
                    }  
                }
                
                if(checkOut){
                    System.out.println("Conversion succeded. --> successfully converted " + name + " to " + nameOutput);
                    System.out.println("Directory: " + converter.getOutputPath());
                }else{
                    System.out.println("\nConversion failed.");
                }
                
                
            }else{
                System.out.println("Error on the path:" + pathExe);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("\nConversion failed. --> " + e.getMessage());
        }
    }
    

    
    public static void convertAudio(Converter converter, boolean overwrite) {
        if (!isValidConverter(converter)) {
            return;  //Esce se il converter non è valido.
        }

        // Costruisce comando FFMPEG
        String command = buildFFMPEGCommand(converter, overwrite);

        // Esegue la conversione
        executeCommand(command, converter);
    }
}