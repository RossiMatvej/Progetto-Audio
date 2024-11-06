package audioconverter;

class Converter {
    private String name;
    private String format;
    private String destination;
    private double quality;
    
    //Possibilità di istanziare un costruttore vuoto X TEST
    public Converter(){
        
    }
    
    public Converter(String name, String format, String destination, double quality){
        this.name = name;
        this.format = format; //MP3, FLAC, WAV, OGG, AAC, M4A
        this.destination = destination;
        this.quality = quality;
    }
    
    
    //Ritorna non solo il nome ma il nome più l'estensione.
    public String getNameFormat() {
        setFormat(this.format);
        return this.name+this.format;
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
    public void setFormat(String format) {
        String[] allFormats = {"MP3", "FLAC", "WAV", "OGG", "AAC", "M4A"};
        for (String formatCheck : allFormats) {
            if (!(format.equals(formatCheck))) {
                format = "Invalid format";
                break;
            }
        }
        this.format = format;
    }

    public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getQuality() {
        return quality;
    }
    
    //Importante: trovare tutti i tipi di Bitrate possibili.
    public void setQuality(double quality) {
        this.quality = quality;
    }
    
    

    
}