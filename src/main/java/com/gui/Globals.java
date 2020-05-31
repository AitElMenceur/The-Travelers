package com.gui;

import java.util.ArrayList;

import com.Reseau.Client.Client;
import com.Reseau.Data.Message;


public class Globals {
	public static String UserName;
	public static char[] Passwd;
	public static Client clnt = new Client("localhost", 6668);
	public static ChatGUI chatGUI;
	public static Message message=null;
	public static String[] Chatters = {};
	//public static ArrayList<String> GroupCode = new ArrayList<String>() ;
	public static String[] GroupCode;

	public static String CurrentGroup;
}
