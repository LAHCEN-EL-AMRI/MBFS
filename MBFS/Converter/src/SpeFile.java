import java.awt.SystemColor;
import java.awt.TextField;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;

public class SpeFile {    
    private static Scanner s;

    public static void ReadSpeFile() throws IOException {
        File file = new File(Converter.fichier);
        s = new Scanner(file);        
        //--------name file----------
        Converter.namFile = file.getName();
        
        String keyword = s.next();
        
        //---------------------$SPEC_ID:--Sample description------------------------
        if(keyword.equals("$SPEC_ID:")) {
            String strVar1 = "";
            String A1 = "";
            for(int b = 0; b < 50; b++) {
            
                strVar1 = s.next(); 
                if(strVar1.equals("$SPEC_REM:")){keyword = "$SPEC_REM:"; break; }
                A1 = A1 + strVar1 + " ";      
            }
            Converter.SampleDescription = A1;
            strVar1 = "";
        }
        //---------------------$SPEC_REM:--detector description------------------------
        if(keyword.equals("$SPEC_REM:")) {
            
            String strVar1 = "";
            String A1 = "";
            for(int b = 0; b < 100; b++) {
            
                strVar1 = s.next(); 
                
                if(strVar1.equals("$DATE_MEA:")){ break; }
                A1 = A1 + strVar1 + " ";      
            }
            Converter.DetectorDescription = A1; 
            strVar1 = "";
        }
        //---------------------date-----$DATE_MEA:------------------------
                          
        //-------------------DD-MMM-YY--------------                        
        String DD_MMM_YY = s.next();
        Converter.StrDat = DD_MMM_YY;
        //-------------------HH_MM_SS--------------        
        String HH_MM_SS = s.next();
        Converter.StrTime = HH_MM_SS;
        //System.out.println(HH_MM_SS);
        
        
        //------------------------$MEAS_TIM------------------------
        s.next();                            
        String Live_Time = s.next();
        Converter.livetimS = Live_Time; 
        
        String Real_Time = s.next();
        Converter.realtimS = Real_Time;
        //---------dead time--------
        float i4a = Float.parseFloat(Real_Time);
        float i5a = Float.parseFloat(Live_Time);
        float dead = (float) (i4a - i5a) * 100 / i4a;    
        String Dead = Float.toString(dead) + " %";
        Converter.Dead = Dead;              
        //-------------$DATA:-----------------------------------------------
        s.next();     
        
                         //---------offset of data and length of data------- 
        Converter.offsetofdata = s.nextShort();
        Converter.offsetofdataS = Short.toString(Converter.offsetofdata);
        Converter.lengthofdata = s.nextShort();
        Converter.lengthofdataS = Short.toString(Converter.lengthofdata);
                         //---------Data of spectrum-------------------------
         
        Converter.tableauEntier = new int[Converter.lengthofdata - Converter.offsetofdata+1];
        for(int j = 0; j < Converter.lengthofdata - Converter.offsetofdata+1; j++) {
            int data = s.nextInt();
            Converter.tableauEntier[j] = data; 
            //System.out.println("data :  "+Converter.tableauEntier[j] );
        } 
        
        String Ls=System.getProperty("line.separator");
		String dtt,dss,dvv;
		String tss="";				

		      int je=Converter.offsetofdata;
	   for(int j=0;j<Converter.lengthofdata-Converter.offsetofdata+1;j++) {
		  
		  
		    dvv=je+"                   "+Converter.tableauEntier[j]+Ls;
            tss=tss+dvv; 	
            je=je+1;
	   }
	   Converter.textArea_2.setText(tss);	
        
        
        //------ Lecture des valeurs $ROI: --------------------------
        ArrayList<String> roiList = new ArrayList<>();
        while(s.hasNext()) {
            String line = s.next();
            if(line.equals("$ROI:")) {
                for(int i = 0; i < 100; i++) { // nombre maximum de ROI à lire
                    String roiValue = s.next();
                    if(roiValue.startsWith("$")) { // fin de la section ROI
                        break;
                    }
                    roiList.add(roiValue);
                }
                break;
            }
        }

        
        // Affichage du contenu des ROIs dans textArea_ROI
        Converter.textArea_ROI.setText("");
        for (int i = 1; i < roiList.size(); i += 2) {
            if (i + 1 < roiList.size()) {
                String roiValue1 = roiList.get(i);
                String roiValue2 = roiList.get(i + 1);
                Converter.textArea_ROI.append(roiValue1 + " " + roiValue2);

                // Ajouter un saut de ligne uniquement si ce n'est pas la dernière paire
                if (i + 2 < roiList.size()) {
                    Converter.textArea_ROI.append("\n");
                }
            }
        }

        
        //---------$MCA_CAL:------------------------------------------------------        
        for(int j = 0; j < 100; j++) {
            String wordkey = s.next();
            int nbrFctor;
            if(wordkey.equals("$MCA_CAL:")) {
                nbrFctor = s.nextInt();
                if(nbrFctor == 2) {
                    Converter.cf0S = s.next();
                    Converter.cf1S = s.next();
                    Converter.cf2S = "0";
                    j = 100;
                }
                if(nbrFctor == 3) {
                    Converter.cf0S = s.next();
                    Converter.cf1S = s.next();
                    Converter.cf2S = s.next();
                    j = 100;
                }
            }                  
        }    
        //---------$SHAPE_CAL:------------------------------------------------------
        String casKev = s.next();
        if(casKev.equalsIgnoreCase("keV")) {               
            s.next();
            int nbrFctor1 = s.nextInt();
            //System.out.println(nbrFctor1);
            if(nbrFctor1 == 2) {
                Converter.cf0SF = s.next();
                Converter.cf1SF = s.next();
            }
            if(nbrFctor1 == 3) {
                Converter.cf0SF = s.next();
                Converter.cf1SF = s.next();
                s.next();
            }
        } else {
            int nbrFctor1 = s.nextInt();
            //System.out.println(nbrFctor1);
            if(nbrFctor1 == 2) {
                Converter.cf0SF = s.next();
                Converter.cf1SF = s.next();
            }
            if(nbrFctor1 == 3) {
                Converter.cf0SF = s.next();
                Converter.cf1SF = s.next();
                s.next();
            }
        }
        
       
        Converter.lblNewLabel_18.setText("");
    }
    
}
