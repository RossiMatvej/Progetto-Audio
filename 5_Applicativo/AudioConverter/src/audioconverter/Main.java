package audioconverter;

class Main {
    public static void main(String[] args){
        
        // Verifica se sono stati passati i necessari argomenti
        if (args.length < 6) {
            System.out.println("Default Usage: java Main -input <input-file> -outputformat <output-format> -inputpath <path>");
            System.out.println("Other arguments: -bitrate <bitrate-value> -outputpath <path> -outputname <name-file>");
            return;
        }
        
        // Oggetto Converter (Valori di default)
        Converter converter = new Converter();
        
        String inputFile = null;
        String outputFormat = null;
        int bitrate = converter.getBitrate();
        String outputPath = converter.getDefaultPath();
        String inputPath = null;
        String outputName = converter.getOnlyName() + "." + converter.getOutputFormat();
        
        
        
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i].toLowerCase()) {
                case "-input":
                    if (i + 1 < args.length) {
                        inputFile = args[i + 1];
                        converter.setName(inputFile);
                        converter.setFormat(inputFile);
                    } else {
                        System.out.println("Error: Missing input file.");
                        return;
                    }
                    break;
                case "-outputformat":
                    if (i + 1 < args.length) {
                        outputFormat = args[i + 1].toUpperCase();
                        converter.setOutputFormat(outputFormat);
                    } else {
                        System.out.println("Error: Missing output format.");
                        return;
                    }
                    break;
                case "-bitrate":
                    if (i + 1 < args.length) {
                        try {
                            bitrate = Integer.parseInt(args[i + 1]);
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
                case "-inputpath":
                    if (i + 1 < args.length) {
                        inputPath = args[i + 1];
                        converter.setPath(inputPath);
                    } else {
                        System.out.println("Error: Invalid Input path.");
                        return;
                    }
                    break;
                case "-outputpath":
                    if (i + 1 < args.length) {
                        outputPath = args[i + 1];
                        converter.setOutputPath(outputPath);
                    } else {
                        converter.setOutputPath(outputPath);
                        System.out.println("Output path not set: Using default path --> " + converter.getDefaultPath());
                        return;
                    }
                    break;

                case "-outputname":
                    if (i + 1 < args.length) {
                        outputName = args[i + 1];
                        converter.setOutputName(outputName);
                    } else {
                        System.out.println("Output name not set: Using same file name");
                        return;
                    }
                    break;

                default:
                    System.out.println("Error: Unrecognized argument " + args[i]);
                    return;
            }
        }
        
        //Validazione dei parametri
        Validator.convertAudio(converter);
    }
}