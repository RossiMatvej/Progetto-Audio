package audioconverter;

import java.io.File;

class Converter {
    private String name;
    private String format;
    private String destination;
    private int bitrate;
    
    // Costruttore di default (con valori predefiniti)
    public Converter() {
        this.name = "Undefined";
        this.format = "MP3";  // formato di default
        this.destination = null;
        this.bitrate = 128; // bitrate di default
    }
    public Converter(String name, String format, String destination, int bitrate){
        this.name = name;
        setFormat(format); //MP3, FLAC, WAV, OGG, AAC, M4A
        this.destination = destination;
        this.bitrate = bitrate;
    }
    
    
    //Ritorna non solo il nome ma il nome più l'estensione.
    public String getNameFormat() {
        setFormat(this.format);
        return this.name+this.format.toLowerCase();
    }
    
    //Ritorna solamente il nome del file.
    public String getName(){
        return name;
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

    //Controlla tutti i formati possibili e trova un formato non valido mette 
    // "Invalid format" al formato.
    // Metodo messo "final" così da togliere la possibilità di fare un Override.
    public final void setFormat(String format) {
        
        format = format.toUpperCase();
        
        boolean check = true;
        
        String[] allFormats = {"MP3", "FLAC", "WAV", "OGG", "AAC", "M4A"};
        for (String formatCheck : allFormats) {
            if (!(format.equals(formatCheck))) {
                check = false;
            }else{
                check = true;
                break;
            }
        }
        
        if(check){
            this.format = format;
        }else{
            this.format = "Invalid format";
        }
    }

    public String getDestination() {
        return destination;
    }
    
    // Controlla che la destinazione sia una directory valida e scrivibile
    public void setDestination(String destination) {
        File dir = new File(destination);
        if (!dir.exists()) {
            this.destination = null;
        } else if (!dir.canWrite()) {
            this.destination = null;
        } else {
            this.destination = destination;
        }
    }

    public int getBitrate() {
        return bitrate;
    }
    
    //Per settare tutti i tipi di bitrate.
    public void setBitrate(int bitrate) {
        if (bitrate <= 0) {
            throw new IllegalArgumentException("Bitrate invalid.");
        }
        this.bitrate = bitrate;
    }
    
    

    
}