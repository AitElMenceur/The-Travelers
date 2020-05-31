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
	public static ArrayList<String> GroupCode = initGroupCode() ;
	public static String CurrentGroup;

	public static ArrayList<String> initGroupCode() {
		ArrayList<String> gp = new ArrayList<String>();
		for (String g : clnt.getLisGroup()) {
			gp.add(g);
			}
			return gp;
			
		}
	}
