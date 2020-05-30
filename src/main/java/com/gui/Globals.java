package com.gui;

import com.Reseau.Client.Client;

public class Globals {
	    public static String UserName;
		public static char[] Passwd ;
		static Client clnt = new Client("localhost", 6669);
	    // public static String[] Chatters = {"Rebecca", "Nassim", "Marine"};
	    public static String[] Chatters = {};
	    // public static int totalChatters = 0;
	    // public static String[] Freinds = {"Nassim", "Marine", "Karina", "Ilyes"};
	    // public static int totalFreinds = 4;
	    // public static String[] Users = {"Clara", "Meta", "Stella", "Neils"};
	    // public static int totalUsers = 4;
	    // public static String[] GroupMembers = {"Marine", "Nassim",};
	    // public static int totalgroupMembers = 2;
	    public static String[] GroupCode = { "AA", "BB", "CC" };
	    // public static int totalgroupCode = 3;
	    public static String CurrentGroup;
	}
