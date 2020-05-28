    private static Document initializeXml
--> needs to be call once, before using the following functions. The value returned value needs to be stored and keep in order to use the other functions. 
    

    private static boolean addUser(Document doc, String id, String UserName, String Password)
--> for the "id" string, its supposed to be a number which identify the user. 

    private static boolean addFriend(Document doc, String User, String FriendName)

    private static boolean DeleteUser(Document doc, String UserToDelete)

    private static boolean DeleteFriend(Document doc, String User, String FriendToDelete)

    private static boolean UpdateUserName(Document doc, String OldUserName, String NewUserName) 

    private static boolean UpdatePassword(Document doc, String UserName, String OldPassword, String NewPassword)

(New function added on 26/05)

    private static boolean addGroupCodeToUser(Document doc, String Groupcode, String UserName)

    private static boolean AddMessage(Document doc, String Groupcode, String UserName, String message)

    private static boolean createGroup(Document doc, String Groupcode)

    private static boolean deleteMessage(Document doc, String Groupcode, String UserName, String message)

    private static boolean deleteGroup(Document doc, String Groupcode, String UserName)

    private static String[][] displayHistory(Document doc, String Groupcode, int numberOfMessages)
--> numberOfMessages represents how many messages we want the database to send back to the GUI.
-->String [NumberOfMessages][2] : so "Number of messages " lines and 2 columns. In column 0, we stock the message and in column 1 the user who send it. 

    private static String[] ListUser(Document doc)

    private static String[] ListFriend(Document doc, String UserName)

    public static String[] ListOfGroupsOfAUser(Document doc, String UserName)

    public static String[] ListOfUsersInGroup(Document doc, String GroupCode)

    private static boolean InTheList(String[] list, String value)
-->Check if the value given is in the list 

    private static boolean CheckingLogins(Document doc , String UserName, String Password)	
-->Check that the logins 

    

    




