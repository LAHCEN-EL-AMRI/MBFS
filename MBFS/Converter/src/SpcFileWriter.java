import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SpcFileWriter {

    public static void writeSpcFile() {
        String namFileW = Converter.textField1.getText();
        String namFileWithoutLastFour = namFileW.substring(0, namFileW.length() - 4);
        String namFile = "example" + "/" + "Converter_" + namFileWithoutLastFour + Converter.ConvertTo;
        
        String HH_MM_SS = Converter.textField2.getText();
        String DD_MMM_YY = Converter.textField3.getText();
        String Real_Time = Converter.textField4.getText();
        String Live_Time = Converter.textField5.getText();
        String Detector_Description0 = Converter.textArea_1.getText();        
        String Sample_Description0 = Converter.textArea.getText();        
        int lengthofdata = Integer.parseInt(Converter.textFieldRange1.getText());
        int offsetofdata = Integer.parseInt(Converter.textFieldRange.getText());
      
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

        int[] tableauEntier = new int[lengthofdata - offsetofdata];
        for (int i = 0; i < tableauEntier.length; i++) {
            tableauEntier[i] = column2[i];
        }
        
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
        
        float cf0F, cf1F;
        if (Converter.textFieldFWHM1.getText().isEmpty() && Converter.textFieldFWHM2.getText().isEmpty()) {
            cf0F = 0.0f;
            cf1F = 0.0f;
        } else {
            cf0F = Float.parseFloat(Converter.textFieldFWHM1.getText());
            cf1F = Float.parseFloat(Converter.textFieldFWHM2.getText());
        }

        // Calcul de la taille du ByteBuffer
        int bufferSize = 4096 + 9000; // Taille de base
        bufferSize += HH_MM_SS.length();
        bufferSize += DD_MMM_YY.length();
        bufferSize += Real_Time.length();
        bufferSize += Live_Time.length();
        bufferSize += 4; // lengthofdata (int) -> 4 bytes
        bufferSize += 4; // offsetofdata (int) -> 4 bytes
        bufferSize += (lengthofdata - offsetofdata) * 4; // tableauEntier (int array) -> 4 bytes par int
        bufferSize += 3 * 4; // cf0, cf1, cf2 (float) -> 3 * 4 bytes
        bufferSize += 2 * 4; // cf0F, cf1F (float) -> 2 * 4 bytes
        bufferSize += Detector_Description0.length();
        bufferSize += Sample_Description0.length();

        // Création d'un fichier .spc
        File file = new File(namFile);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ByteBuffer bb = ByteBuffer.allocate(bufferSize);
            bb.order(ByteOrder.LITTLE_ENDIAN);

            // Start time (HH:MM:SS)
            bb.position(128 + 16 + 12);
            for (char c : HH_MM_SS.toCharArray()) {
                bb.put((byte) c);
            }

            // Start date (DD-MMM-YY)
            bb.position(128 + 16);
            for (char c : DD_MMM_YY.toCharArray()) {
                bb.put((byte) c);
            }

            // Real time
            bb.position(128 + 16 + 12 + 10 + 10);
            for (char c : Real_Time.toCharArray()) {
                bb.put((byte) c);
            }

            // Live time
            bb.position(128 + 16 + 12 + 10);
            for (char c : Live_Time.toCharArray()) {
                bb.put((byte) c);
            }

            // Longueur et offset des données
            bb.position(64);
            bb.putShort((short) lengthofdata);
            bb.position(66);
            bb.putShort((short) offsetofdata);

            // Données de spectre
            bb.position(128 + 16 + 12 + 10 + 10 + 42 + 12 + 8 + 10 + 8 + 128 + 128 + 8 + 12 + 12 + 8 + 1112);
            for (int data : tableauEntier) {
                bb.putFloat(data);
            }

            // Calibration d'énergie
            bb.position(128 + 16 + 12 + 10 + 10 + 42 + 12 + 8 + 10 + 8 + 128 + 128 + 8 + 12);
            bb.putFloat(cf0);
            bb.putFloat(cf1);
            bb.putFloat(cf2);

            // FWHM
            bb.position(128 + 16 + 12 + 10 + 10 + 42 + 12 + 8 + 10 + 8 + 128 + 128 + 8 + 12 + 12);
            bb.putFloat(cf0F);
            bb.putFloat(cf1F);

            // Description du détecteur
            bb.position(128 + 16 + 12 + 10 + 10 + 42 + 12 + 8 + 10 + 8 + 128);
            for (char c : Detector_Description0.toCharArray()) {
                bb.put((byte) c);
            }
            
            // Description de l'échantillon
            bb.position(128 + 16 + 12 + 10 + 10 + 42 + 12 + 8 + 10 + 8);
            for (char c : Sample_Description0.toCharArray()) {
                bb.put((byte) c);
            }

            // Écriture du ByteBuffer dans le fichier
            fos.write(bb.array());
            Converter.lblNewLabel_18.setText("SPE file written successfully: " + namFile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
