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

public class ChnFile {
	
	public static void ChnFile() {
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
	      bb.position(6);
	      char s0 =  (char) (bb.get());
	      String s00 = Character.toString(s0);
	      char s1 =  (char) (bb.get());
	      String s11 = Character.toString(s1);
	      //char s2 =  (char) (bb.get());
	      
	      bb.position(24);
	      char s3 =  (char) (bb.get());
	      String s33 = Character.toString(s3);
	      char s4 =  (char) (bb.get());
	      String s44 = Character.toString(s4);
	      //char s5 =  (char) (bb.get());
	      String s55 = ":";
	      char s6 =  (char) (bb.get());
	      String s66 = Character.toString(s6);
	      char s7 =  (char) (bb.get());
	      String s77 = Character.toString(s7);
	      String s22 = ":";
          Converter.StrTime = s33+s44+s55+s66+s77+s22+s00+s11;
          //System.out.println("starttime   :"+StrTim);
          
        //---------start date--------
          bb.position(16);
	      char sa0 =  (char) (bb.get());
	      String sa00 = Character.toString(sa0);
	      char sa1 =  (char) (bb.get());
	      String sa11 = Character.toString(sa1);
	      char sa2 =  (char) (bb.get());
	      String sa22 = Character.toString(sa2);
	      char sa3 =  (char) (bb.get());
	      String sa33 = Character.toString(sa3);
	      char sa4 =  (char) (bb.get());
	      String sa44 = Character.toString(sa4);
	      char sa5 =  (char) (bb.get());
	      String sa55 = Character.toString(sa5);
	      char sa6 =  (char) (bb.get());
	      String sa66 = Character.toString(sa6);
	      Converter.StrDat = sa00+sa11+sa22+sa33+sa44+sa55+sa66;
	      //System.out.println("strat date   :"+StrDat);
	      
	    //---------Real and live time--------
	      bb.position(8);
	      float realtim =(float)bb.getInt();
	      float i4a=   ((float)(realtim)/50);
	      Converter.realtimS = Float.toString(i4a);
	      float livetim =(float)bb.getInt();
	      float i5a=   ((float)(livetim)/50);
	      Converter.livetimS = Float.toString(i5a);
	      //System.out.println("real time   :"+realtimS);
	      //System.out.println("live time   :"+livetimS );
	      
	    //---------dead time--------
	      float  dead =(float) (i4a-i5a)*100/i4a;	
	      String Dead = Float.toString(dead)+" %";
	      Converter.Dead = Dead;
	     /* bb.position(23);
	      float dead = (float)bb.getFloat();
	      char prc =  (char) (bb.get());
	      String prctg = Character.toString(prc);
	      Converter.Dead = Dead;*/
	     // System.out.println("dead   :"+Dead );
	      
	    //---------offset of data and length of data----------------
	      bb.position(28);
	      Converter.offsetofdata = bb.getShort();
	      Converter.offsetofdataS = Short.toString(Converter.offsetofdata);
	      Converter.lengthofdata = bb.getShort();
	      Converter.lengthofdataS = Short.toString(Converter.lengthofdata);
	      //System.out.println("Converter.offsetofdata   :"+offsetofdataS+"        "+"Converter.lengthofdata   :"+lengthofdataS );
	      
	    //---------Data of spectrum---------------------------      
	      
	      Converter.tableauEntier= new int[Converter.lengthofdata-Converter.offsetofdata];
	      
	      for(int j=0;j<Converter.lengthofdata-Converter.offsetofdata;j++) {
	    	  int data =bb.getInt();
	    	  Converter.tableauEntier[j]=data; 
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
	      
	      
	    //---------coficient of calibration energy --------------------------  
	      bb.position(32+4*(Converter.lengthofdata-Converter.offsetofdata)+4);
	      float cf0 = bb.getFloat();
	      Converter.cf0S = Float.toString(cf0);
	      float cf1 = bb.getFloat();
	      Converter.cf1S = Float.toString(cf1);
	      float cf2 = bb.getFloat();
	      Converter.cf2S = Float.toString(cf2);
	     // String cfS = cf0S+"    "+cf1S+"   "+cf2S;
	     // System.out.println("coficient of calibration energy   :"+cfS );
	      
	    //---------coficient of Peak shape -----------------------------------
	      bb.position(32+4*(Converter.lengthofdata-Converter.offsetofdata)+16);
	      float cfp0 = bb.getFloat();
	      Converter.cfp0S = Float.toString(cfp0);
	      float cfp1 = bb.getFloat();
	      Converter.cfp1S = Float.toString(cfp1);
	      float cfp2 = bb.getFloat();
	      Converter.cfp2S = Float.toString(cfp2);
	    //  String cfpS = Converter.cfp0S+"    "+Converter.cfp1S+"   "+Converter.cfp2S;
	      //System.out.println("coficient of Peak shape   :"+cfpS );
	      
	      //-------Length of detector description--------------
	      bb.position(32+4*(Converter.lengthofdata-Converter.offsetofdata)+256);
	      short ldd =  (short) (bb.get());
	      Converter.lddS = Float.toString(ldd);
	      //System.out.println("Length of detector description : "+lddS);
	      
	    //-----------------------Detector description--------------
	      bb.position(32+4*(Converter.lengthofdata-Converter.offsetofdata)+257);
	      String A ="";
	      for(int b=0;b<63;b++) {
	    	  char B =  (char) (bb.get());     	  
	    	  String  strVar =  Character.toString(B);  
	    	   A = A+ strVar ;  	  
	      }
	      Converter.DetectorDescription =  A;  
	      //System.out.println("Detector description           : "+DetectorDescription);
	      
	    //-----------------------Length of sample description--------------
	      bb.position(32+4*(Converter.lengthofdata-Converter.offsetofdata)+320);
	      short lsd =  (short) (bb.get());
	      Converter.lsdS = Float.toString(lsd);
	      //System.out.println("Length of sample description : "+lsdS);
	      				      
	      
	    //-----------------------Sample description------------------------
	      bb.position(32+4*(Converter.lengthofdata-Converter.offsetofdata)+321);
	      String A1 ="";
	      for(int c=0;c<63;c++) {
	    	  char B1 =  (char) (bb.get()); 	    	
	    	  String  strVar1 =  Character.toString(B1);  
	    	   A1 = A1 + strVar1 ;  	  
	      }
	      Converter.SampleDescription =  A1;
	      Converter.lblNewLabel_18.setText("");
	      //System.out.println("Sample description            : "+SampleDescription);
	      
	    
	     
		
	      
			
	     
	         //---------- peak shape calibration
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
			
			
	    //---------coficient of Efficiency calibration  --------------------------  
	   
			
	
			
			
    //--------------------------------------------------------------------
	
	
	
	}	
}
