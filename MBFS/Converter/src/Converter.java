import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.TextArea;
import java.awt.TextField;

public class Converter extends JFrame {

	 static final long serialVersionUID = 1L;
	 static JPanel panel;
	 static TextField textField1;
	 static TextField textField2;
	 static TextField textField3;
	 static TextField textField4;
	 static TextField textField5;
	 static TextField textField6;
	 static TextField textFieldE;
	 static TextField textFieldE1;
	 static TextField textFieldE2;
	 static TextField textFieldE3;
	 static TextArea textArea_1;
	 static TextArea textArea_ROI;
	 static TextArea textArea;
	 static TextField textFieldRange ;
	 static TextField textFieldRange1 ;
	 static TextField textFieldLenghtDetector;
	 static TextField textFieldLenghtSample;
	 static TextField textFieldFWHM1;
	 static TextField textFieldFWHM2;
	 static TextField peakShape1;
	 static TextField peakShape2;
	 static TextField peakShape3;
	 static TextArea textArea_2;
	 static JLabel lblNewLabel_18 ;
	 
	 static int[] tableauEntier;
	 static short offsetofdata;
	 static short lengthofdata;
	 static JPanel contentPane;
	 static String fichier, fichie;
	 static String namFile,StrTime,StrDat,realtimS,livetimS,Dead,offsetofdataS,lengthofdataS,DetectorDescriptionEffi,cf0S,cf1S,cf2S,cf0SF,cf1SF,cfp0S,cfp1S,cfp2S,lddS,DetectorDescription,lsdS,SampleDescription;

	 static String ConvertTo,eviterErreurTableau;
	 
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Converter frame = new Converter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
			
	}
	
	

	/**
	 * Create the frame.
	 */
	public Converter() {
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("icons\\image.png"));
		setTitle("  MBFS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1017, 676);	
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		textArea_2 = new TextArea();
		
		JMenu menu = new JMenu("File       ");
		menuBar.add(menu);
		JMenuItem mntmOuvrir = new JMenuItem("Open");
		mntmOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser dialogue = new JFileChooser("example");					
				dialogue.setAcceptAllFileFilterUsed(false); 
				dialogue.setDialogTitle("Select file ");
				
				FileNameExtensionFilter restrict = new FileNameExtensionFilter(".Chn","Chn");
				FileNameExtensionFilter restrict1 = new FileNameExtensionFilter(".Spc","Spc");
				FileNameExtensionFilter restrict2 = new FileNameExtensionFilter(".Spe","Spe");
				FileNameExtensionFilter restrict3 = new FileNameExtensionFilter(".dat","dat");
				FileNameExtensionFilter restrict4 = new FileNameExtensionFilter(".txt","txt");
				dialogue.addChoosableFileFilter(restrict);
				dialogue.addChoosableFileFilter(restrict1);
				dialogue.addChoosableFileFilter(restrict2);
				dialogue.addChoosableFileFilter(restrict3);
				dialogue.addChoosableFileFilter(restrict4);
				if (dialogue.showOpenDialog(null) ==  JFileChooser.APPROVE_OPTION) {					
					fichie = dialogue.getSelectedFile().getAbsolutePath();
					
				}
				
                   fichier = fichie;
				
				//---------------read-input-file-Chn----------------------------------------------
				String fileExtension = fichier.substring(fichier.lastIndexOf("."));
				if(fileExtension.equals(".Chn")||fileExtension.equals(".chn")||fileExtension.equals(".CHN")) {ChnFile.ChnFile();}
				if(fileExtension.equals(".Spc")||fileExtension.equals(".spc")||fileExtension.equals(".SPC")) {SpcFile.ReadSpcFile();}
				if(fileExtension.equals(".Spe")||fileExtension.equals(".spe")||fileExtension.equals(".SPE")) {try {SpeFile.ReadSpeFile();} catch (IOException e1) {e1.printStackTrace();}}
				
				
				//__________________________________________________________________
        		if(fichier==null){JOptionPane.showMessageDialog(null, "enter spectrum file");}
				else{
				
					    		
				}	
        	//__________________________________________________________________	
        		
        		
        		Converter.textField1.setText(Converter.namFile);
        		Converter.textField2.setText(Converter.StrTime);  if(Converter.textField2.getText().isEmpty()) {Converter.textField2.setText("0.0");}
        		Converter.textField3.setText(Converter.StrDat);   if(Converter.textField3.getText().isEmpty()) {Converter.textField3.setText("0.0");}
        		Converter.textField4.setText(Converter.realtimS); if(Converter.textField4.getText().isEmpty()) {Converter.textField4.setText("0.0");}	
        		Converter.textField5.setText(Converter.livetimS); if(Converter.textField5.getText().isEmpty()) {Converter.textField5.setText("0.0");}
        		Converter.textField6.setText(Converter.Dead);     if(Converter.textField6.getText().isEmpty()) {Converter.textField6.setText("0.0");}
        		Converter.textFieldE.setText(Converter.cf0S);     if(Converter.textFieldE.getText().isEmpty()) {Converter.textFieldE.setText("0.0");}
        		Converter.textFieldE1.setText(Converter.cf1S);    if(Converter.textFieldE1.getText().isEmpty()) {Converter.textFieldE1.setText("0.0");}
        		Converter.textFieldE2.setText(Converter.cf2S);    if(Converter.textFieldE2.getText().isEmpty()) {Converter.textFieldE2.setText("0.0");}
        		Converter.textArea_1.setText(Converter.DetectorDescription);
        		Converter.textArea.setText(Converter.SampleDescription);	
        		Converter.textFieldRange.setText(Converter.offsetofdataS ); if(Converter.textFieldRange.getText().isEmpty()) {Converter.textFieldRange.setText("0.0");}
        		Converter.textFieldRange1.setText(Converter.lengthofdataS );if(Converter.textFieldRange1.getText().isEmpty()) {Converter.textFieldRange1.setText("0.0");}
        		Converter.textFieldLenghtDetector.setText(Converter.lddS);  if(Converter.textFieldLenghtDetector.getText().isEmpty()) {Converter.textFieldLenghtDetector.setText("0.0");}
        		Converter.textFieldLenghtSample.setText(Converter.lsdS);    if(Converter.textFieldLenghtSample.getText().isEmpty()) {Converter.textFieldLenghtSample.setText("0.0");}	
        		
        		
        		//----------------for windows of FWHM calibration------------------------------------------------------------------------
        		Converter.textFieldFWHM1.setText(Converter.cf0SF); if(Converter.textFieldFWHM1.getText().isEmpty()) {Converter.textFieldFWHM1.setText("0.0");}
        		Converter.textFieldFWHM2.setText(Converter.cf1SF); if(Converter.textFieldFWHM2.getText().isEmpty()) {Converter.textFieldFWHM2.setText("0.0");}
        		Converter.peakShape1.setText(Converter.cfp0S);     if(Converter.peakShape1.getText().isEmpty()) {Converter.peakShape1.setText("0.0");}
        		Converter.peakShape2.setText(Converter.cfp1S);     if(Converter.peakShape2.getText().isEmpty()) {Converter.peakShape2.setText("0.0");}
        		Converter.peakShape3.setText(Converter.cfp2S);     if(Converter.peakShape3.getText().isEmpty()) {Converter.peakShape3.setText("0.0");}
        		
        		//----------------------videz les champs----------------------------------------------------------------------------------
        		Converter.namFile ="";	
        		Converter.StrTime="0.0";
        		Converter.StrDat="0.0";
        		Converter.realtimS="0.0";
        		Converter.livetimS="0.0";
        		Converter.Dead="0.0";
        		Converter.cf0S="0.0";
        		Converter.cf1S="0.0";
        		Converter.cf2S="0.0";
        		Converter.DetectorDescription="";
        		Converter.SampleDescription="";
        		Converter.offsetofdataS="0.0";
        		Converter.lengthofdataS="0.0";
        		Converter.lddS="0.0";
        		Converter.lsdS="0.0";
        		Converter.cf0SF="0.0";
        		Converter.cf1SF="0.0";
        		Converter.cfp0S="0.0";
        		Converter.cfp1S="0.0";
        		Converter.cfp2S="0.0";
        		
        		
        	 //------------------------------------------------------------------------------------------------
        		
			}
		});				
		menu.add(mntmOuvrir);
		
		JMenu mnCalibration = new JMenu("Converter  ");
		menuBar.add(mnCalibration);
		
		JMenuItem mntmChn = new JMenuItem("to .chn");
		mntmChn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Converter.eviterErreurTableau= "plusOuMoins";
				Converter.ConvertTo = "To.Chn";
				
				ChnFileWriter.ChnFileWriter();
				//Converter.eviterErreurTableau= "";
				
			}
		});
		mntmChn.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(fichier==null){mntmChn.setEnabled(false);}
				else{mntmChn.setEnabled(true);}	
			}
		});
		
		mnCalibration.add(mntmChn);
		
		JMenuItem mntmSpe = new JMenuItem("to .spe");
		mntmSpe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Converter.eviterErreurTableau= "plusOuMoins";
				
                Converter.ConvertTo = "To.Spe";
				
				SpeFileWriter.WriteSpeFile();
				Converter.eviterErreurTableau= "";
			}
		});
		mntmSpe.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(fichier==null){mntmSpe.setEnabled(false);}
				else{mntmSpe.setEnabled(true);}	
			}
		});
		
		mnCalibration.add(mntmSpe);
		
		JMenuItem mntmSpc = new JMenuItem("to .spc");
		mntmSpc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
                Converter.ConvertTo = "To.Spc";
				
                SpcFileWriter.writeSpcFile();
				
				
				
			}
		});
		mntmSpc.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(fichier==null){mntmSpc.setEnabled(false);}
				else{mntmSpc.setEnabled(true);}	
			}
		});
		
		mnCalibration.add(mntmSpc);
		
		JMenuItem mntmDat = new JMenuItem("to .dat");
		mntmDat.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(fichier==null){mntmDat.setEnabled(false);}
				else{mntmDat.setEnabled(true);}	
			}
		});
		
		mnCalibration.add(mntmDat);
		
		JMenu mnMake = new JMenu("Make new file  ");
		menuBar.add(mnMake);
		
		JMenuItem mntmMakeFile = new JMenuItem("Dump content");
		mntmMakeFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Converter.textField1.setText("0.0");
				Converter.textField2.setText("0.0");
				Converter.textField3.setText("0.0");
				Converter.textField4.setText("0.0");
				Converter.textField5.setText("0.0");
				Converter.textField6.setText("0.0");
				Converter.textFieldE.setText("0.0");
				Converter.textFieldE1.setText("0.0");
				Converter.textFieldE2.setText("0.0");
				Converter.textFieldE3.setText("0.0");
				Converter.textArea_1.setText("");
				Converter.textArea.setText("");
				Converter.textFieldRange.setText("0.0") ;
				Converter.textFieldRange1.setText("0.0") ;
				Converter.textFieldLenghtDetector.setText("0.0");
				Converter.textFieldLenghtSample.setText("0.0");
				Converter.textFieldFWHM1.setText("0.0");
				Converter.textFieldFWHM2.setText("0.0");
				Converter.peakShape1.setText("0.0");
				Converter.peakShape2.setText("0.0");
				Converter.peakShape3.setText("0.0");
				Converter.textArea_2.setText("0.0");
				Converter.textArea_ROI.setText("");
				
				
				
			}
		});
		mntmMakeFile.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(fichier==null){mntmMakeFile.setEnabled(false);}
				else{mntmMakeFile.setEnabled(true);}	
			}
		});
		
		mnMake.add(mntmMakeFile);
		
		JMenuItem CreatingFile = new JMenuItem("Creating a file");
		CreatingFile.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(fichier==null){CreatingFile.setEnabled(false);}
				else{CreatingFile.setEnabled(true);}	
				

				
			}
		});
		CreatingFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String namFileWC = Converter.textField1.getText();
		    	String namFormat = namFileWC.substring(namFileWC.length() - 4, namFileWC.length());
		    	
		    	if(namFormat.equals(".chn")||namFormat.equals(".Chn")) {Converter.ConvertTo = "To.Chn"; ChnFileWriter.ChnFileWriter();}
		    	if(namFormat.equals(".spe")||namFormat.equals(".Spe")) {Converter.ConvertTo = "To.Spe"; SpeFileWriter.WriteSpeFile();}
		    	if(namFormat.equals(".spc")||namFormat.equals(".Spc")) {Converter.ConvertTo = "To.Spc"; SpcFileWriter.writeSpcFile();}
		    
			}
		});
		
		
		mnMake.add(CreatingFile);
		
		JMenu mnNewMenu = new JMenu("graphic visualization");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Log graph");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel panelgraph = new JPanel();
				panelgraph.removeAll();
				

		        String Ccontenu = textArea_2.getText(); // Assurez-vous que ce fichier est lu correctement
		        if (Ccontenu.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Enter spectrum file ");
		        } else {
		            JPanel pn;
		            try {
		                pn = new LogGraph(); // Initialise le graphique avec les données actuelles
		            } catch (IOException e2) {
		                JOptionPane.showMessageDialog(null, "Enter spectrum file ");
		                e2.printStackTrace();
		                return;
		            }  				
				panelgraph.setLayout(new java.awt.BorderLayout());
				panelgraph.add(pn);
				panelgraph.validate();	 
				
				
				JFrame framegraph = new JFrame("Graph Window");
				framegraph.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				framegraph.getContentPane().setLayout(new BorderLayout());
				framegraph.getContentPane().add(pn, BorderLayout.CENTER);
				framegraph.pack(); // Adjusts the frame to fit the preferred size of its components
				framegraph.setLocationRelativeTo(null); // Centers the frame on the screen
				framegraph.setVisible(true);
				
				
				
	            
        	}		  
			
                
				
			}
		});
		mntmNewMenuItem_2.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(fichier==null){mntmNewMenuItem_2.setEnabled(false);}
				else{mntmNewMenuItem_2.setEnabled(true);}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Normal graph");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel panelgraph = new JPanel();
				panelgraph.removeAll();
				

		        String Ccontenu = textArea_2.getText(); // Assurez-vous que ce fichier est lu correctement
		        if (Ccontenu.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Enter spectrum file ");
		        } else {
		            JPanel pn;
		            try {
		                pn = new Ggraphique(); // Initialise le graphique avec les données actuelles
		            } catch (IOException e2) {
		                JOptionPane.showMessageDialog(null, "Enter spectrum file ");
		                e2.printStackTrace();
		                return;
		            }  				
				panelgraph.setLayout(new java.awt.BorderLayout());
				panelgraph.add(pn);
				panelgraph.validate();	 
				
				
				JFrame framegraph = new JFrame("Graph Window");
				framegraph.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				framegraph.getContentPane().setLayout(new BorderLayout());
				framegraph.getContentPane().add(pn, BorderLayout.CENTER);
				framegraph.pack(); // Adjusts the frame to fit the preferred size of its components
				framegraph.setLocationRelativeTo(null); // Centers the frame on the screen
				framegraph.setVisible(true);
				
			}
		        }
		});
		mntmNewMenuItem_1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(fichier==null){mntmNewMenuItem_1.setEnabled(false);}
				else{mntmNewMenuItem_1.setEnabled(true);}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
	    panel = new JPanel();
		
		JToolBar toolBar = new JToolBar();
		
		JLabel lblNewLabel = new JLabel("File Name :");
		lblNewLabel.setForeground(new Color(0, 0, 160));
		
		JLabel lblNewLabel_1 = new JLabel("Start time :");
		lblNewLabel_1.setForeground(new Color(0, 0, 160));
		
		JLabel lblNewLabel_2 = new JLabel("Start date :");
		lblNewLabel_2.setForeground(new Color(0, 0, 160));
		
		JLabel lblNewLabel_3 = new JLabel("Real          :");
		lblNewLabel_3.setForeground(new Color(0, 0, 160));
		
		JLabel lblNewLabel_4 = new JLabel("Live           :");
		lblNewLabel_4.setForeground(new Color(0, 0, 160));
		
		JLabel lblNewLabel_5 = new JLabel("Dead         :");
		lblNewLabel_5.setForeground(new Color(0, 0, 160));
		
		JLabel lblNewLabel_6 = new JLabel("Sample description :");
		lblNewLabel_6.setForeground(new Color(0, 0, 160));
		
		
		
		JButton btnNewButton = new JButton(new ImageIcon( "icons\\folder.png" ));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser dialogue = new JFileChooser("example");
				dialogue.setAcceptAllFileFilterUsed(false); 
				dialogue.setDialogTitle("Select file ");
				FileNameExtensionFilter restrict = new FileNameExtensionFilter(".Chn","Chn");
				FileNameExtensionFilter restrict1 = new FileNameExtensionFilter(".Spc","Spc");
				FileNameExtensionFilter restrict3 = new FileNameExtensionFilter(".Spe","Spe");
				FileNameExtensionFilter restrict2 = new FileNameExtensionFilter(".dat","dat");
				FileNameExtensionFilter restrict4 = new FileNameExtensionFilter(".txt","txt");
				dialogue.addChoosableFileFilter(restrict);
				dialogue.addChoosableFileFilter(restrict1);
				dialogue.addChoosableFileFilter(restrict2);
				dialogue.addChoosableFileFilter(restrict3);
				dialogue.addChoosableFileFilter(restrict4);
				if (dialogue.showOpenDialog(null) ==  JFileChooser.APPROVE_OPTION) {					
					fichie = dialogue.getSelectedFile().getAbsolutePath();									
				  }	
				fichier = fichie;
				
				//---------------read-input-file-Chn----------------------------------------------
				String fileExtension = fichier.substring(fichier.lastIndexOf("."));
				if(fileExtension.equals(".Chn")||fileExtension.equals(".chn")||fileExtension.equals(".CHN")) {ChnFile.ChnFile();}
				if(fileExtension.equals(".Spc")||fileExtension.equals(".spc")||fileExtension.equals(".SPC")) {SpcFile.ReadSpcFile();}
				if(fileExtension.equals(".Spe")||fileExtension.equals(".spe")||fileExtension.equals(".SPE")) {try {SpeFile.ReadSpeFile();} catch (IOException e1) {e1.printStackTrace();}}
				
				
				//__________________________________________________________________
        		if(fichier==null){JOptionPane.showMessageDialog(null, "enter spectrum file");}
				else{
					
	        
		    	       		
				}	
        	//__________________________________________________________________		
        		
        		
        		Converter.textField1.setText(Converter.namFile);
        		Converter.textField2.setText(Converter.StrTime);   if(Converter.textField2.getText().isEmpty()) {Converter.textField2.setText("0.0");}
        		Converter.textField3.setText(Converter.StrDat);    if(Converter.textField3.getText().isEmpty()) {Converter.textField3.setText("0.0");}
        		Converter.textField4.setText(Converter.realtimS);  if(Converter.textField4.getText().isEmpty()) {Converter.textField4.setText("0.0");}	
        		Converter.textField5.setText(Converter.livetimS);  if(Converter.textField5.getText().isEmpty()) {Converter.textField5.setText("0.0");}
        		Converter.textField6.setText(Converter.Dead);      if(Converter.textField6.getText().isEmpty()) {Converter.textField6.setText("0.0");}
        		Converter.textFieldE.setText(Converter.cf0S);      if(Converter.textFieldE.getText().isEmpty()) {Converter.textFieldE.setText("0.0");}
        		Converter.textFieldE1.setText(Converter.cf1S);     if(Converter.textFieldE1.getText().isEmpty()) {Converter.textFieldE1.setText("0.0");}
        		Converter.textFieldE2.setText(Converter.cf2S);     if(Converter.textFieldE2.getText().isEmpty()) {Converter.textFieldE2.setText("0.0");}
        		Converter.textArea_1.setText(Converter.DetectorDescription);
        		Converter.textArea.setText(Converter.SampleDescription);	
        		Converter.textFieldRange.setText(Converter.offsetofdataS );  if(Converter.textFieldRange.getText().isEmpty()) {Converter.textFieldRange.setText("0.0");}
        		Converter.textFieldRange1.setText(Converter.lengthofdataS ); if(Converter.textFieldRange1.getText().isEmpty()) {Converter.textFieldRange1.setText("0.0");}
        		Converter.textFieldLenghtDetector.setText(Converter.lddS);   if(Converter.textFieldLenghtDetector.getText().isEmpty()) {Converter.textFieldLenghtDetector.setText("0.0");}
        		Converter.textFieldLenghtSample.setText(Converter.lsdS);     if(Converter.textFieldLenghtSample.getText().isEmpty()) {Converter.textFieldLenghtSample.setText("0.0");}	
        		
        		
        		//----------------for windows of FWHM calibration-----------------------------------------------
        		Converter.textFieldFWHM1.setText(Converter.cf0SF);  if(Converter.textFieldFWHM1.getText().isEmpty()) {Converter.textFieldFWHM1.setText("0.0");}
        		Converter.textFieldFWHM2.setText(Converter.cf1SF);  if(Converter.textFieldFWHM2.getText().isEmpty()) {Converter.textFieldFWHM2.setText("0.0");}
        		Converter.peakShape1.setText(Converter.cfp0S);      if(Converter.peakShape1.getText().isEmpty()) {Converter.peakShape1.setText("0.0");}
        		Converter.peakShape2.setText(Converter.cfp1S);      if(Converter.peakShape2.getText().isEmpty()) {Converter.peakShape2.setText("0.0");}
        		Converter.peakShape3.setText(Converter.cfp2S);      if(Converter.peakShape3.getText().isEmpty()) {Converter.peakShape3.setText("0.0");}
        		

        		//----------------------videz les champs---------------
        		Converter.namFile ="";	
        		Converter.StrTime="0.0";
        		Converter.StrDat="0.0";
        		Converter.realtimS="0.0";
        		Converter.livetimS="0.0";
        		Converter.Dead="0.0";
        		Converter.cf0S="0.0";
        		Converter.cf1S="0.0";
        		Converter.cf2S="0.0";
        		Converter.DetectorDescription="";
        		Converter.SampleDescription="";
        		Converter.offsetofdataS="0.0";
        		Converter.lengthofdataS="0.0";
        		Converter.lddS="0.0";
        		Converter.lsdS="0.0";
        		Converter.cf0SF="0.0";
        		Converter.cf1SF="0.0";
        		Converter.cfp0S="0.0";
        		Converter.cfp1S="0.0";
        		Converter.cfp2S="0.0";
        		
        		
        	 //------------------------------------------------------------------------------------------------
				
			}
		});
		btnNewButton.setToolTipText( "Open spectrum file" );
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(new ImageIcon("icons\\Creation.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String namFileWC = Converter.textField1.getText();
		    	String namFormat = namFileWC.substring(namFileWC.length() - 4, namFileWC.length());
				
				if(namFormat.equals(".chn")||namFormat.equals(".Chn")) {Converter.ConvertTo = "To.Chn"; ChnFileWriter.ChnFileWriter();}
		    	if(namFormat.equals(".spe")||namFormat.equals(".Spe")) {Converter.ConvertTo = "To.Spe"; SpeFileWriter.WriteSpeFile();}
		    	if(namFormat.equals(".spc")||namFormat.equals(".Spc")) {Converter.ConvertTo = "To.Spc"; SpcFileWriter.writeSpcFile();}
				
				
			}
		});
		
		JButton btnNewButton_3 = new JButton(new ImageIcon( "icons\\Deletion.png" ));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Converter.textField1.setText("");
				Converter.textField2.setText("");
				Converter.textField3.setText("");
				Converter.textField4.setText("");
				Converter.textField5.setText("");
				Converter.textField6.setText("");
				Converter.textFieldE.setText("");
				Converter.textFieldE1.setText("");
				Converter.textFieldE2.setText("");
				Converter.textFieldE3.setText("");
				Converter.textArea_1.setText("");
				Converter.textArea.setText("");
				Converter.textFieldRange.setText("") ;
				Converter.textFieldRange1.setText("") ;
				Converter.textFieldLenghtDetector.setText("");
				Converter.textFieldLenghtSample.setText("");
				Converter.textFieldFWHM1.setText("");
				Converter.textFieldFWHM2.setText("");
				Converter.peakShape1.setText("");
				Converter.peakShape2.setText("");
				Converter.peakShape3.setText("");
				Converter.textArea_2.setText("");
				Converter.textArea_ROI.setText("");
				
				
			}
		});
		toolBar.add(btnNewButton_3);
		
		btnNewButton_1.setToolTipText("Ceation new File");
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton(new ImageIcon( "icons\\LogSpectre.png" ));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel panelgraph = new JPanel();
				panelgraph.removeAll();
				

		        String Ccontenu = textArea_2.getText(); // Assurez-vous que ce fichier est lu correctement
		        if (Ccontenu.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Enter spectrum file ");
		        } else {
		            JPanel pn;
		            try {
		                pn = new LogGraph(); // Initialise le graphique avec les données actuelles
		            } catch (IOException e2) {
		                JOptionPane.showMessageDialog(null, "Enter spectrum file ");
		                e2.printStackTrace();
		                return;
		            }  				
				panelgraph.setLayout(new java.awt.BorderLayout());
				panelgraph.add(pn);
				panelgraph.validate();	 
				
				
				JFrame framegraph = new JFrame("Graph Window");
				framegraph.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				framegraph.getContentPane().setLayout(new BorderLayout());
				framegraph.getContentPane().add(pn, BorderLayout.CENTER);
				framegraph.pack(); // Adjusts the frame to fit the preferred size of its components
				framegraph.setLocationRelativeTo(null); // Centers the frame on the screen
				framegraph.setVisible(true);
				
				
				
	            
        	}
				
				
			}
		});
		btnNewButton_2.setToolTipText( "Log Graph" );
		toolBar.add(btnNewButton_2);
		
		
		
		JButton btnNewButton_4 = new JButton(new ImageIcon( "icons\\spectre.png" ));
		btnNewButton_4.setToolTipText("Normal Graph");
		toolBar.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panelgraph = new JPanel();
				panelgraph.removeAll();
				

		        String Ccontenu = textArea_2.getText(); // Assurez-vous que ce fichier est lu correctement
		        if (Ccontenu.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Enter spectrum file ");
		        } else {
		            JPanel pn;
		            try {
		                pn = new Ggraphique(); // Initialise le graphique avec les données actuelles
		            } catch (IOException e2) {
		                JOptionPane.showMessageDialog(null, "Enter spectrum file ");
		                e2.printStackTrace();
		                return;
		            }  				
				panelgraph.setLayout(new java.awt.BorderLayout());
				panelgraph.add(pn);
				panelgraph.validate();	 
				
				
				JFrame framegraph = new JFrame("Graph Window");
				framegraph.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				framegraph.getContentPane().setLayout(new BorderLayout());
				framegraph.getContentPane().add(pn, BorderLayout.CENTER);
				framegraph.pack(); // Adjusts the frame to fit the preferred size of its components
				framegraph.setLocationRelativeTo(null); // Centers the frame on the screen
				framegraph.setVisible(true);
				
			}
				
			}
		});
		toolBar.add(btnNewButton_4);
		
		textArea = new TextArea();
		textArea.setBounds(10, 300, 204, 150);
		panel.add(textArea);
		
		textArea_1 = new TextArea();
		textArea_1.setBounds(300, 300, 204, 150);
		panel.add(textArea_1);
		
		textArea_ROI = new TextArea();
		textArea_ROI.setBounds(350, 480, 204, 150);
		panel.add(textArea_ROI);
		
		
		textArea_2.setBounds(500, 300, 204, 150);
		panel.add(textArea_2);
		
		JLabel lblNewLabel_7 = new JLabel("Energy calibration         :");
		lblNewLabel_7.setForeground(new Color(0, 0, 160));
		
		JLabel lblAE = new JLabel(" E=  a + b*Ca + c*Ca^2 + d*Ca^3");
		//lblA_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAE.setBounds(405, 60, 250, 17);
		panel.add(lblAE);
		
		JLabel lblNewLabel_8 = new JLabel("FWHM Calibration       :");
		lblNewLabel_8.setForeground(new Color(0, 0, 160));
		
		JLabel lblAFwhm = new JLabel(" FWHM=  a + b*E^(1/2) ");
		//lblA_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAFwhm.setBounds(405, 137, 250, 17);
		panel.add(lblAFwhm);
		
		JLabel lblNewLabel_9 = new JLabel("peak shape calibration   :");
		lblNewLabel_9.setForeground(new Color(0, 0, 160));
		
		JLabel lblNewLabel_10 = new JLabel("Chanel range  :");
		lblNewLabel_10.setForeground(new Color(0, 0, 160));
		
		JLabel lblNewLabel_11 = new JLabel("Length of detector description :");
		lblNewLabel_11.setForeground(new Color(0, 0, 160));
		
		JLabel lblNewLabel_12 = new JLabel("Length of sample description   :");
		lblNewLabel_12.setForeground(new Color(0, 0, 160));
		
		JLabel lblNewLabel_13 = new JLabel("Detector description  :");
		lblNewLabel_13.setForeground(new Color(0, 0, 160));
		
		JLabel lblA1 = new JLabel("* a :");
		//lblA_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblA1.setBounds(255, 100, 26, 17);
		panel.add(lblA1);
		
		JLabel lblA2 = new JLabel("* b :");
		//lblA_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblA2.setBounds(360, 100, 26, 17);
		panel.add(lblA2);
		
		JLabel lblA3 = new JLabel(" c :");
		//lblA_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblA3.setBounds(460, 100, 26, 17);
		panel.add(lblA3);
		
		JLabel lblA4 = new JLabel(" d :");
		//lblA_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblA4.setBounds(550, 100, 26, 17);
		panel.add(lblA4);
		
		JLabel lblAFWHM1 = new JLabel("* a :");
		//lblA_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAFWHM1.setBounds(255, 160, 26, 17);
		panel.add(lblAFWHM1);
		
		JLabel lblAFWHM2 = new JLabel("* b :");
		//lblA_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAFWHM2.setBounds(360, 160, 26, 17);
		panel.add(lblAFWHM2);
		
		textField1 = new TextField("");
		textField1.setBounds(100, 60, 86, 20);
		textField1.setText(namFile);
		panel.add(textField1);
		
		textField2 = new TextField("");
		textField2.setBounds(100, 105, 86, 20);				
		panel.add(textField2);
		
		textField3 = new TextField("");
		textField3.setBounds(100, 138, 86, 20);				
		panel.add(textField3);
		
		textField4 = new TextField("");
		textField4.setBounds(100, 170, 86, 20);				
		panel.add(textField4);
		
	    textField5 = new TextField("");
		textField5.setBounds(100, 205, 86, 20);				
		panel.add(textField5);
		
		textField6 = new TextField("");
		textField6.setBounds(100, 238, 86, 20);				
		panel.add(textField6);
		
		TextField textField7 = new TextField("");
		textField7.setBounds(100, 60, 86, 20);				
		panel.add(textField7);
		
		textFieldE = new TextField("");
		textFieldE.setBounds(285, 100, 66, 17);			
		panel.add(textFieldE);
		
		textFieldE1 = new TextField("");
		textFieldE1.setBounds(385, 100, 66, 17);			
		panel.add(textFieldE1);
		
		textFieldE2 = new TextField("");
		textFieldE2.setBounds(485, 100, 66, 17);			
		panel.add(textFieldE2);
		
		textFieldE3 = new TextField("");
		textFieldE3.setBounds(585, 100, 66, 17);			
		panel.add(textFieldE3);
		
		textFieldFWHM1 = new TextField("");
		textFieldFWHM1.setBounds(285, 160, 66, 17);			
		panel.add(textFieldFWHM1);
		
		textFieldFWHM2 = new TextField("");
		textFieldFWHM2.setBounds(385, 160, 66, 17);			
		panel.add(textFieldFWHM2);
		
		peakShape1 = new TextField("");
		peakShape1.setBounds(285, 265, 66, 17);			
		panel.add(peakShape1);
		
		peakShape2 = new TextField("");
		peakShape2.setBounds(385, 265, 66, 17);			
		panel.add(peakShape2);
		
		peakShape3 = new TextField("");
		peakShape3.setBounds(485, 265, 66, 17);			
		panel.add(peakShape3);
		
		textFieldRange = new TextField("");
		textFieldRange.setBounds(425, 327, 66, 17);			
		panel.add(textFieldRange);
		
		textFieldRange1 = new TextField("");
		textFieldRange1.setBounds(560, 327, 66, 17);			
		panel.add(textFieldRange1);
		
		textFieldLenghtDetector = new TextField("");
		textFieldLenghtDetector.setBounds(485, 355, 66, 17);			
		panel.add(textFieldLenghtDetector);
		
		textFieldLenghtSample = new TextField("");
		textFieldLenghtSample.setBounds(485, 390, 66, 17);			
		panel.add(textFieldLenghtSample);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel_14 = new JLabel("Chanel :");
		lblNewLabel_14.setForeground(new Color(0, 0, 160));
		
		JLabel lblNewLabel_16 = new JLabel("Counts :");
		lblNewLabel_16.setForeground(new Color(0, 0, 160));
		
		JLabel lblA1_1 = new JLabel("* a :");
		
		JLabel lblA2_1 = new JLabel("* b :");
		
		JLabel lblA3_1 = new JLabel(" c :");
		
		JLabel lblNewLabel_15 = new JLabel("From  ");
		
		JLabel lblNewLabel_17 = new JLabel("to ");
		
		JLabel lblNewLabel_19 = new JLabel("ROI for .Spe");
		lblNewLabel_19.setForeground(new Color(0, 0, 160));
		
		
		
		lblNewLabel_18 = new JLabel();
		toolBar.add(lblNewLabel_18);
		lblNewLabel_18.setForeground(new Color(255, 0, 0));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField7, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(textField2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(textField3, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(textField4, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(textField5, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(textField6, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(31)
							.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(28)
							.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(5)
									.addComponent(lblNewLabel_7)
									.addGap(22)
									.addComponent(lblAE, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblA1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(textFieldE, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addGap(9)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblA2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(25)
											.addComponent(textFieldE1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)))
									.addGap(9)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(25)
											.addComponent(textFieldE2, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblA3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(90)
											.addComponent(lblA4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
									.addGap(9)
									.addComponent(textFieldE3, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(5)
									.addComponent(lblNewLabel_8)
									.addGap(30)
									.addComponent(lblAFwhm, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblAFWHM1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(textFieldFWHM1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addGap(9)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAFWHM2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(25)
											.addComponent(textFieldFWHM2, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(5)
									.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(5)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(25)
											.addComponent(peakShape1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblA1_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
									.addComponent(lblA2_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(63)
											.addComponent(lblA3_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
										.addComponent(peakShape2, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
									.addGap(11)
									.addComponent(peakShape3, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(19)
									.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(48)
											.addComponent(textFieldRange, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblNewLabel_15, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
									.addGap(20)
									.addComponent(lblNewLabel_17, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addComponent(textFieldRange1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(19)
									.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(textFieldLenghtDetector, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(19)
									.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(textFieldLenghtSample, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(108)
									.addComponent(lblNewLabel_19, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(126)
							.addComponent(textArea_ROI, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)))
					.addGap(44)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_14, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_16, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_14)
								.addComponent(lblNewLabel_16))
							.addGap(10)
							.addComponent(textArea_2, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
							.addGap(34))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGap(8)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(2)
											.addComponent(lblNewLabel_7))
										.addComponent(lblAE, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
									.addGap(23)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblA1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldE, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblA2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldE1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldE2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblA3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblA4, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldE3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
									.addGap(19)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_8)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(1)
											.addComponent(lblAFwhm, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
									.addGap(6)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAFWHM1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldFWHM1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAFWHM2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldFWHM2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
									.addGap(49)
									.addComponent(lblNewLabel_9)
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(7)
											.addComponent(peakShape1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblA1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblA2_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblA3_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(7)
											.addComponent(peakShape2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(7)
											.addComponent(peakShape3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
									.addGap(30)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(8)
											.addComponent(lblNewLabel_10))
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(8)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
													.addGap(7)
													.addComponent(textFieldRange, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
												.addComponent(lblNewLabel_15)))
										.addComponent(lblNewLabel_17)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(15)
											.addComponent(textFieldRange1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
									.addGap(8)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_11)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(3)
											.addComponent(textFieldLenghtDetector, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
									.addGap(12)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_12)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(6)
											.addComponent(textFieldLenghtSample, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
									.addGap(31)
									.addComponent(lblNewLabel_19, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addGap(25)
									.addComponent(textArea_ROI, GroupLayout.PREFERRED_SIZE, 107, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(2)
											.addComponent(lblNewLabel))
										.addComponent(textField7, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
									.addGap(24)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(1)
											.addComponent(textField2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
									.addGap(11)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_2)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(2)
											.addComponent(textField3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
									.addGap(10)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_3)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(2)
											.addComponent(textField4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
									.addGap(10)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_4)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(5)
											.addComponent(textField5, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
									.addGap(7)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_5)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(6)
											.addComponent(textField6, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
									.addGap(20)
									.addComponent(lblNewLabel_6)
									.addGap(20)
									.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 86, Short.MAX_VALUE)
									.addGap(38)
									.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGap(27)
									.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 103, Short.MAX_VALUE)))
							.addGap(43))))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		
		
		
		
	}
}

