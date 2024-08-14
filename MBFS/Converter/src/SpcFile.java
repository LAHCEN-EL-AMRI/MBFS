import java.awt.SystemColor;
import java.awt.TextField;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.swing.JLabel;

public class SpcFile {
	
	public static void ReadSpcFile() {
	//---------------Info extracted from spectrum file-------------------
	File file = new File(Converter.fichier);
	  FileInputStream fis = null;
	try {
		fis = new FileInputStream(file);
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  byte [] arr = new byte[(int)file.length()];
	  try {
		fis.read(arr);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	  ByteBuffer bb = ByteBuffer.wrap(arr);
	      bb.order(ByteOrder.LITTLE_ENDIAN);
	      //--------name file----------
	       Converter.namFile = file.getName();
	      //System.out.println("naaaaaaaame  :  "+namFile);
	      //---------start time--------
	       
	     //-------------------Start time of sample--------------
	       
	     //-------------------HH:MM:SS--------------
		      bb.position(128+16+12);
		      String A3 ="";
		      for(int c=0;c<10;c++) {
		    	  char B3 =  (char) (bb.get()); 	    	
		    	  String  strVar1 =  Character.toString(B3);  
		    	   A3 = A3 + strVar1 ;  	  
		      }
		      String  HH_MM_SS =  A3;
		      Converter.StrTime = HH_MM_SS;
		     // System.out.println("HH:MM:SS            : "+HH_MM_SS);
          
        //---------start date--------
		      
		//-------------------DD-MMM-YY*--------------
		      bb.position(128+16);
		      String A2 ="";
		      for(int c=0;c<12;c++) {
		    	  char B2 =  (char) (bb.get()); 	    	
		    	  String  strVar1 =  Character.toString(B2);  
		    	   A2 = A2 + strVar1 ;  	  
		      }
		      String  DD_MMM_YY =  A2;
		      Converter.StrDat = DD_MMM_YY;
		      //System.out.println("DD-MMM-YY*            : "+DD_MMM_YY);
	      
	    //---------Real time-----------------------------------------
		      bb.position(128+16+12+10+10);
		      String A5 ="";
		      for(int c=0;c<10;c++) {
		    	  char B5 =  (char) (bb.get()); 	    	
		    	  String  strVar1 =  Character.toString(B5);  
		    	   A5 = A5 + strVar1 ;  	  
		      }
		      String  Real_Time =  A5;
		      Converter.realtimS = Real_Time;
		     // System.out.println("Real Time            : "+Real_Time);
	  //---------Live time----------------------------------------------		      
		      bb.position(128+16+12+10);
		      String A4 ="";
		      for(int c=0;c<10;c++) {
		    	  char B4 =  (char) (bb.get()); 	    	
		    	  String  strVar1 =  Character.toString(B4);  
		    	   A4 = A4 + strVar1 ;  	  
		      }
		      String  Live_Time =  A4;
		      //System.out.println("Live Time            : "+Live_Time);
		      Converter.livetimS = Live_Time;
	      
	    //---------dead time--------
		     float i4a = Float.parseFloat(Real_Time);
		     float i5a = Float.parseFloat(Live_Time);
	         float  dead =(float) (i4a-i5a)*100/i4a;	
	         String Dead = Float.toString(dead)+" %";
	         Converter.Dead = Dead;
	     
	      
	    //---------offset of data and length of data--------
	      bb.position(64);
	      Converter.lengthofdata = bb.getShort();
	      Converter.lengthofdataS = Short.toString(Converter.lengthofdata);   // Je dois verifier
		  bb.position(66);
		  Converter.offsetofdata = bb.getShort();
		  Converter.offsetofdataS = Short.toString(Converter.offsetofdata); // Je dois verifier
	      
	    //---------Data of SPECTRUM--------------------------------------------------------------     
		  bb.position(128+16+12+10+10+42+12+8+10+8+128+128+8+12+12+8+1112); 
	      Converter.tableauEntier= new int[Converter.lengthofdata-Converter.offsetofdata];
	      
	      for(int j=0;j<Converter.lengthofdata-Converter.offsetofdata;j++) {
	    	  float data = bb.getFloat();
	          int dataa = (int) data;
	    	  Converter.tableauEntier[j]=dataa; 
	    	  //System.out.println("data :  "+Converter.tableauEntier[j] );
	    	  
	      }
	      
	      String Ls=System.getProperty("line.separator");
			String dtt,dss,dvv;
			String tss="";				

			      int je=Converter.offsetofdata;
		   for(int j=0;j<Converter.lengthofdata-Converter.offsetofdata;j++) {
			  
			  
			  dvv=je+"                   "+Converter.tableauEntier[j]+Ls;
	            tss=tss+dvv; 	
	            je=je+1;
		   }
		   Converter.textArea_2.setText(tss);	
	      
	  //-------------------First, Second, and Third Energy calibration-----------------------
	      bb.position(128+16+12+10+10+42+12+8+10+8+128+128+8+12); 	      
	      float cf0 = bb.getFloat();
	      Converter.cf0S = Float.toString(cf0);    
	      float cf1 = bb.getFloat();
	      Converter.cf1S = Float.toString(cf1);
	      float cf2 = bb.getFloat();
	      Converter.cf2S = Float.toString(cf2);
	     // String cfS = cf0S+"    "+cf1S+"   "+cf2S;
	     // System.out.println("coficient of calibration energy   :"+cfS );
		      
	 //-------------------First, Second, and Third FWHM----------------------------------
		      bb.position(128+16+12+10+10+42+12+8+10+8+128+128+8+12+12); 
	    	  
	    	  float cf0F = bb.getFloat();
		      Converter.cf0SF = Float.toString(cf0F);    
		      float cf1F = bb.getFloat();
		      Converter.cf1SF = Float.toString(cf1F);
		      
	      
	    //-----------------------Detector description--------------	    	
		      bb.position(128+16+12+10+10+42+12+8+10+8+128);
		      String A12 ="";
		      for(int c=0;c<64;c++) {
		    	  char B12 =  (char) (bb.get()); 	    	
		    	  String  strVar1 =  Character.toString(B12);  
		    	   A12 = A12 + strVar1 ;  	  
		      }
		      String  Detector_Description0 =  A12;
		      //System.out.println("Detector Description 0            : "+Detector_Description0);
		      
		      String A13 ="";
		      for(int c=0;c<64;c++) {
		    	  char B13 =  (char) (bb.get()); 	    	
		    	  String  strVar1 =  Character.toString(B13);  
		    	   A13 = A13 + strVar1 ;  	  
		      }
		      String  Detector_Description1 =  A13;
		     // System.out.println("Detector Description 1            : "+Detector_Description1);
	    	  
		      Converter.DetectorDescription = "["+ Detector_Description0+"]"+"    "+"["+ Detector_Description1+"]";
	    
	    //-----------------------Sample description------------------------
		      bb.position(128+16+12+10+10+42+12+8+10+8);
		      String A10 ="";
		      for(int c=0;c<64;c++) {
		    	  char B10 =  (char) (bb.get()); 	    	
		    	  String  strVar1 =  Character.toString(B10);  
		    	   A10 = A10 + strVar1 ;  	  
		      }
		      String  Sample_Description0 =  A10;
		      //System.out.println("Sample Description 0            : "+Sample_Description0);
		      
		      String A11 ="";
		      for(int c=0;c<64;c++) {
		    	  char B11 =  (char) (bb.get()); 	    	
		    	  String  strVar1 =  Character.toString(B11);  
		    	   A11 = A11 + strVar1 ;  	  
		      }
		      String  Sample_Description1 =  A11;
		      //System.out.println("Sample Description 1           : "+Sample_Description1);
		      Converter.SampleDescription =  "["+ Sample_Description0+"]"+"    "+"["+ Sample_Description1+"]";
		     
		      Converter.lblNewLabel_18.setText("");
	   
		      /*	
		      Converter.Vshapecalib.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent arg0) {
						Converter.pan.repaint();								
						  //Vshapecalib.setText(Dead);								
					}
				});	
		      Converter.Vshapecalib.setBounds(450, 50, 150, 17);
		      Converter.Vshapecalib.setForeground(SystemColor.inactiveCaptionText);
		      Converter.pan.add(Converter.Vshapecalib);
				*/
				
			
				
				
				
				//Converter.lddS = "";
				
				
				//Converter.lsdS = "";
				
			
	
	
	}	
}
