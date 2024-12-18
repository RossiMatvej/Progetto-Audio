package audioconverter;

class Main {
    public static void main(String[] args){
        
        // Verifica se sono stati passati i minimi argomenti
        if (args.length < 6) {
            System.out.println("""
                               Default Usage: java Main -input <input-file> -outputformat <output-format> -path "<path>"
                               IF YOU HAVE WHITE SPACE IN YOUR PATH OR IN YOUR FILE REMEMBER TO USE \u001B[33mQUOTATION MARKS!\033[0m""");
            System.out.println("Other arguments: -bitrate <bitrate-value> -overwrite <true|false> -outputpath \"<path>\" -outputname <name-file>");
            return;
        }
        
        // Oggetto Converter (Valori di default)
        Converter converter = new Converter();
        
        //Check per non settare 2 volte gli stessi parametri.
        boolean checkOutName = true;
        boolean checkOutPath = true;
        
        //Args
        String inputFile;
        String outputFormat;
        int bitrate;
        String outputPath = converter.getDefaultPath();
        String inputPath;
        String outputName = converter.getOutputName();
        boolean overwrite = false;
        
        
        
        for (int i = 0; i < args.length; i += 2) {
            String key = args[i].toLowerCase();
            
            String value = "";
            
            if (i + 1 < args.length) {
                if (args[i + 1].startsWith("\"")) {
                    //Uso StringBuilder per efficenza.
                    StringBuilder sb = new StringBuilder(args[i + 1]);

                    //Controllo fine del percorso.
                    while (i + 1 < args.length && !args[i + 1].endsWith("\"")) {
                        i++;
                        sb.append(" ").append(args[i + 1]);
                    }
                    value = sb.toString().replace("\"", ""); // Rimuovo le virgolette
                } else {
                    value = args[i + 1];
                }
            }
            
            switch (key) {
                case "-input":
                    if (i + 1 < args.length) {
                        inputFile = value;
                        converter.setName(inputFile);
                        converter.setFormat(inputFile);
                    } else {
                        System.out.println("Error: Missing input file.");
                        return;
                    }
                    break;
                case "-outputformat":
                    if (i + 1 < args.length) {
                        outputFormat = value.toUpperCase();
                        converter.setOutputFormat(outputFormat);
                    } else {
                        System.out.println("Error: Missing output format.");
                        return;
                    }
                    break;
                case "-bitrate":
                    if (i + 1 < args.length) {
                        try {
                            bitrate = Integer.parseInt(value);
                            converter.setBitrate(bitrate);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid bitrate value.");
                            return;
                        }
                    } else {
                        System.out.println("Bitrate not set: Using default bitrate --> 128.0");
                        return;
                    }
                    break;
                case "-path":
                    if (i + 1 < args.length) {
                        inputPath = value;
                        converter.setPath(inputPath);
                    } else {
                        System.out.println("Error: Invalid Input path.");
                        return;
                    }
                    break;
                case "-outputpath":
                    if (i + 1 < args.length) {
                        outputPath = value;
                        converter.setOutputPath(outputPath);
                        checkOutPath = false;
                    } else {
                        converter.setOutputPath(outputPath);
                        System.out.println("Output path not set: Using default path --> " + converter.getDefaultPath());
                        return;
                    }
                    break;

                case "-outputname":
                    if (i + 1 < args.length) {
                        outputName = value;
                        converter.setOutputName(outputName,overwrite);
                        checkOutName = false;
                    } else {
                        System.out.println("Output name not set: Using same file name");
                        return;
                    }
                    break;
                case "-overwrite":
                    if(i + 1 < args.length){
                        try{
                            overwrite = Boolean.parseBoolean(value);
                        }catch(Exception ex){
                            System.out.println("Overwrite value not valid: Using default value \"false\"");
                        }
                    }else{
                        System.out.println("Overwrite value not set: Using default value \"false\"");
                        return;
                    }
                    break;
                default:
                    System.out.println("Error: Unrecognized argument " + args[i]);
                    return;
            }
        }
        //In ogni caso setto outputName e outputPath per evitare problemi di controlli.
        if(checkOutPath){
            converter.setOutputPath(outputPath);
        }
        if(checkOutName){
            converter.setOutputName(outputName, overwrite);
        }
        
        
        
        
        
        //Validazione dei parametri
        Validator.convertAudio(converter, overwrite);
    }
}