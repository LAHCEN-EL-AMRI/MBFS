import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class ChnFileWriter {

    public static void ChnFileWriter() {
        // Informations à écrire
    	String namFileW = Converter.textField1.getText();
    	String namFileWithoutLastFour = namFileW.substring(0, namFileW.length() - 4);
        String namFile = "example"+ "/"+"Converter_"+namFileWithoutLastFour+Converter.ConvertTo;
        
        
        String StrTimeWithPoints = Converter.textField2.getText();
     // Supprimer les deux points (':') du contenu
        String StrTime0 = StrTimeWithPoints.replace(":", "");
        
        String firstTwo0 = StrTime0.substring(0, 1);
        String firstTwo1 = StrTime0.substring(1, 2);
        String secondTwo0 = StrTime0.substring(2, 3);
        String secondTwo1 = StrTime0.substring(3, 4);
        String thirdTwo0 = StrTime0.substring(4, 5);
        String thirdTwo1 = StrTime0.substring(5, 6);

        
        //System.out.println("Les deux premiers chiffres: " + firstTwo0);
        //System.out.println("Les deux premiers chiffres: " + firstTwo1);
        //System.out.println("Les deux chiffres suivants: " + secondTwo0);
        //System.out.println("Les deux chiffres suivants: " + secondTwo1);
        //System.out.println("Les deux chiffres suivants: " + thirdTwo0);
        //System.out.println("Les deux chiffres suivants: " + thirdTwo1);
       // System.out.println("StrTime binaire écrit avec StrTime " + StrTime);
        String StrDat = Converter.textField3.getText();
        
        

        boolean isMMDDYYYY = false;
        boolean isDDMMMYY = false;

        // Vérification du format MM/DD/YYYY
        String regexMMDDYYYY = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern patternMMDDYYYY = Pattern.compile(regexMMDDYYYY);
        Matcher matcherMMDDYYYY = patternMMDDYYYY.matcher(StrDat);
        isMMDDYYYY = matcherMMDDYYYY.matches();

        // Vérification du format DDMMMYY
        String regexDDMMMYY = "^\\d{2}[A-Z]{3}\\d{2}$";
        Pattern patternDDMMMYY = Pattern.compile(regexDDMMMYY);
        Matcher matcherDDMMMYY = patternDDMMMYY.matcher(StrDat);
        isDDMMMYY = matcherDDMMMYY.matches();

        if (isMMDDYYYY) {
            System.out.println("Le format de la date est MM/DD/YYYY.");
            // Conversion de MM/DD/YYYY en DDMMMYY
            String[] parts = StrDat.split("/");
            String month = parts[0];
            String day = parts[1];
            String year = parts[2].substring(2); // Récupère les deux derniers chiffres de l'année

            // Conversion du mois en abréviation de 3 lettres
            String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
            int monthIndex = Integer.parseInt(month) - 1;
            String monthAbbreviation = months[monthIndex];

            StrDat = day + monthAbbreviation + year;
            System.out.println("La date convertie en format DDMMMYY est : " + StrDat);
        } else if (isDDMMMYY) {
            System.out.println("Le format de la date est DDMMMYY.");
        } else {
            System.out.println("Le format de la date n'est pas reconnu.");
        }

    
        
        
        float realtim = Float.parseFloat(Converter.textField4.getText());
        float livetim = Float.parseFloat(Converter.textField5.getText());
        short offsetofdata = Short.parseShort(Converter.textFieldRange.getText());
        short lengthofdata = Short.parseShort(Converter.textFieldRange1.getText());
        
       
        //-------------------Contenu de txtArea_2---------------------------
        
     // Lire le contenu de textArea_2
        String content = Converter.textArea_2.getText();
        
        // Diviser le contenu en lignes
        String[] lines = content.split("\\n");

        // Initialiser les tableaux pour les deux colonnes
        int[] column1 = new int[lines.length];
        int[] column2 = new int[lines.length];

        // Parcourir chaque ligne pour extraire les deux colonnes
        for (int i = 0; i < lines.length; i++) {
            // Diviser chaque ligne en colonnes
            String[] columns = lines[i].split("\\s+");
            if (columns.length >= 2) {
                try {
                    column1[i] = Integer.parseInt(columns[0]);
                    column2[i] = Integer.parseInt(columns[1]);
                } catch (NumberFormatException e) {
                    System.err.println("Erreur de format de nombre à la ligne " + (i + 1));
                    column1[i] = 0; // Valeur par défaut en cas d'erreur
                    column2[i] = 0; // Valeur par défaut en cas d'erreur
                }
            }
        }
        //-----------------------------------------------------------------
        
        int[] tableauEntier = new int[lengthofdata - offsetofdata];
        for (int i = 0; i < tableauEntier.length; i++) {
        	 tableauEntier[i] = column2[i]; // Utiliser les valeurs de la deuxième colonne
        }
  
        
        
        
        //-----------------------------------------------------------------
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
        
        
        float cfp0, cfp1, cfp2;

        if (Converter.peakShape1.getText().isEmpty() && Converter.peakShape2.getText().isEmpty() && Converter.peakShape3.getText().isEmpty()) {
            cfp0 = 0.0f;
            cfp1 = 0.0f;
            cfp2 = 0.0f;
        } else {
            cfp0 = Float.parseFloat(Converter.peakShape1.getText());
            cfp1 = Float.parseFloat(Converter.peakShape2.getText());
            cfp2 = Float.parseFloat(Converter.peakShape3.getText());
        }
        
        
        
        
        //-----------------------------------------------------------------
        
        
        //short ldd = Short.parseShort(Converter.textFieldLenghtDetector.getText());
        short ldd = (short) Float.parseFloat(Converter.textFieldLenghtDetector.getText());
        String DetectorDescription = Converter.textArea_1.getText();
        
        //short lsd = Short.parseShort(Converter.textFieldLenghtSample.getText());
        short lsd = (short) Float.parseFloat(Converter.textFieldLenghtSample.getText());
        String SampleDescription = Converter.textArea.getText(); 
        
        

     // Calcul de la taille du buffer
        int bufferSize = 32 + 4 * (lengthofdata - offsetofdata) + 4 * 3 + 16 + 256 + 1 + 63 + 1 + 63 + 1;
        ByteBuffer bb = ByteBuffer.allocate(bufferSize);
        bb.order(ByteOrder.LITTLE_ENDIAN);

        // Écriture des données dans le buffer
       
                bb.position(0);   
                bb.putShort((short) -1); // Doit être -1
                bb.putShort((short) 1);  // Numéro de MCA ou numéro de détecteur (exemple)
                bb.putShort((short) 1);  // Numéro de segment (réglé sur 1 dans UMCBI)
                
                bb.position(6);
                bb.put((byte) thirdTwo0.charAt(0)); // Second
                bb.put((byte) thirdTwo1.charAt(0)); // Second
                
                bb.position(8);
                bb.putInt((int) (realtim * 50));
                bb.putInt((int) (livetim * 50));
            

            bb.position(16);
            for (char c : StrDat.toCharArray()) {
                bb.put((byte) c);
            }
            

            bb.position(24);
            bb.put((byte) firstTwo0.charAt(0)); //Hour
            bb.put((byte) firstTwo1.charAt(0)); // Hour
            bb.put((byte) secondTwo0.charAt(0)); // Minute
            bb.put((byte) secondTwo1.charAt(0)); // Minute
            
            
            bb.position(28);
            bb.putShort(offsetofdata);
            bb.putShort(lengthofdata);
            for (int data : tableauEntier) {
                bb.putInt(data);
            }
            
            bb.position(32 + 4 * (lengthofdata - offsetofdata) + 4);
            bb.putFloat(cf0);
            bb.putFloat(cf1);
            bb.putFloat(cf2);
            bb.position(32 + 4 * (lengthofdata - offsetofdata) + 16);
            bb.putFloat(cfp0);
            bb.putFloat(cfp1);
            bb.putFloat(cfp2);
            bb.position(32 + 4 * (lengthofdata - offsetofdata) + 256);
            bb.put((byte) ldd);
            bb.position(32 + 4 * (lengthofdata - offsetofdata) + 257);
            for (char c : DetectorDescription.toCharArray()) {
                bb.put((byte) c);
            }
            bb.position(32 + 4 * (lengthofdata - offsetofdata) + 320);
            bb.put((byte) lsd);
            bb.position(32 + 4 * (lengthofdata - offsetofdata) + 321);
            for (char c : SampleDescription.toCharArray()) {
                bb.put((byte) c);
            }
        

        // Écriture du buffer dans un fichier binaire
        File outputFile = new File(namFile);
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(bb.array());
            Converter.lblNewLabel_18.setText("Binary file written successfully: " + namFile);
            //System.out.println("Fichier binaire écrit avec succès: " + namFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}