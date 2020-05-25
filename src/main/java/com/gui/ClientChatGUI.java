package com.gui;
import com.Reseau.Client.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JApplet;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.DropMode;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;

public class ClientChatGUI extends JFrame implements ActionListener {

	
	int i = 6668;
	Client clnt = new Client("localhost", i, "User"+i);
	
	private JPanel contentPane;
	private JTextArea usernameInputFeild;
	private JTextField inputtextField;
	


	
	JTextPane chattextArea = new JTextPane();
	
	//StyledDocument doc = chattextArea.getStyledDocument();
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
		
		inputtextField = new JTextField();
		inputtextField.setHorizontalAlignment(SwingConstants.LEFT);
		inputtextField.setDocument(new JTextFieldLimit(30));
		inputtextField.addActionListener(new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent evt) {
				String writtenText=inputtextField.getText();
				inputtextField.setText(null);
				
				PutTextToChatTextArea("AA", getName(), writtenText );
				clnt.send("AA", getName()/*Gets the name of the component.*/, writtenText);
				
			}
		
		});
		
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
		
		JPanel chatPanel = new JPanel();
		
		JPanel contactListPanel = new JPanel();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
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
								.addComponent(disconnectButton, GroupLayout.PREFERRED_SIZE, 115, Short.MAX_VALUE)
								.addComponent(leaveButton, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
							.addGap(2))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(inputtextField, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(sendButton))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(contactList)
						.addComponent(contactListPanel, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
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
							.addComponent(chatPanel, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(inputtextField)
								.addComponent(sendButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(13))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(contactListPanel, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		contactListPanel.setLayout(new BorderLayout(0, 0));
		
		
		JTextArea contactlistArea = new JTextArea();
		contactListPanel.add(contactlistArea);
		//JScrollPane contactlistAreajsp = new JScrollPane(contactlistArea);
		contactlistArea.setEditable(false);
		contactlistArea.setColumns(20);
		
		JScrollPane contactListScrollBar = new JScrollPane(contactlistArea);
		contactListScrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contactListScrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contactListPanel.add(contactListScrollBar, BorderLayout.CENTER);
		chatPanel.setLayout(new BorderLayout(0, 0));
		chatPanel.add(chattextArea);
		
		
		chattextArea.setBackground(Color.YELLOW);
		chattextArea.setEditable(false);
		chattextArea.setForeground(Color.WHITE);
		chattextArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JScrollPane scrollPane = new JScrollPane(chattextArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		chatPanel.add(scrollPane, BorderLayout.CENTER);
		//newPanel.setBounds(6,7,175,179);
		contentPane.setLayout(gl_contentPane);
	}

	public class JTextFieldLimit extends PlainDocument {
		  private int limit;

		  JTextFieldLimit(int limit) {
		   super();
		   this.limit = limit;
		   }

		  public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
		    if (str == null) return;

		    if ((getLength() + str.length()) <= limit) {
		      super.insertString(offset, str, attr);
		    }
		  }
		}
/*
 * @param e  action event for buttons	
 * @see PutTextToChatTextArea
 */
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
			
			inputtextField.setText(null);
			//System.out.print("hello2");
			
			PutTextToChatTextArea("AA", getName(), writtenText );
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

/*
 * @param string      groupcode
 * @param name        the user's name
 * @param writtenText what the user types
 * 
 * The login user will be aligned to the left, the other side will be aligned to the right.
 * 
 */
	private void PutTextToChatTextArea(String groupcode, String name, String writtenText) {
		// TODO Auto-generated method stub
		String header = "";
		StyledDocument doc = chattextArea.getStyledDocument();
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		if (name == getName()) {
			header = "Me : ";
			
			StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_LEFT);
			StyleConstants.setForeground(attribs, Color.BLUE);
			
			chattextArea.setParagraphAttributes(attribs, true);
		try {
			
			doc.insertString(doc.getLength(), header + writtenText +"\n" , attribs);
			}
		catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else {
			
			StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);
			StyleConstants.setForeground(attribs, Color.GREEN);
			chattextArea.setParagraphAttributes(attribs, true);
			try {
				doc.insertString(doc.getLength(),  writtenText +getName() +"\n", attribs);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}