package com.gui;
import com.Reseau.Client.Client;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientChatGUI extends JFrame implements ActionListener {

	
	int i = 6668;
	Client clnt = new Client("localhost", i, "User"+i);
	
	private JPanel contentPane;
	private JTextArea usernameInputFeild;
	private JTextField inputtextField;

	
	JTextArea chattextArea = new JTextArea();
	/**
	 * Create the frame.
	 */
	public ClientChatGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 598);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		usernameInputFeild = new JTextArea();
		
		JLabel userName = new JLabel("User Name");
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton connectButton = new JButton("Connect");
		connectButton.setActionCommand("connectButton");
		connectButton.addActionListener(this);
		
		JButton disconnectButton = new JButton("Disconnect");
		disconnectButton.setActionCommand("disconnectButton");
		disconnectButton.addActionListener(this);
		
		JTextArea contactlistArea = new JTextArea();
		contactlistArea.setColumns(20);
		contactlistArea.setEditable(false);
		
		//JTextArea chattextArea = new JTextArea();
		chattextArea.setColumns(20);
		chattextArea.setEditable(false);
		chattextArea.setLineWrap(true);
		//chattextArea.append("Me: \n");
		//chattextArea.append(inputtextField.getText());
		
		inputtextField = new JTextField();
		inputtextField.setColumns(20);
		
		JButton sendButton = new JButton("send");
		sendButton.setActionCommand("sendButton");
		sendButton.addActionListener(this);
		
		
		JTextArea groupcodeInputField = new JTextArea();
		
		JLabel groupcode = new JLabel("Group Code");
		
		JButton joinButton = new JButton("Join");
		joinButton.setActionCommand("joinButton");
		joinButton.addActionListener(this);
		
		JButton leaveButton = new JButton("Leave");
		leaveButton.setActionCommand("leaveButton");
		leaveButton.addActionListener(this);
		
		JLabel contactList = new JLabel("Contact  List");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(inputtextField, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(sendButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(userName, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
								.addComponent(groupcode, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(usernameInputFeild, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(connectButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(groupcodeInputField, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(joinButton, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)))
							.addGap(7)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(disconnectButton, GroupLayout.PREFERRED_SIZE, 107, Short.MAX_VALUE)
								.addComponent(leaveButton, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
							.addGap(2))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(chattextArea, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(contactList)
							.addGap(116))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(contactlistArea, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(usernameInputFeild, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(userName, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(connectButton)
						.addComponent(disconnectButton)
						.addComponent(contactList))
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(groupcodeInputField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(groupcode, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(joinButton)
								.addComponent(leaveButton))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chattextArea, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(inputtextField, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(sendButton)))
						.addComponent(contactlistArea))
					.addGap(0))
		);
		contentPane.setLayout(gl_contentPane);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		switch(e.getActionCommand()) {
		case "connectButton" :
			//System.out.print("bonjour");
			clnt.connect();
			
		break;
		case "disconnectButton" :
			//System.out.print("Good");
			//clnt.diconnect();
			
			break;
		case "sendButton" :
			
			//JTextArea chattextArea = new JTextArea();
			String writtenText=inputtextField.getText();
			//System.out.print("hello");
			System.out.print(writtenText);
			//inputtextField.getText();
			//inputtextField.selectAll();
			chattextArea.append("Me: ");
			chattextArea.append(writtenText);
			//System.out.print("hello2");
			
			//PutTextToChatTextArea("AA", getName(), writtenText );
			clnt.send("AA", getName()/*Gets the name of the component.*/, writtenText);
			
			break;
		case "joinButton" :
			//System.out.print("hey");
			clnt.join("AA");
			
			break;
		case "leaveButton" :
			//System.out.print("goodbye");
			clnt.leave("AA");
			
			break;
	}
	}


	/*private void PutTextToChatTextArea(String string, String name, String writtenText) {
		// TODO Auto-generated method stub
		JTextArea chattextArea = new JTextArea();
	}
	*/
}
