package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.JComboBox;

public class ChatGUI extends JFrame implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputtextField;
	private JTextPane chattextArea = new JTextPane();
	private JButton sendButton = new JButton("send");
	private JComboBox<Object> comboBox = new JComboBox<Object>();
	private JLabel GroupWarningLabel = new JLabel("  ");
	private JLabel GroupLabel = new JLabel("@");
	private String GroupCode;

	private Object makeObj(final String item) {
		return new Object() {
			public String toString() {
				return item;
			}
		};
	}

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
		inputtextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String writtenText = inputtextField.getText();
				inputtextField.setText(null);
				if (sendButton.isEnabled()) {
					Globals.clnt.send(Globals.CurrentGroup, Globals.UserName, writtenText);
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

		comboBox.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				comboBox.removeAllItems();
				for (String s : Globals.clnt.list) {

					comboBox.addItem(s);
				}

			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {

			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

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
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chatPanel, GroupLayout.PREFERRED_SIZE, 630, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(inputtextField, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(sendButton))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(groupcode, GroupLayout.PREFERRED_SIZE, 99,
																GroupLayout.PREFERRED_SIZE)
														.addGap(18)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 155,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(GroupWarningLabel)))
												.addComponent(UserNameLabel, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
														.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(joinButton, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(CreateGroupButton, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_contentPane
																.createParallelGroup(Alignment.LEADING, false)
																.addComponent(DeleteGroupButton,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(leaveButton, GroupLayout.DEFAULT_SIZE, 87,
																		Short.MAX_VALUE)))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(GroupLabel, GroupLayout.DEFAULT_SIZE, 228,
																Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(disconnectButton))))))
				.addGap(22).addComponent(connectButton, 0, 0, Short.MAX_VALUE).addGap(212)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(UserNameLabel)
								.addComponent(GroupLabel))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(16).addComponent(disconnectButton)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(connectButton).addGap(82))
						.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.BASELINE)
								.addComponent(groupcode, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(CreateGroupButton).addComponent(DeleteGroupButton))
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(GroupWarningLabel).addGap(18)
								.addComponent(chatPanel, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(38)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(joinButton).addComponent(leaveButton))))
				.addGap(9)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(sendButton, 0, 0, Short.MAX_VALUE).addComponent(inputtextField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(39)));
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

		int numberOfMessages = 2;
		String[][] Historymessages = {};
		// need be modified*****
		// HistoryMessages = displayHistory(doc, GroupCode, numbaerOfMessages);

	}

	public class JTextFieldLimit extends PlainDocument {
		private int limit;

		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null)
				return;

			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}

	/*
	 * @param e action event for buttons
	 * 
	 * @see PutTextToChatTextArea
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Globals.clnt.getLisGroup();
		Globals.GroupCode = Globals.clnt.list;

		switch (e.getActionCommand()) {
			// case "contactorButton" :
			// Need to be modified
			// Globals.clnt.contactor();

			// break;
			case "disconnectButton":
				// need to be modified*****
				Globals.clnt.diconnect();
				System.exit(0);
				break;
			case "sendButton":
				String writtenText = inputtextField.getText();
				System.out.print(writtenText);

				inputtextField.setText(null);
				/*
				 * @see PutTextToChatTextArea
				 */
				// PutTextToChatTextArea(Globals.CurrentGroup, Globals.UserName, writtenText);
				Globals.clnt.send(Globals.CurrentGroup, Globals.UserName, writtenText);

				break;

			case "joinButton":
				// join function need to be fixed
				// Please return an integer back*****
				Globals.clnt.getLisGroup();
				Globals.GroupCode = Globals.clnt.list;
				String temp = (String) comboBox.getSelectedItem();
				boolean Ret = Globals.clnt.join(temp);
				GroupWarningLabel.setText("");
				GroupLabel.setText("");
				String GroupName1;
				boolean flag = false;
				GroupName1 = (String) comboBox.getSelectedItem();
				/*
				 * compare whether the selected group code has been created, otherwise joined
				 * failed!
				 */
				for (String s : Globals.GroupCode) {
					if (GroupName1.equals(s)) {
						flag = true;
					}
				}
				if (!flag) {
					GroupWarningLabel.setForeground(new Color(255, 0, 0));
					GroupWarningLabel.setText("Group coede not exist!");
				}
				if (Ret) {

					GroupWarningLabel.setForeground(new Color(0, 255, 0));
					GroupWarningLabel.setText("Group Joined!");
					Globals.CurrentGroup = GroupName1;
					GroupLabel.setText("@" + GroupName1);
					sendButton.setEnabled(true);
				} else {
					GroupWarningLabel.setForeground(new Color(255, 0, 0));
					GroupWarningLabel.setText("Failed to Join Group!");
					sendButton.setEnabled(false);

				}
				/*
				 * if join succeeded, a bunch of user names should be put into Globals.Chattes.
				 */
				// need to be modified*****
				// Globals.Chatters[]= Globals.clnt.join((String)comboBox.getSelectedItem());

				break;
			case "leaveButton":
				boolean Ret2 = true;
				// Please return an integer back*****
				Ret2 = Globals.clnt.leave(Globals.CurrentGroup);
				GroupWarningLabel.setText("");
				String GroupName2;
				GroupName2 = (String) comboBox.getSelectedItem();
				if (Ret2) {
					GroupWarningLabel.setForeground(new Color(0, 255, 0));
					GroupWarningLabel.setText("Group leaved!");
					Globals.CurrentGroup = null;
					GroupLabel.setText("");
					sendButton.setEnabled(false);
					;
					Globals.Chatters = null;
				} else {
					GroupWarningLabel.setForeground(new Color(255, 0, 0));
					GroupWarningLabel.setText("Failed to leave Group!");
					sendButton.setEnabled(true);
					;
				}
				/*
				 * clear group member list Area
				 * 
				 */

				break;

			case "Create":

				GroupWarningLabel.setText("");
				String GroupName3;
				boolean Ret3 = true;
				GroupName3 = (String) comboBox.getSelectedItem();

				/*
				 * need to be fixed***** yes it need to be fixed
				 * 
				 */
				Ret3 = Globals.clnt.createGroup(GroupName3);

				if (Ret3) {
					Globals.clnt.getLisGroup();
					Globals.GroupCode = Globals.clnt.list;
					GroupWarningLabel.setForeground(new Color(0, 255, 0));
					GroupWarningLabel.setText("Group Created!");
				} else {
					GroupWarningLabel.setForeground(new Color(255, 0, 0));
					GroupWarningLabel.setText("Failed to create Group!");
				}

				break;

			case "Delete":
				String GroupName4;
				GroupName4 = (String) comboBox.getSelectedItem();
				boolean Ret4 = Globals.clnt.deleteGroup(GroupName4);
				if (Ret4) {
					Globals.clnt.getLisGroup();
					Globals.GroupCode = Globals.clnt.list;
					GroupWarningLabel.setForeground(new Color(0, 255, 0));
					GroupWarningLabel.setText("Group Deleted!");
				} else {
					GroupWarningLabel.setForeground(new Color(255, 0, 0));
					GroupWarningLabel.setText("Failed to delete this Group!");
				}
				break;

		}
	}

	/*
	 * @param string groupcode
	 * 
	 * @param name the user's name
	 * 
	 * @param writtenText what the user types
	 * 
	 * The login user will be aligned to the left, the other side will be aligned to
	 * the right.
	 * 
	 */
	public void PutTextToChatTextArea(String groupcode, String name, String writtenText) {

		if (writtenText.length() == 0) {
			return;
		}

		String header = "";
		StyledDocument doc = chattextArea.getStyledDocument();

		if (name.equals(Globals.UserName)) {
			header = "Me : ";
			SimpleAttributeSet left = new SimpleAttributeSet();
			StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
			StyleConstants.setForeground(left, Color.RED);
			StyleConstants.setFontSize(left, 14);
			try {
				int offset = doc.getLength();
				doc.insertString(doc.getLength(), header + writtenText + "\n", left);
				doc.setParagraphAttributes(offset, 1, left, false);
			} catch (BadLocationException e) {

				e.printStackTrace();
			}
		} else if (name.equals("Server")) {
			SimpleAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			StyleConstants.setForeground(center, Color.ORANGE);
			StyleConstants.setFontSize(center, 14);
			try {
				int offset = doc.getLength();
				doc.insertString(doc.getLength(), header + writtenText + "\n", center);
				doc.setParagraphAttributes(offset, 1, center, false);
			} catch (BadLocationException e) {

				e.printStackTrace();
			}
		} else {
			SimpleAttributeSet right = new SimpleAttributeSet();
			StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
			StyleConstants.setForeground(right, Color.BLUE);
			StyleConstants.setFontSize(right, 14);

			try {
				header = " : " + name;
				int offset = doc.getLength();
				doc.insertString(doc.getLength(), writtenText + header + "\n", right);
				doc.setParagraphAttributes(offset, 1, right, false);
			} catch (BadLocationException e) {

				e.printStackTrace();
			}
		}
	}
}

/*
 * public static String[] removeItemFromArray(String[] input, String item) { if
 * (input == null) { return null; } else if (input.length <= 0) { return input;
 * } else { String[] output = new String[input.length - 1]; int count = 0; for
 * (String i : input) { if (!i.equals(item)) { output[count++] = i; } } return
 * output; } }
 * 
 * 
 * public static String[] addItemIntoArray(String[] input, String item) {
 * 
 * 
 * String[] output = new String[input.length+1]; int count = 0; for (String i :
 * input) { output[count++] = i;
 * 
 * } output[count] = item;
 * 
 * return output; }
 * 
 * public void ClearGroupMemberListArea() {
 * 
 * 
 * }
 */
