package DataBase;


import java.io.File;
import java.nio.file.Files;

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
				
				addFriend(doc, "1", "Nassim", "Marine");
				addGroupCodeToUser(doc, "1", "Nassim"); 
				addFriend(doc, "1", "Nassim", "karina");
				addGroupCodeToUser(doc, "2", "Nassim"); 
	            
	            //DeleteUser(doc, "Nassim");
	            
	            //UpdateUserName(doc, "Nassim", "Elie");
	            
	            //UpdatePassword(doc, "Nassim", "MotDePasse", "Passwd");
	            
	            //DeleteFriend(doc, "Nassim", "Marine");

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
	
	private static boolean addFriend(Document doc, String id, String User, String FriendName) {
		Element friend = doc.createElement("Friend");
		
		friend.setAttribute("id", id);
		
		friend.appendChild(AddElement(doc, friend, "FriendName", FriendName));
		
		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
		
				
		for(int i = 0; i < users.getLength(); i++) { 
			user = (Element) users.item(i);
			String TempString = user.getElementsByTagName("UserName").item(0).getFirstChild().getNodeValue();
			
			if(TempString == User) {
			
				user.getElementsByTagName("Friends").item(0).appendChild(friend);
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
            NodeList friends = user.getElementsByTagName("Friend");
           
            for( int j = 0 ; j < friends.getLength() ; j++ ) {
                Element friend = (Element) friends.item(j);
                String friendTemp = friend.getElementsByTagName("FriendName").item(0).getFirstChild().getNodeValue();
               
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
		
		NodeList groups = doc.getElementsByTagName("Groups");
		Element group = null;
				
		for(int i = 0; i < groups.getLength(); i++) { 
			group = (Element) groups.item(i);
			String TempString;	
			
			TempString = group.getElementsByTagName("GroupCode").item(0).getFirstChild().getNodeValue();
			if(TempString == Groupcode) {
					//DO THE CREATE GROUP FUNCTION FIRST 
					transformerXml(doc);
			}
		}
			
	
	}
	//createGroup
	//deleteMessage
	//DeleteGroup
	//Display 30 last messages 
	
}

