package audioconverter;

import java.io.File;

final class Converter {
    private String name; //Nome completo del file + estensione (ES. file.mp3)
    private String format; // Formato preso in base al nome (ES. "MP3")
    private String path; 
    private String defaultPath = "C:\\Users\\" + System.getProperty("user.name"); //Path di default per Output
    private int bitrate; //Facoltativo
    private String outputPath; //Facoltativo
    private String outputName; //Facoltativo
    private String outputFormat; 

    
    
    // Costruttore di default (con valori predefiniti)
    public Converter() {
        this.name = "fileConverter.mp3";
        this.format = "MP3";  // formato di default
        this.path = null;
        this.bitrate = 128; // bitrate di default
        this.outputPath = this.defaultPath;
        this.outputFormat = "ogg";
        this.outputName = this.getOnlyName() + "." + this.outputFormat;
        
    }
    
    public Converter(String name, String format, String path, int bitrate ,String outputPath, String outputName, String outputFormat){
        this.name = name;
        this.format = format;
        this.path = path;
        this.bitrate = bitrate;
        this.outputPath = outputPath;
        this.outputName = outputName;
        this.outputFormat = outputFormat;
    }
    
    
    
    //Ritorna solamente il nome del file.
    public String getName(){
        return name;
    }
    
    public String getOnlyName(){
        if(this.name.equals("Undefined")){
            return "Undefined";
        }else{
            try{
                String[] splitName = this.name.split("\\.");
                return splitName[0];
            }catch(Exception e){
                return "Undefined";
            }
            
        }
    }

    public void setName(String name) {
        if(name.isBlank() || name.isEmpty()){
            name = "Undefined";
        }
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    
    // Metodo messo "final" così da togliere la possibilità di fare un Override.
    public final void setFormat(String name) {
        boolean check = true;
        String formatLocal = null;
        //setName(name);
        if(name.equals("Undefined")){
            check = false;
        }else{
            
            name = name.toUpperCase();
            String[] formatSplit = name.split("\\.");
            if(formatSplit.length < 2){
                check = false;
            }else{
                formatLocal = formatSplit[formatSplit.length - 1].toUpperCase();
                
            }
            

            String[] allFormats = {"MP3", "FLAC", "WAV", "OGG", "AAC", "M4A"};
            for (String formatCheck : allFormats) {
                if (!(formatCheck.equals(formatLocal))) {
                    check = false;
                }else{
                    check = true;
                    break;
                }
            }
        }
        if(check){
            this.format = formatLocal;
        }else{
            this.format = "Invalid format";
        }
    }

    public String getPath() {
        return this.path;
    }
    
    // Controlla che il percorso sia una directory o file valido e scrivibile
    public void setPath(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            path = "Not found";
        }if (!dir.canWrite()) {
            path = "Not found";
        }
        
        this.path = path;
        
    }

    public int getBitrate() {
        return bitrate;
    }
    
    //Per settare tutti i tipi di bitrate.
    public void setBitrate(int bitrate) {
        if (bitrate <= 0) {
            bitrate = -1;
        }
        this.bitrate = bitrate;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        File path = new File(outputPath);
        if(!(path.exists())){
            outputPath = this.defaultPath;
        }
        if(!(path.isDirectory())){
            outputPath = this.defaultPath;
        }
        if(!(path.canWrite())){
            outputPath = this.defaultPath;
        }
        this.outputPath = outputPath;
    }

    public String getOutputName() {
        return outputName;
    }

    //Controlla anche che nella directory dove verrà salvato il file 
    //non ci sia un file con lo stesso nome
    public void setOutputName(String outputName) {
        File outFile = new File(this.outputPath, outputName);
        if(!(outFile.exists())){
            outputName = "fileConverter" + this.format;
        }
        if(outputName.isBlank() || outputName.isEmpty()){
            outputName = "fileConverter" + this.format;
        }
        if(this.name.equals(outputName)){
            outputName = "fileConverter" + this.format;
        }
        this.outputName = outputName;
    }
    
    public String getOutputFormat() {
        return outputFormat;
    }

    public void setDefaultPath(String defaultPath){
        System.out.println("");
    }
    
    public String getDefaultPath(){
        return this.defaultPath;
    }
    
    //Metodo specifico per settare il formato in uscita.
    public void setOutputFormat(String outputFormat){
        boolean check = true;
        
        outputFormat = outputFormat.toUpperCase();
        
        String[] allFormats = {"MP3", "FLAC", "WAV", "OGG", "AAC", "M4A"};
            for (String formatCheck : allFormats) {
                if (!(formatCheck.equals(outputFormat))) {
                    check = false;
                }else{
                    check = true;
                    break;
                }
            }
        if(check){
            this.outputFormat = outputFormat;
        }else{
            this.outputFormat = "Invalid format";
        }
        
        
    }

    
}
