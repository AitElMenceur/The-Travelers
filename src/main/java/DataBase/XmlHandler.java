package DataBase;


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
	public static void main(String[] args) {	
		
				Document doc = initializeXml("Database");
				Document historyDoc = initializeXml("Groups");
			
				addUser(doc, "1", "Nassim", "MotDePasse");
				addUser(doc, "2", "Karina", "Passwd");
				addUser(doc, "3", "Emeline", "Mahmoud");
				
				addFriend(doc, "Nassim", "Marine");
				addGroupCodeToUser(doc, "1", "Nassim"); 
				addFriend(doc, "Nassim", "Karina");
				addGroupCodeToUser(doc, "2", "Nassim"); 
				createGroup(doc, "1");
				createGroup(doc, "2");
				
				AddMessage(doc, "2", "Nassim", "Salam les amis");
				AddMessage(doc, "2", "Marine", "Et Languet ton moman");

				//deleteMessage(doc, "2", "Marine", "Languet ton moman");
				
				String[][] history = displayHistory(doc, "2", 4); 
				
				String[] listUsers = ListFriend(doc, "Nassim");		
				
				//deleteGroup(doc, "2", "Nassim"); 

	            //DeleteUser(doc, "Nassim");
	            
	            //UpdateUserName(doc, "Nassim", "Elie");
	            
	            //UpdatePassword(doc, "Nassim", "MotDePasse", "Passwd");
	            
	            //DeleteFriend(doc, "Nassim", "Marine");
	            //DeleteFriend(doc, "Nassim", "karina");

				String[] list = ListOfGroupsOfAUser(doc, "Nassim");
	               
                for(int i = 0 ; i < list.length; i++) {
                    System.out.println(list[i]);
                }

	}
	
	private static Document initializeXml(String xmlRoot)
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
	
	private static boolean transformerXml(Document doc) {
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
       
        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(TempFile);

        transformer.transform(source, console);
        transformer.transform(source, file);

        
        return true;
        }catch (Exception e) {
        	e.printStackTrace();
        }
		
		return false;
	}
	
	private static boolean addUser(Document doc, String id, String UserName, String Password) {
		Element user = doc.createElement("User");

        // set id attribute
        user.setAttribute("id", id);

        // create UserName element
        user.appendChild(AddElement(doc, user, "UserName", UserName));

        // create Password element
        user.appendChild(AddElement(doc, user, "Password", Password));        

        user.appendChild(doc.createElement("GroupCodes")); 
        user.appendChild(doc.createElement("Friends")); 
        //Link the use element as a child node of the root of doc
        doc.getElementsByTagName("Users").item(0).appendChild(user);
       
        
        transformerXml(doc);
       
        //Check if the children node were added
        if (user.hasChildNodes())
        	return true; 
        else 
        	return false; 
	}
	
	private static boolean addFriend(Document doc, String User, String FriendName) {
		Element friend = doc.createElement("Friend");
		
		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
		
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString == User) {
			
				user.getElementsByTagName("Friends").item(0).appendChild(AddElement(doc, friend, "FriendName", FriendName));
				transformerXml(doc);
				return true;
			}
		
		}
		return false;
	}
	
	private static Node AddElement(Document doc, Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
	
	private static boolean DeleteUser(Document doc, String UserToDelete) {
		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
		
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString == UserToDelete) {
				user.getParentNode().removeChild(user);
				transformerXml(doc);
				return true;
			}
			
			
		}
		return false;
	}
	
    private static boolean DeleteFriend(Document doc, String User, String FriendToDelete) {
    NodeList users = doc.getElementsByTagName("User");
    Element user = null;
   
    for( int i = 0 ; i < users.getLength() ; i++) {
        user = (Element) users.item(i);
        String userTemp = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();

       
        if(userTemp == User) {
            NodeList friends = user.getElementsByTagName("FriendName");
           
            for( int j = 0 ; j < friends.getLength() ; j++ ) {
                Element friend = (Element) friends.item(j);
                String friendTemp = friend.getFirstChild().getNodeValue();
               
                if(friendTemp == FriendToDelete) {
                    friend.getParentNode().removeChild(friend);
                    transformerXml(doc);
                    return true;
                }
               
            }
            return false;
           
        }
       
    }
    return false;
}
	
	private static boolean UpdateUserName(Document doc, String OldUserName, String NewUserName) {
		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
		
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString == OldUserName) {
				user.getElementsByTagName("UserName").item(0).getFirstChild().setNodeValue(NewUserName);
				transformerXml(doc);
				return true;
			}
			
			
		}
		return false;
		
	}
	
	private static boolean UpdatePassword(Document doc, String UserName, String OldPassword, String NewPassword) {

		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
		
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("Password").item(0).getFirstChild().getNodeValue();
			String TempUserName = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString == OldPassword && TempUserName == UserName) {
				user.getElementsByTagName("Password").item(0).getFirstChild().setNodeValue(NewPassword);
				transformerXml(doc);
				return true;
			}
			
		}
		return false;
		
	}

	private static boolean addGroupCodeToUser(Document doc, String Groupcode, String UserName) {
		Element node = doc.createElement("Keys");
		node.appendChild(doc.createTextNode(Groupcode));
		
		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
		
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString == UserName) {
			
				user.getElementsByTagName("GroupCodes").item(0).appendChild(node);
				transformerXml(doc);
				return true;
			}
		
		}
		return false;
	}
	
	private static boolean AddMessage(Document doc, String Groupcode, String UserName, String message) {
		
		NodeList group = doc.getElementsByTagName("Group");
		Element groupcode = null;
				
		for(int i = 0; i < group.getLength(); i++) { 
			groupcode = (Element) group.item(i);
			String TempString;	
			
			TempString = groupcode.getElementsByTagName("Groupcode").item(0).getFirstChild().getNodeValue();
			if(TempString == Groupcode) {
				
				Element user = doc.createElement("UserName");
				user.appendChild(doc.createTextNode(UserName));
				groupcode.appendChild(user); 
	
				Element text = doc.createElement("Message");
		        text.appendChild(doc.createTextNode(message));        
		        groupcode.appendChild(text);
		        
				transformerXml(doc);
				return true;
			}
		}
		return false;
			
	}
	
	private static boolean createGroup(Document doc, String Groupcode) {
		Element group = doc.createElement("Group");

        // create group element
        group.appendChild(AddElement(doc, group, "Groupcode", Groupcode));        

        //Link the use element as a child node of the root of doc
        doc.getElementsByTagName("Groups").item(0).appendChild(group);
        
        transformerXml(doc);
       
        //Check if the children node were added
        if (group.hasChildNodes())
        	return true; 
        else 
        	return false; 
				
	}
	
	private static boolean deleteMessage(Document doc, String Groupcode, String UserName, String message) {
		NodeList group = doc.getElementsByTagName("Group");
		Element groupcode = null;
				
		for(int i = 0; i < group.getLength(); i++) { 
			groupcode = (Element) group.item(i);
			String TempString;	
			
			TempString = groupcode.getElementsByTagName("Groupcode").item(0).getFirstChild().getNodeValue();
			if(TempString == Groupcode) {
				
				NodeList UserNames = doc.getElementsByTagName("UserName");
				Element user = null;
				
				for(int j=0; j<UserNames.getLength(); j++) {
					
					user = (Element) UserNames.item(j);
					TempString = user.getFirstChild().getNodeValue();
					
					if(TempString == UserName) {
						
						NodeList MessageList = doc.getElementsByTagName("Message");
						Element TempMessage = null;
						
						for(int k=0; k<MessageList.getLength(); k++) {
							TempMessage = (Element) MessageList.item(k);
							TempString = TempMessage.getFirstChild().getNodeValue();

							if(TempString == message) {
			                    TempMessage.getParentNode().removeChild(TempMessage);
			                    user.getParentNode().removeChild(user);
								transformerXml(doc);
								return true;
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	private static boolean deleteGroup(Document doc, String Groupcode, String UserName) {
		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString == UserName) {
				NodeList GroupeCodes = doc.getElementsByTagName("Keys");
				Element groupcode = null;
				
				for(int k=0; k<GroupeCodes.getLength(); k++) {
					groupcode = (Element) GroupeCodes.item(k);
					TempString = groupcode.getFirstChild().getNodeValue();

					if(TempString == Groupcode) {
	                    groupcode.getParentNode().removeChild(groupcode);
						transformerXml(doc);
						return true;
					}
				}
				
			}
			
			
		}
		return false;
	}
	

	private static String[][] displayHistory(Document doc, String Groupcode, int numberOfMessages) {
		
		String[][] history = new String[numberOfMessages][2];
		NodeList group = doc.getElementsByTagName("Group");
		Element groupcode = null;
				
		for(int i = 0; i < group.getLength(); i++) { 
			groupcode = (Element) group.item(i);
			String TempString;	
			
			TempString = groupcode.getElementsByTagName("Groupcode").item(0).getFirstChild().getNodeValue();
			if(TempString == Groupcode) {		
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
	
	private static String[] ListUser(Document doc) {
		
		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
		String[] usersList = new String[users.getLength()];
		
		for(int i=0; i<users.getLength(); i++) {
			user = (Element) users.item(i);
			usersList[i] = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
		}
		
		return usersList;
	}

	private static String[] ListFriend(Document doc, String UserName) {

		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString == UserName) {
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
	
	public static String[] ListOfGroupsOfAUser(Document doc, String UserName) {
        String[] ListOfGroups;
       
        NodeList users = doc.getElementsByTagName("User");
        Element user = null;
       
        for(int i = 0; i < users.getLength(); i++) {
            user = (Element) users.item(i);
            String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
           
            if(TempString == UserName) {
           
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
	
	public static String[] ListOfUsersInGroup(Document doc, String GroupCode) {
	        List<String> DynamicList = new ArrayList<>();
	       
	        NodeList users = doc.getElementsByTagName("User");
	        Element user = null;
	       
	        //We go through our nodelist of users
	        for(int i = 0; i < users.getLength(); i++) {
	            user = (Element) users.item(i);
	            String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
	           
	            //We get the groups in which the user is in
	            String[] GroupCodesList = ListOfGroupsOfAUser(doc, TempString);
	           
	            if(InTheList(GroupCodesList, GroupCode)) {
	                DynamicList.add(TempString);
	            }
	               
	        }
	        String[] ListOfUsersInGroup = DynamicList.toArray(new String[0]);
	       
	       
	        return ListOfUsersInGroup;
	    }
	   
	private static boolean InTheList(String[] list, String value) {
	       
	        for(int i = 0; i < list.length; i++) {
	            if( list[i] == value ) {
	                return true;
	            }
	        }
	        return false;
	    }
	
	private static boolean CheckingLogins(Document doc , String UserName, String Password) {
        Element user = null;
        NodeList users = doc.getElementsByTagName("User");
       
        for(int i = 0; i < users.getLength() ; i++) {
            user = (Element) users.item(i);
            String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
           
            if(TempString == UserName) {
                if(user.getElementsByTagName("Password").item(0).getFirstChild().getNodeValue() == Password)
                        return true;
            }
           
        }
        return false;
    }
	//fonctions nécessaires toutes faites
	
	
}

