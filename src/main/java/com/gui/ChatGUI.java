package com.gui;
import com.gui.LogInGUI;
import com.Reseau.Client.Client;
import com.Reseau.Data.Group;
import com.Reseau.Server.Host;
import com.gui.Globals;
import com.gui.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.BadLocationException;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ChatGUI extends JFrame implements ActionListener {

	
	int i = 6668;
	//Client clnt = new Client("localhost", i, "User"+i);
	Client clnt = new Client("localhost", i);
	private JPanel contentPane;
	private JTextField inputtextField;
	private JTextPane chattextArea = new JTextPane();
	private JButton sendButton = new JButton("send");
	private JComboBox comboBox = new JComboBox();
	private JLabel GroupWarningLabel = new JLabel("  "); 
	private JLabel GroupLabel = new JLabel("@");
	//Host host = new Host();

	/**
	 * Create the frame.
	 */
	
	public ChatGUI() {
		setTitle("The-Travelers");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton connectButton = new JButton("Contactor");
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
				if(sendButton.isEnabled()) {
				PutTextToChatTextArea(Globals.CurrentGroup,Globals.UserName , writtenText );
				clnt.send(Globals.CurrentGroup, Globals.UserName, writtenText);
				}
				
			}
		
		});
		

		sendButton.setActionCommand("sendButton");
		sendButton.addActionListener(this);
		sendButton.setEnabled(false);
		
		JLabel groupcode = new JLabel("Group Code");
		
		JButton joinButton = new JButton("Join");
		joinButton.setActionCommand("joinButton");
		joinButton.addActionListener(this);
		
		JButton leaveButton = new JButton("Leave");
		leaveButton.setActionCommand("leaveButton");
		leaveButton.addActionListener(this);
		
		JPanel chatPanel = new JPanel();
		
		JLabel UserNameLabel = new JLabel("");
		UserNameLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		UserNameLabel.setText(Globals.UserName);
		
		comboBox.setEditable(true);
	            	
	    comboBox.addPopupMenuListener(new PopupMenuListener () {
	    	 public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
	    		 ArrayList<Group> al =new ArrayList<Group>();
	    		 // call needed*****
	    		al = clnt.getLisGroup();
	    	    Globals.GroupCode = al.toArray(Globals.GroupCode);
	    		 comboBox.removeAllItems();
	    		 for(int i=0;i<Globals.GroupCode.length; i++) {
	            		comboBox.addItem(Globals.GroupCode[i]);
	            		
	            	}
	    	 }

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			
	     });   
	     

		JButton DeleteGroupButton = new JButton("Delete");
		DeleteGroupButton.setActionCommand("Delete");
		DeleteGroupButton.addActionListener(this);
		
		JButton CreateGroupButton = new JButton("Create");
		CreateGroupButton.setActionCommand("Create");
		CreateGroupButton.addActionListener(this);
		
		GroupWarningLabel.setForeground(new Color(255, 0, 0));
		GroupWarningLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		GroupLabel.setForeground(Color.BLUE);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chatPanel, GroupLayout.PREFERRED_SIZE, 630, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(inputtextField, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(sendButton))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(groupcode, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
											.addComponent(GroupWarningLabel)))
									.addComponent(UserNameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(joinButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(CreateGroupButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addComponent(DeleteGroupButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(leaveButton, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(GroupLabel, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(disconnectButton))))))
					.addGap(22)
					.addComponent(connectButton, 0, 0, Short.MAX_VALUE)
					.addGap(212))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(UserNameLabel)
							.addComponent(GroupLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addComponent(disconnectButton)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(connectButton)
								.addGap(82))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(groupcode, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(CreateGroupButton)
									.addComponent(DeleteGroupButton))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(GroupWarningLabel)
								.addGap(18)
								.addComponent(chatPanel, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(joinButton)
								.addComponent(leaveButton))))
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(sendButton, 0, 0, Short.MAX_VALUE)
						.addComponent(inputtextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39))
		);
		chatPanel.setLayout(new BorderLayout(0, 0));
		chatPanel.add(chattextArea);
		
		
		chattextArea.setBackground(new Color(255, 218, 185));
		chattextArea.setEditable(false);
		chattextArea.setForeground(Color.WHITE);
		chattextArea.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		
		JScrollPane scrollPane = new JScrollPane(chattextArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		chatPanel.add(scrollPane, BorderLayout.CENTER);
		contentPane.setLayout(gl_contentPane);
		
		
		
		String GroupCode = "AA";
		int numberOfMessages = 2;
		String[][] Historymessages = {{"hello","Rebecca"},{"okay","Karina"}};
		//need be modified*****
		//HistoryMessages = displayHistory(doc, GroupCode, numbaerOfMessages);   
		for( int i=0; i<numberOfMessages; i++) {
			PutTextToChatTextArea(GroupCode, Historymessages[i][1], Historymessages[i][0] );
		}
		
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
		//case "contactorButton" :
			//Need to be modified
			//clnt.contactor();
			
		//break;
		case "disconnectButton" :
			//need to be modified*****
			clnt.diconnect();
			System.exit(0);
			break;
		case "sendButton" :
			String writtenText=inputtextField.getText();
			System.out.println(writtenText);

			
			inputtextField.setText(null);
			/*
			 * @see PutTextToChatTextArea 
			 */
			PutTextToChatTextArea(Globals.CurrentGroup, Globals.UserName, writtenText );
			for( int i=0;i<Globals.Chatters.length; i++)
			{
				clnt.send(Globals.CurrentGroup, Globals.Chatters[i], writtenText);
			}
			
			
			break;
			
			
		case "joinButton" :
			boolean Ret = true;
			//join function need to be fixed
			//Please return an integer back*****
			Ret = clnt.join((String)comboBox.getSelectedItem());
			GroupWarningLabel.setText("");
			GroupLabel.setText("");
			String GroupName1;
			int flag = 1;
			GroupName1 = (String) comboBox.getSelectedItem();
			/*
			 * compare whether the selected group code has been created, otherwise joined failed!
			 */
			for(int i = 0; i<Globals.GroupCode.length; i++) {
				if(GroupName1.equals(Globals.GroupCode[i]))
					flag *= 0;
				else
					flag *= 1;
			}
			if(flag == 1) {
				GroupWarningLabel.setForeground(new Color(255, 0, 0));
				GroupWarningLabel.setText("Group coede not exist!");
				return;
			}
			 if(Ret) {
				 
				 GroupWarningLabel.setForeground(new Color(0, 255, 0));
				 GroupWarningLabel.setText("Group Joined!");
				 Globals.CurrentGroup = GroupName1;
				 GroupLabel.setText("@" + GroupName1);
				 sendButton.setEnabled(true);
			 }
			 else {
				 GroupWarningLabel.setForeground(new Color(255, 0, 0));
				 GroupWarningLabel.setText("Failed to Join Group!");
				 sendButton.setEnabled(false);;
				 
				 
			 }
			 /*
			  * if join succeeded, a bunch of user names should be put into Globals.Chattes.
			  */
			 //need to be modified*****
			 //Globals.Chatters[]= clnt.join((String)comboBox.getSelectedItem());
			
			break;
		case "leaveButton" :
			boolean Ret2 = true;
			//Please return an integer back*****
			Ret2 = clnt.leave(Globals.CurrentGroup);
			GroupWarningLabel.setText("");
			String GroupName2;
			GroupName2 = (String) comboBox.getSelectedItem();
			 if(Ret2) {
				 GroupWarningLabel.setForeground(new Color(0, 255, 0));
				 GroupWarningLabel.setText("Group leaved!");
				 Globals.CurrentGroup = null;
				 GroupLabel.setText("");
				 sendButton.setEnabled(false);;
				 Globals.Chatters = null;
			 }
			 else {
				 GroupWarningLabel.setForeground(new Color(255, 0, 0));
				 GroupWarningLabel.setText("Failed to leave Group!");
				 sendButton.setEnabled(true);;
			 }
			/*
			 * clear group member list Area
			 * 
			 */
			 
			
			break;
		
		case "Create" :
			
			GroupWarningLabel.setText("");
			String GroupName3;
			boolean Ret3 = true;
			GroupName3 = (String) comboBox.getSelectedItem();
			/*
			 * need to be fixed*****
			 * 
			 */ 
			Ret3 = clnt.createGroup( GroupName3);
			 if(Ret3) {
				 GroupWarningLabel.setForeground(new Color(0, 255, 0));
				 GroupWarningLabel.setText("Group Created!");
			 }
			 else {
				 GroupWarningLabel.setForeground(new Color(255, 0, 0));
				 GroupWarningLabel.setText("Failed to create Group!");
			 }
			
			break;
			
		case "Delete" :
			boolean Ret4 = true;
			String GroupName4;
			GroupName4 = (String) comboBox.getSelectedItem();
			//needs to be modified*****
			Ret4 = clnt.deleteGroup( GroupName4);
			if(Ret4) {
				GroupWarningLabel.setForeground(new Color(0, 255, 0));
				 GroupWarningLabel.setText("Group Deleted!");
			 }
			 else {
				 GroupWarningLabel.setForeground(new Color(255, 0, 0));
				 GroupWarningLabel.setText("Failed to delete this Group!");
			 }
			
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
	public void PutTextToChatTextArea(String groupcode, String name, String writtenText) {
		// TODO Auto-generated method stub
		//System.out.println("*****"+writtenText);
		//if(writtenText.length() == 0) {
			//return;
		//}
		String header = "";
		StyledDocument doc = chattextArea.getStyledDocument();
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		if (name.equals(Globals.UserName)) {
			header = "Me : ";
			//System.out.println("#####"+writtenText);
			StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_LEFT);
			StyleConstants.setForeground(attribs, Color.DARK_GRAY);
			
			chattextArea.setParagraphAttributes(attribs, true);
		try {
			//System.out.println("#####"+writtenText);
			doc.insertString(doc.getLength(), header + writtenText +"\n" , attribs);
			}
		catch (BadLocationException e) {
			// TODO Auto-generated catch block
			//System.out.println("@@@@@"+writtenText);
			e.printStackTrace();
	    	}
		}
		else {
			//System.out.println("&&&&&"+writtenText);
			StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);
			StyleConstants.setForeground(attribs, Color.BLUE);
			chattextArea.setParagraphAttributes(attribs, true);
			try { 
				header = " : " +name;
				doc.insertString(doc.getLength(),  writtenText +header +"\n", attribs);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println("-----"+writtenText);		
	}
	

}
	
/*	public static String[] removeItemFromArray(String[] input, String item) {
	    if (input == null) {
	        return null;
	    } else if (input.length <= 0) {
	        return input;
	    } else {
	        String[] output = new String[input.length - 1];
	        int count = 0;
	        for (String i : input) {
	            if (!i.equals(item)) {
	                output[count++] = i;
	            }
	        }
	        return output;
	    }
	}
	
	
	public static String[] addItemIntoArray(String[] input, String item) {
	    
		
		String[] output = new String[input.length+1];
        int count = 0;
        for (String i : input) {
                output[count++] = i;              
           
        } 
        output[count] = item;
        
        return output; 
	}
	
	public void ClearGroupMemberListArea() {
		
		
	}
*/	
