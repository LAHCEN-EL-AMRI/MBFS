import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class SpeFileWriter {

    public static void WriteSpeFile() {
    	
    	
    	
    	//---------lire extention de file-------
    	String extension = "";
    	String Filenam = Converter.textField1.getText();

    	int lastIndexOfDot = Filenam.lastIndexOf('.');
    	if (lastIndexOfDot > 0 && lastIndexOfDot < Filenam.length() - 1) {
    	    extension = Filenam.substring(lastIndexOfDot + 1);
    	}

    	System.out.println("Extension du fichier : " + extension);
    	
    	//-----------------------------------------
    	
        // Informations à écrire
        String namFileW = Converter.textField1.getText();
        String namFileWithoutLastFour = namFileW.substring(0, namFileW.length() - 4);
        String namFile = "example" + "/" + "Converter_" + namFileWithoutLastFour + Converter.ConvertTo;
        
        
        
        

        String strDat = Converter.textField3.getText();
        try {
            // Définir le format de la chaîne d'entrée
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMMyy", Locale.ENGLISH);
            
            // Définir le format de sortie souhaité
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            // Parser la chaîne d'entrée en LocalDate
            LocalDate date = LocalDate.parse(strDat, inputFormatter);

            // Convertir la date en chaîne avec le format de sortie
            String formattedDate = date.format(outputFormatter);

            // Afficher le résultat
            System.out.println("Date formatée : " + formattedDate);
            strDat = formattedDate;

        } catch (DateTimeParseException e) {
            System.err.println("Erreur de format de date : " + e.getMessage());
        }
        
        String strTime = Converter.textField2.getText();
        float realTim = Float.parseFloat(Converter.textField4.getText());
        float liveTim = Float.parseFloat(Converter.textField5.getText());
        short offsetOfData = Short.parseShort(Converter.textFieldRange.getText());
        short lengthOfData = Short.parseShort(Converter.textFieldRange1.getText());
        String sampleDescription = Converter.textArea.getText();
        String detectorDescription = Converter.textArea_1.getText();

        float cf0, cf1, cf2;

        if (Converter.textFieldE.getText().isEmpty() && Converter.textFieldE1.getText().isEmpty() && Converter.textFieldE2.getText().isEmpty()) {
            cf0 = 0.0f;
            cf1 = 0.0f;
            cf2 = 0.0f;
        } else {
            cf0 = Float.parseFloat(Converter.textFieldE.getText());
            cf1 = Float.parseFloat(Converter.textFieldE1.getText());
            cf2 = Float.parseFloat(Converter.textFieldE2.getText());
        }

        float cfp0, cfp1;

        if (Converter.textFieldFWHM1.getText().isEmpty() && Converter.textFieldFWHM2.getText().isEmpty()) {
            cfp0 = 0.0f;
            cfp1 = 0.0f;
        } else {
            cfp0 = Float.parseFloat(Converter.textFieldFWHM1.getText());
            cfp1 = Float.parseFloat(Converter.textFieldFWHM2.getText());
        }

        // Lire le contenu de textArea_2
        String content = Converter.textArea_2.getText();
        String[] lines = content.split("\\n");
        int[] column2 = new int[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] columns = lines[i].split("\\s+");
            if (columns.length >= 2) {
                try {
                    column2[i] = Integer.parseInt(columns[1]);
                } catch (NumberFormatException e) {
                    System.err.println("Erreur de format de nombre à la ligne " + (i + 1));
                    column2[i] = 0; // Valeur par défaut en cas d'erreur
                }
            }
        }
       
        int lon;
        if((extension.equals("Chn")||extension.equals("chn")||extension.equals("CHN"))) {lon= lengthOfData - offsetOfData;}
        else{lon= lengthOfData - offsetOfData+1;}
        
        int[] tableauEntier = new int[lon];
        for (int i = 0; i < tableauEntier.length; i++) {
            tableauEntier[i] = column2[i];
        }
        

        
        
        // Lire le contenu de textArea_ROI
        String roiContent = Converter.textArea_ROI.getText();
        String[] roiLines = roiContent.split("\\n");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namFile, StandardCharsets.US_ASCII))) {
            // Écriture du fichier en format .spe

            // Écrire $SPEC_ID
            writer.write("$SPEC_ID:\n");
            writer.write(sampleDescription + "\n");

            // Écrire $SPEC_REM
            writer.write("$SPEC_REM:\n");
            writer.write(detectorDescription + "\n");

            // Écrire la date et l'heure
            writer.write("$DATE_MEA:\n");
            writer.write(strDat + " " + strTime + "\n");

            // Écrire $MEAS_TIM
            writer.write("$MEAS_TIM:\n");
            writer.write(String.format(Locale.US, "%.2f %.2f\n", liveTim, realTim));

            // Écrire $DATA
            writer.write("$DATA:\n");
            writer.write(offsetOfData + " " + lengthOfData + "\n");

            // Écrire les données du spectre
            for (int data : tableauEntier) {
                writer.write("       "+ data + "\n");
            }
            
            if((extension.equals("Chn")||extension.equals("chn")||extension.equals("CHN") && (Converter.eviterErreurTableau.equals("plusOuMoins")))) {
            	writer.write("       "+ 0 + "\n");}

            // Écrire $ROI
            writer.write("$ROI:\n");
            writer.write(roiLines.length + "\n"); // Nombre de ROIs

            // Écrire chaque ROI
            for (String roiLine : roiLines) {
                writer.write(roiLine + "\n");
            }

            // Écrire $ENER_FIT
            writer.write("$ENER_FIT:\n");
            writer.write(String.format(Locale.US, "%f %f\n", cf0, cf1));

            // Écrire $MCA_CAL
            writer.write("$MCA_CAL:\n");
            writer.write("3\n" + String.format(Locale.US, "%f %f %f\n", cf0, cf1, cf2));

            // Écrire $SHAPE_CAL
            writer.write("$SHAPE_CAL:\n");
            writer.write("2\n" + String.format(Locale.US, "%f %f\n", cfp0, cfp1));

            System.out.println("SPE file written successfully: " + namFile);
            Converter.lblNewLabel_18.setText("SPE file written successfully: " + namFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
