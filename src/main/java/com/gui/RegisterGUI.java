package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Reseau.Client.Client.Globals;

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
	private final JTextField NametextField;
	private final JTextField PasswordtextField;

	String UserName = "", Password = "", OldUserName = "", NewUserName = "", OldPassword = "", NewPassword = "";

	/**
	 * Create the dialog.
	 */

	JButton UpdateButton = new JButton("Update");
	JButton DeleteButton = new JButton("Delete");

	public RegisterGUI() {
		setBounds(100, 100, 450, 300);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		final JLabel RegisterWarningMessage = new JLabel("");
		RegisterWarningMessage.setForeground(Color.RED);
		{
			userAccountLabel = new JLabel("User Account");
			userAccountLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

			final JButton RegisterButton = new JButton("Register");
			RegisterButton.setActionCommand("RegisterButton");
			RegisterButton.addActionListener(new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					RegisterWarningMessage.setText("");
					UserName = NametextField.getText();
					Password = PasswordtextField.getText();

					if ((UserName.length() == 0) || (Password.length() == 0)) {
						RegisterWarningMessage.setForeground(Color.RED);
						RegisterWarningMessage.setText("Please fill in your Name and Password!");
					} else {

						/*
						 * need to be fixed*****
						 * 
						 */
						final int rtn = 1;
						// doc = ;
						// id = ;

						//
						// rtn = clnt.addUser(doc, id, UserName, Password);

						if (rtn == 1) {
							RegisterWarningMessage.setForeground(Color.GREEN);
							RegisterWarningMessage.setText("Register Succceeded!");
						} else {
							RegisterWarningMessage.setForeground(Color.RED);
							RegisterWarningMessage.setText("Register Failed!");
						}
					}
				}
			});

			buttonPane.add(RegisterButton);
			getRootPane().setDefaultButton(RegisterButton);

			// JButton UpdateButton = new JButton("Update");
			UpdateButton.setActionCommand("UpdateButton");
			UpdateButton.addActionListener((new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					UserName = NametextField.getText();
					OldUserName = Globals.UserName;
					NewUserName = UserName;
					OldPassword = Globals.Passwd;

					NewPassword = PasswordtextField.getText();
					RegisterWarningMessage.setText("");
					if (NewUserName.length() == 0 || NewPassword.length() == 0) {
						RegisterWarningMessage.setForeground(Color.RED);
						RegisterWarningMessage.setText("Please check your User Name and Password!");
					} else {

						final int rtn1 = 1, rtn2 = 1;
						/*
						 * need to be fixed*****
						 * 
						 */
						// doc = ;
						// id = ;
						// rtn1= clnt.UpdateUserNamme(doc, OldUserName, NewUserName);
						// rtn2 = clnt.UpdatePassword(doc, UserName, OldPassword, NewPassword);
						if (rtn1 == 1 && rtn2 == 1) {
							RegisterWarningMessage.setForeground(Color.GREEN);
							RegisterWarningMessage.setText("Update Succceeded!");
							Globals.UserName = NewUserName;
							Globals.Passwd = NewPassword;
						} else if (rtn1 != 1 && rtn2 == 1) {
							RegisterWarningMessage.setForeground(Color.RED);
							RegisterWarningMessage.setText("Please check your User Name!");
						} else if (rtn1 == 1 && rtn2 != 1) {
							RegisterWarningMessage.setForeground(Color.RED);
							RegisterWarningMessage.setText("Please check your Password!");
						} else {
							RegisterWarningMessage.setForeground(Color.RED);
							RegisterWarningMessage.setText("Please check your User Name and Password!");
						}
					}
				}
			}));
			buttonPane.add(UpdateButton);

			DeleteButton.setActionCommand("DeleteButton");
			DeleteButton.addActionListener((new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					/*
					 * must use login name to delete, or the user may delete others account!!
					 */
					UserName = Globals.UserName;
					Password = Globals.Passwd;
					RegisterWarningMessage.setText("");
					if (UserName.length() == 0 || Password.length() == 0) {
						RegisterWarningMessage.setForeground(Color.RED);
						RegisterWarningMessage.setText("Please check your User Name and Password!");
					} else {

						final int rtn = 1;
						/*
						 * need to be fixed*****
						 * 
						 */
						// id = ;
						// rtn = clnt.DeleteUser(doc, UserToDelete);
						if (rtn == 1) {
							RegisterWarningMessage.setForeground(new Color(0, 255, 0));
							RegisterWarningMessage.setText("User Deleted!");
						} else {
							RegisterWarningMessage.setForeground(Color.RED);
							RegisterWarningMessage.setText("User Delete Failed!");
						}
					}
				}
			}));
			buttonPane.add(DeleteButton);
		}
		final GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
				.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)));

		final JLabel RegisterPasswordLabel = new JLabel("Password");
		RegisterPasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 20));

		NametextField = new JTextField();
		NametextField.setColumns(10);
		NametextField.setText(Globals.UserName);
		if (Globals.UserName.length() == 0) {
			UpdateButton.setEnabled(false);
			DeleteButton.setEnabled(false);
		}

		final JLabel RegisterNewNameLabel = new JLabel("Name");
		RegisterNewNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));

		PasswordtextField = new JTextField();
		PasswordtextField.setColumns(10);
		PasswordtextField.setText(Globals.Passwd);

		final GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(userAccountLabel, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(RegisterPasswordLabel)
								.addComponent(RegisterNewNameLabel))
							.addGap(45)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(PasswordtextField, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
								.addComponent(NametextField, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
								.addComponent(RegisterWarningMessage))))
					.addGap(66))
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
