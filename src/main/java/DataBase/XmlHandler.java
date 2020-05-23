package DataBase;


import java.io.File;


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
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		try {
			 dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.newDocument();
	            // add elements to Document
	            Element rootElement = doc.createElement("Users");
	            // append root element to document
	            doc.appendChild(rootElement);

	            // append first child element to root element

	            rootElement.appendChild(createUserElement(doc, "1", "Nassim", "MotDePasse"));
	            rootElement.appendChild(createUserElement(doc, "2", "Emeline", "MotDePasse"));
	            


	            // for output to file, console
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            // for pretty print
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            DOMSource source = new DOMSource(doc);
	            
	            rootElement.getElementsByTagName("User").item(0).appendChild(createFriendElement(doc, "1", "Marine"));
	            rootElement.getElementsByTagName("User").item(0).appendChild(createFriendElement(doc, "2", "Karina"));



	            // write to console or file
	            StreamResult console = new StreamResult(System.out);
	            StreamResult file = new StreamResult(new File("users.xml"));
	            
	            //DeleteUser(doc, "Emeline");
	            
	            //UpdateUserName(doc, "Emeline", "Elie");
	            
	            //UpdatePassword(doc, "Emeline", "MotDePasse", "Passwd");
	            
	            DeleteFriend(doc, "Nassim", "abdelkader");

	            // write data
	            transformer.transform(source, console);
	            transformer.transform(source, file);
	            
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Node createUserElement(Document doc, String id, String UserName, String Password) {
		Element user = doc.createElement("User");

        // set id attribute
        user.setAttribute("id", id);
        
        

        // create firstName element
        user.appendChild(AddElement(doc, user, "UserName", UserName));

        // create lastName element
        user.appendChild(AddElement(doc, user, "Password", Password));        

        return user;
        
	}
	
	private static Node createFriendElement(Document doc, String id, String FriendName) {
		Element friend = doc.createElement("Friend");
		
		friend.setAttribute("id", id);
		
		friend.appendChild(AddElement(doc, friend, "FriendName", FriendName));
		
		return friend;
		
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
        // Give us the node type, so here --> User
        //System.out.println(user.getNodeName());
       
        // Here we get "FriendName"
        //System.out.println(user.getElementsByTagName("FriendName").item(0).getNodeName());
       
       
        //Give us Marine and Karina. 1er item a changer pour passer d'un amis a un autre.
        //System.out.println(user.getElementsByTagName("FriendName").item(0).getChildNodes().item(0).getNodeValue());
       
        //Display Friend
        //System.out.println(user.getElementsByTagName("Friend").item(0).getNodeName());
       
        if(userTemp == User) {
            NodeList friends = user.getElementsByTagName("Friend");
           
            for( int j = 0 ; j < friends.getLength() ; j++ ) {
                Element friend = (Element) friends.item(j);
                String friendTemp = friend.getElementsByTagName("FriendName").item(0).getFirstChild().getNodeValue();
               
                if(friendTemp == FriendToDelete) {
                    friend.getParentNode().removeChild(friend);
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
				return true;
			}
			
		}
		return false;
		
	}
	

}

