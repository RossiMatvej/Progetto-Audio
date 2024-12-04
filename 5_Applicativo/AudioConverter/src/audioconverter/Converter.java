package audioconverter;

import java.io.File;

class Converter {
    private String name; //Nome completo del file + estensione (ES. file.mp3)
    private String format; // Formato preso in base al nome (ES. "MP3")
    private String path;
    private int bitrate;
    private int outputBitrate;
    private String outputPath;
    private String outputName;
    private String outputFormat;

    
    
    // Costruttore di default (con valori predefiniti)
    public Converter() {
        this.name = "file.mp3";
        this.format = "MP3";  // formato di default
        this.path = null;
        this.bitrate = 128; // bitrate di default
        this.outputBitrate = this.bitrate;
        this.outputPath = this.path;
        this.outputName = this.name;
        this.outputFormat = this.format;
    }
    
    public Converter(String name, String format, String path, int bitrate, int outputBitrate ,String outputPath, String outputName, String outputFormat){
        this.name = name;
        this.format = format;
        this.path = path;
        this.bitrate = bitrate;
        this.outputBitrate = outputBitrate;
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
            String[] splitName = this.name.split(".");
            return splitName[0];
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
        setName(name);
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
            path = null;
        }if (!dir.canWrite()) {
            path = null;
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
    
    public double getOutputBitrate() {
        return outputBitrate;
    }

    public void setOutputBitrate(int outputBitrate) {
        if(outputBitrate <= 0){
            bitrate = -1;
        }
        this.outputBitrate = outputBitrate;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        File path = new File(outputPath);
        if(!(path.exists())){
            outputPath = null;
        }
        if(!(path.isDirectory())){
            outputPath = null;
        }
        if(!(path.canWrite())){
            outputPath = null;
        }
        this.outputPath = outputPath;
    }

    public String getOutputName() {
        return outputName;
    }

    public void setOutputName(String outputName) {
        File outFile = new File(outputName);
        this.outputName = outputName;
    }
    
    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    
}