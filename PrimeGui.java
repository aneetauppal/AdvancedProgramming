package javaGui;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.*;
import javax.swing.border.Border;

public class PrimeGui extends JFrame {

	private static final long serialVersionUID = 1L;
	
   
    
			private JPanel buttonPanel = new JPanel();
		    private JPanel mainPanel = new JPanel(); 
		    private JPanel inputPanel = new JPanel(); 
		    
		    private JLabel instructionLabel = new JLabel("Enter a number to check if it's a Prime Number"); 
		    private JLabel userInputLabel = new JLabel("Enter a number:"); 
		    private JTextField userInputText = new JTextField(); 
		    private JLabel answerLabel = new JLabel();
		    
		   
		    private JButton submitButton = new JButton("Submit");
		    private JButton exitButton = new JButton("Exit");
		    private JButton saveButton = new JButton("Save"); 
		    
		    Border blackline = BorderFactory.createLineBorder(Color.black); 
		    
		    PrimeNumberGen prime;
		    private int numbers;
		    private String integertwo;
		    
		    public PrimeGui() {
		        super("Prime Number Generator");
		        this.setLocationRelativeTo(null);
		        this.setSize(300, 300);
		        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        
		        this.createButtonPanel();
		        this.createInputPanel(); 
		        this.createMainTextPanel(); 
		       
		        
		        this.getContentPane().setLayout(new BorderLayout());
		        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		        this.getContentPane().add(mainPanel, BorderLayout.CENTER); 

		        this.setVisible(true);
		        
		        prime = new PrimeNumberGen();

		    }

		    private void createButtonPanel() {

		        submitButton.addActionListener(new SubmitButtonActionListener());
		        saveButton.addActionListener(new SaveButtonActionListener());
		        exitButton.addActionListener(new ExitButtonActionListener());
		        buttonPanel.add(submitButton);
		        buttonPanel.add(exitButton);
		        buttonPanel.add(saveButton);
		        buttonPanel.setLayout(new GridLayout(0, 3));
		    }

		    private void createMainTextPanel() {
		        mainPanel.setBorder(blackline);
		        mainPanel.add(instructionLabel); 
		        mainPanel.add(answerLabel);
		        mainPanel.add(inputPanel); 
		        mainPanel.setLayout(new GridLayout(4,0));
		        
		      
		    }
		    
		    private void createInputPanel(){
		        inputPanel.add(userInputLabel);
		        inputPanel.add(userInputText);
		        inputPanel.setLayout(new GridLayout(0,2));
		    }
		    
		    private class SubmitButtonActionListener implements ActionListener{
		    	@Override
		        public void actionPerformed(ActionEvent e) {
		            String number = userInputText.getText(); 
		            int numbers = Integer.parseInt(number);
		            integertwo = prime.isPrime(numbers); 
		            answerLabel.setText(integertwo);
		            
		        }
		        
		    }
		    private class SaveButtonActionListener implements ActionListener{
		    	@Override
		    	public void actionPerformed(ActionEvent e){
		    		
		    		saveToFile();
		    	}
		    	
		    		
		    	public void saveToFile() {
		            
		            try {
		                BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/aneetauppal/Documents/results.txt"));
		                writer.write("Did you enter a prime number?:" + integertwo);
		                writer.flush();
		                writer.close();
		            } catch (Exception ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(instructionLabel, ex.getMessage(), "could not write the file", JOptionPane.ERROR_MESSAGE);
		            }

		    	}
		    }
		    
		    private class ExitButtonActionListener implements ActionListener{
		    	@Override
		        public void actionPerformed(ActionEvent e) {
		          System.exit(0);
		        }
		    
		    }
}



