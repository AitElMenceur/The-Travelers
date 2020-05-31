package com.gui;


import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RegisterGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JLabel userAccountLabel;
	private JTextField NametextField;
	private JTextField PasswordtextField;

	private String UserName="", Password="", OldUserName="", NewUserName="";
	char[] NewPassword ;
	char[] OldPassword ;
	private JButton UpdateButton = new JButton("Update");
	private JButton DeleteButton = new JButton("Delete");
	private JLabel RegisterWarningMessage = new JLabel("");
	
	public RegisterGUI() {
		setBounds(100, 100, 450, 300);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		RegisterWarningMessage.setForeground(Color.RED);
		{
			userAccountLabel = new JLabel("User Account");
			userAccountLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			
			JButton RegisterButton = new JButton("Register");
			RegisterButton.setActionCommand("RegisterButton");
			RegisterButton.addActionListener(new ActionListener(){
			     public void actionPerformed(ActionEvent e){  
			    	 RegisterWarningMessage.setText("");
			    	 UserName = NametextField.getText() ;
					 Password = PasswordtextField.getText();
					 
					if((UserName.length() == 0)||( Password.length() == 0)){
							RegisterWarningMessage.setForeground(Color.RED);
							RegisterWarningMessage.setText("Please fill in your Name and Password!");
					 }
					 else {
						boolean rtn = true;
						//*****needs to be modified
						rtn = Globals.clnt.createUser(UserName, Password);
						 
						 if(rtn) {
							 RegisterWarningMessage.setForeground(Color.GREEN);
							 RegisterWarningMessage.setText("Register Succceeded!");
						 }
						 else {
							 RegisterWarningMessage.setForeground(Color.RED);
							 RegisterWarningMessage.setText("Register Failed!");
						 }
			        }    
			     }
			 });

			buttonPane.add(RegisterButton);
			getRootPane().setDefaultButton(RegisterButton);
			
			
			UpdateButton.setActionCommand("UpdateButton");
			UpdateButton.addActionListener((new ActionListener(){
			     public void actionPerformed(ActionEvent e){  
					UserName = NametextField.getText();
					OldUserName = Globals.UserName;
					NewUserName = UserName;
				  	OldPassword = Globals.Passwd;
				 
					NewPassword = PasswordtextField.getText().toCharArray();
					RegisterWarningMessage.setText("");
					if(
							NewUserName.length() == 0 || NewPassword.length == 0) {
							RegisterWarningMessage.setForeground(Color.RED);
							RegisterWarningMessage.setText("Please check your User Name and Password!");
					}
					else {
					
					boolean rtn1 =true, rtn2 = true;
					//*****needs to be modified
					rtn1 = Globals.clnt.updateUsername(OldUserName, NewUserName, new String(OldPassword));
					rtn2 = Globals.clnt.updatePassword(UserName,new String(OldPassword), new String(NewPassword));
					
					if(rtn1 && rtn2) {
						RegisterWarningMessage.setForeground(Color.GREEN);
						 RegisterWarningMessage.setText("Update Succceeded!");
						 Globals.UserName = NewUserName;
						 Globals.Passwd = NewPassword;
						 }
					else if( !rtn1 && rtn2) {
						RegisterWarningMessage.setForeground(Color.RED);
						RegisterWarningMessage.setText("Please check your User Name!");
					}
					else if(rtn1 && !rtn2) {
						RegisterWarningMessage.setForeground(Color.RED);
						RegisterWarningMessage.setText("Please check your Password!");
					}
					else {
						RegisterWarningMessage.setForeground(Color.RED);
						 RegisterWarningMessage.setText("Please check your User Name and Password!");
						 }
			        }    
			     }
			 }));
			buttonPane.add(UpdateButton);
				
			DeleteButton.setActionCommand("DeleteButton");
			DeleteButton.addActionListener((new ActionListener(){
			     public void actionPerformed(ActionEvent e){  
			    	 /*
			    	  *  must use login name to delete, or the user may delete others account!!
			    	  */
			    	 UserName = Globals.UserName;
			    	 Password = new String(Globals.Passwd);
			    	 RegisterWarningMessage.setText("");
			    	 if(UserName.length() == 0 || Password.length() == 0) {
			    		 	RegisterWarningMessage.setForeground(Color.RED);
							RegisterWarningMessage.setText("Please check your User Name and Password!");
						}
						else {
						
			    	 boolean rtn = true;
			    	 //*****needs to be modified
					 rtn = Globals.clnt.deleteUser(UserName);
			    	 
			    	 if(rtn) {
			    		 RegisterWarningMessage.setForeground(new Color(0, 255, 0));
			    		 RegisterWarningMessage.setText("User Deleted!");
					 }
					 else {
						 RegisterWarningMessage.setForeground(Color.RED);
						 RegisterWarningMessage.setText("User Delete Failed!");
					 }
			        }    
			     }
			 }));
			buttonPane.add(DeleteButton);
		}
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
				.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JLabel RegisterPasswordLabel = new JLabel("Password");
		RegisterPasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		
		NametextField = new JTextField();
		NametextField.setColumns(10);
		NametextField.setText(Globals.UserName);
		if(Globals.UserName.length()== 0) {
			UpdateButton.setEnabled(false);
			DeleteButton.setEnabled(false);
		}

		
		JLabel RegisterNewNameLabel = new JLabel("Name");
		RegisterNewNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		PasswordtextField = new JTextField();
		PasswordtextField.setColumns(10);
		PasswordtextField.setText(new String(Globals.Passwd));
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(RegisterPasswordLabel)
						.addComponent(RegisterNewNameLabel))
					.addGap(45)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(PasswordtextField, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
						.addComponent(NametextField, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
						.addComponent(RegisterWarningMessage))
					.addGap(66))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(104)
					.addComponent(userAccountLabel, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
					.addGap(98))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(userAccountLabel)
					.addGap(23)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(RegisterNewNameLabel)
						.addComponent(NametextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(16)
							.addComponent(RegisterPasswordLabel))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(PasswordtextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(RegisterWarningMessage)
					.addGap(36))
		);
		contentPanel.setLayout(gl_contentPanel);
		getContentPane().setLayout(groupLayout);
	}

}
