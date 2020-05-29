package com.dataBase;


import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlHandler {
	private static Document doc;
	private static final String filepath = "users.xml";

	public XmlHandler(String XmlName) {
		doc = initializeXml(XmlName);
	}
	
	public static Document getDocument() {
		return doc;
	}

	public static String getFilepath() {
		return filepath;
	}
	
	public static Document initializeXml(String xmlRoot)
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		try {
			 dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(filepath);
	            // add elements to Document
	            /*Element rootElement = doc.createElement(xmlRoot);
	            // append root element to document
	            doc.appendChild(rootElement);
	            doc.getElementsByTagName(xmlRoot).item(0).appendChild(doc.createElement("Users")); 
	            doc.getElementsByTagName(xmlRoot).item(0).appendChild(doc.createElement("Groups")); */
	            return doc;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public static Document newXml(String xmlRoot)
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		try {
			 dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.newDocument();
	            // add elements to Document
	            Element rootElement = doc.createElement(xmlRoot);
	            // append root element to document
	            doc.appendChild(rootElement);
	            doc.getElementsByTagName(xmlRoot).item(0).appendChild(doc.createElement("Users")); 
	            doc.getElementsByTagName(xmlRoot).item(0).appendChild(doc.createElement("Groups")); 
	            return doc;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean transformerXml() {
		 // for output to file, console
		try {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        // for pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
     // write to console or file
        
        File TempFile = new File("users.xml");
        
        TempFile.createNewFile();
       
        StreamResult file = new StreamResult(TempFile);

        transformer.transform(source, file);

        
        return true;
        }catch (Exception e) {
        	e.printStackTrace();
        }
		
		return false;
	}
	
	public static boolean addUser(String id, String UserName, String Password) {
		Element user = doc.createElement("User");
		

		int setId = getLength();
        // set id attribute
        user.setAttribute(String.valueOf(setId), id);

        // create UserName element
        user.appendChild(addElement(user, "UserName", UserName));

        // create Password element
        user.appendChild(addElement(user, "Password", Password));        

        user.appendChild(doc.createElement("GroupCodes")); 
        user.appendChild(doc.createElement("Friends")); 
        //Link the use element as a child node of the root of doc
        doc.getElementsByTagName("Users").item(0).appendChild(user);
       
        
        transformerXml();
       
        //Check if the children node were added
        if (user.hasChildNodes())
        	return true; 
        else 
        	return false; 
	}
	
	public static int getLength() {
		 if (doc.getElementsByTagName("Users").getLength() != 0) {
		 return doc.getElementsByTagName("Users").getLength();
		 }
		 if (doc.getElementsByTagName("Users").getLength() != 0) {
		 return doc.getElementsByTagName("Users").getLength();
		 }
		 if (doc.getElementsByTagName("Users").getLength() != 0) {
		 return doc.getElementsByTagName("Users").getLength();
		 } else {
		 return 0;
		 }
		 }

	public static boolean addFriend(String User, String FriendName) {
		Element friend = doc.createElement("Friend");
		
		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
		
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString.equals(User) ) {
			
				user.getElementsByTagName("Friends").item(0).appendChild(addElement(friend, "FriendName", FriendName));
				transformerXml();
				return true;
			}
		
		}
		return false;
	}
	
	public static Node addElement(Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
	
	public static boolean deleteUser(String UserToDelete) {
		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
		
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString.equals(UserToDelete)) {
				user.getParentNode().removeChild(user);
				transformerXml();
				return true;
			}
			
			
		}
		return false;
	}
	
    public static boolean deleteFriend(String User, String FriendToDelete) {
    NodeList users = doc.getElementsByTagName("User");
    Element user = null;
   
    for( int i = 0 ; i < users.getLength() ; i++) {
        user = (Element) users.item(i);
        String userTemp = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();

       
        if(userTemp.equals(User) ) {
            NodeList friends = user.getElementsByTagName("FriendName");
           
            for( int j = 0 ; j < friends.getLength() ; j++ ) {
                Element friend = (Element) friends.item(j);
                String friendTemp = friend.getFirstChild().getNodeValue();
               
                if(friendTemp.equals(FriendToDelete) ) {
                    friend.getParentNode().removeChild(friend);
                    transformerXml();
                    return true;
                }
               
            }
            return false;
           
        }
       
    }
    return false;
}
	
	public static boolean updateUserName(String OldUserName, String NewUserName) {
		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
		
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString.equals(OldUserName) ) {
				user.getElementsByTagName("UserName").item(0).getFirstChild().setNodeValue(NewUserName);
				transformerXml();
				return true;
			}
			
			
		}
		return false;
		
	}
	
	public static boolean updatePassword(String UserName, String OldPassword, String NewPassword) {

		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
		
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("Password").item(0).getFirstChild().getNodeValue();
			String TempUserName = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString.equals(OldPassword)  && TempUserName.equals(UserName) ) {
				user.getElementsByTagName("Password").item(0).getFirstChild().setNodeValue(NewPassword);
				transformerXml();
				return true;
			}
			
		}
		return false;
		
	}

	public static boolean addGroupCodeToUser(String Groupcode, String UserName) {
		Element node = doc.createElement("Keys");
		node.appendChild(doc.createTextNode(Groupcode));
		
		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
		
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString.equals(UserName) ) {
			
				user.getElementsByTagName("GroupCodes").item(0).appendChild(node);
				transformerXml();
				return true;
			}
		
		}
		return false;
	}
	
	public static boolean addMessage(String Groupcode, String UserName, String message) {
		
		NodeList group = doc.getElementsByTagName("Group");
		Element groupcode = null;
				
		for(int i = 0; i < group.getLength(); i++) { 
			groupcode = (Element) group.item(i);
			String TempString;	
			
			TempString = groupcode.getElementsByTagName("Groupcode").item(0).getFirstChild().getNodeValue();
			if(TempString.equals(Groupcode)) {
				
				Element user = doc.createElement("UserName");
				user.appendChild(doc.createTextNode(UserName));
				groupcode.appendChild(user); 
	
				Element text = doc.createElement("Message");
		        text.appendChild(doc.createTextNode(message));        
		        groupcode.appendChild(text);
		        
				transformerXml();
				return true;
			}
		}
		return false;
			
	}
	
	public static boolean createGroup(String Groupcode) {
		Element group = doc.createElement("Group");

        // create group element
        group.appendChild(addElement(group, "Groupcode", Groupcode));        

        //Link the use element as a child node of the root of doc
        doc.getElementsByTagName("Groups").item(0).appendChild(group);
        
        transformerXml();
       
        //Check if the children node were added
        if (group.hasChildNodes())
        	return true; 
        else 
        	return false; 
				
	}
	
	public static boolean deleteMessage(String Groupcode, String UserName, String message) {
		NodeList group = doc.getElementsByTagName("Group");
		Element groupcode = null;
				
		for(int i = 0; i < group.getLength(); i++) { 
			groupcode = (Element) group.item(i);
			String TempString;	
			
			TempString = groupcode.getElementsByTagName("Groupcode").item(0).getFirstChild().getNodeValue();
			if(TempString.equals(Groupcode)  ) {
				
				NodeList UserNames = doc.getElementsByTagName("UserName");
				Element user = null;
				
				for(int j=0; j<UserNames.getLength(); j++) {
					
					user = (Element) UserNames.item(j);
					TempString = user.getFirstChild().getNodeValue();
					
					if(TempString.equals(UserName)) {
						
						NodeList MessageList = doc.getElementsByTagName("Message");
						Element TempMessage = null;
						
						for(int k=0; k<MessageList.getLength(); k++) {
							TempMessage = (Element) MessageList.item(k);
							TempString = TempMessage.getFirstChild().getNodeValue();

							if(TempString.equals(message)) {
			                    TempMessage.getParentNode().removeChild(TempMessage);
			                    user.getParentNode().removeChild(user);
								transformerXml();
								return true;
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public static boolean deleteGroup(String Groupcode, String UserName) {
		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString.equals(UserName) ) {
				NodeList GroupeCodes = doc.getElementsByTagName("Keys");
				Element groupcode = null;
				
				for(int k=0; k<GroupeCodes.getLength(); k++) {
					groupcode = (Element) GroupeCodes.item(k);
					TempString = groupcode.getFirstChild().getNodeValue();

					if(TempString.equals(Groupcode) ) {
	                    groupcode.getParentNode().removeChild(groupcode);
						transformerXml();
						return true;
					}
				}
				
			}
			
			
		}
		return false;
	}
	
	public static String[][] displayHistory(String Groupcode, int numberOfMessages) {
		
		String[][] history = new String[numberOfMessages][2];
		NodeList group = doc.getElementsByTagName("Group");
		Element groupcode = null;
				
		for(int i = 0; i < group.getLength(); i++) { 
			groupcode = (Element) group.item(i);
			String TempString;	
			
			TempString = groupcode.getElementsByTagName("Groupcode").item(0).getFirstChild().getNodeValue();
			if(TempString.equals(Groupcode) ) {		
				int stringCapacity = 0 ; 
				
				for( int j = groupcode.getChildNodes().getLength()-1 ; stringCapacity < numberOfMessages && j >=1; j--) {
				
					
					history[stringCapacity][0] = groupcode.getChildNodes().item(j).getFirstChild().getNodeValue();
					
					history[stringCapacity][1] = groupcode.getChildNodes().item(--j).getFirstChild().getNodeValue();
					
					stringCapacity++; 
					
				}
			}
		}
		return history; 
	}
	
	public static String[] listUser() {
		
		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
		String[] usersList = new String[users.getLength()];
		
		for(int i=0; i<users.getLength(); i++) {
			user = (Element) users.item(i);
			usersList[i] = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
		}
		
		return usersList;
	}

	public static String[] listFriend(String UserName) {

		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString.equals(UserName) ) {
			NodeList friends = user.getElementsByTagName("Friends").item(0).getChildNodes(); 
				Element friend = null;
				String[] ListFriend = new String[friends.getLength()]; 

				for(int j=0; j < friends.getLength(); j++) {
					
					friend = (Element) friends.item(j);
					ListFriend[j] = friend.getFirstChild().getNodeValue();
					
				}
				return ListFriend;
			}
		
		}
		return null;
		
	}
	
	public static String[] listOfGroupsOfAUser(String UserName) {
        String[] ListOfGroups;
       
        NodeList users = doc.getElementsByTagName("User");
        Element user = null;
       
        for(int i = 0; i < users.getLength(); i++) {
            user = (Element) users.item(i);
            String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
           
            if(TempString.equals(UserName)) {
           
                NodeList groups = user.getElementsByTagName("GroupCodes").item(0).getChildNodes();
                Element group = null;
                ListOfGroups = new String[groups.getLength()];
                for(int j = 0 ; j < groups.getLength(); j++) {       
                    group = (Element) groups.item(j);
                   
                    ListOfGroups[j] = group.getFirstChild().getNodeValue();            
                }
               
                return ListOfGroups;
            }
       
        }
       
        return null;    
       
    }
	
	public static String[] listOfUsersInGroup(String GroupCode) {
	        List<String> DynamicList = new ArrayList<>();
	       
	        NodeList users = doc.getElementsByTagName("User");
	        Element user = null;
	       
	        //We go through our nodelist of users
	        for(int i = 0; i < users.getLength(); i++) {
	            user = (Element) users.item(i);
	            String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
	           
	            //We get the groups in which the user is in
	            String[] GroupCodesList = listOfGroupsOfAUser(TempString);
	           
	            if(inTheList(GroupCodesList, GroupCode)) {
	                DynamicList.add(TempString);
	            }
	               
	        }
	        String[] ListOfUsersInGroup = DynamicList.toArray(new String[0]);
	       
	       
	        return ListOfUsersInGroup;
	    }
	   
	public static boolean inTheList(String[] list, String value) {
	       
	        for(int i = 0; i < list.length; i++) {
	            if( list[i] == value ) {
	                return true;
	            }
	        }
	        return false;
	    }
	
	public static boolean checkingLogins(Document doc , String UserName, String Password) {
        Element user = null;
        NodeList users = doc.getElementsByTagName("User");
       
        for(int i = 0; i < users.getLength() ; i++) {
            user = (Element) users.item(i);
            String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
            System.out.println(TempString+UserName);
            if(TempString.equals(UserName)) {
                if(user.getElementsByTagName("Password").item(0).getFirstChild().getNodeValue().equals(Password))
                        return true;
            }
           
        }
        return false;
    }
	//fonctions nécessaires toutes faites
	
	
}

