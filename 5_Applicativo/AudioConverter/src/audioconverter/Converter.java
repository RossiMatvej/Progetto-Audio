package audioconverter;

class Converter {
    private String name;
    private String format;
    private String destination;
    private double quality;
    
    public Converter(String name, String format, String destination, double quality){
        this.name = name;
        this.format = format; //MP3, FLAC, WAV, OGG, AAC, M4A
        this.destination = destination;
        this.quality = quality;
    }
    
    public Converter(){
        
    }

    public String getName() {
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

    public void setFormat(String format) {
        String[] allFormats = {"MP3", "FLAC", "WAV", "OGG", "AAC", "M4A"};
        for(int i = 0; i < allFormats.length;i++){
            if(!(format.equals(allFormats[i]))){
                format = "";
            }
        }
        this.format = format;
    }

    public String getDestination() {
        return destination;
    }

    //Path
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }
    
    

    
}