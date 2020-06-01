package com.XmlTest;


import static org.junit.Assert.*;
import java.io.StringWriter;


import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.dataBase.XmlHandler;
	
public class XmlHandlerTest {
		XmlHandler doc = new XmlHandler("Database"); 
		

	@Test
	public void testGetDocument() {
		assertEquals( "objects should be identical _ get Document ", XmlHandler.getDocument().getFirstChild().getNodeName(), "Database" ); 
	}

	@Test
	public void testGetFilepath() {
		String path = XmlHandler.getFilepath(); 
		assertEquals( "objects should be identical _ get file path  ",path, "\\src\\test\\java\\com\\XmlTest\\" ); 
	}

	@Test
	public void testInitializeXml() {
		assertEquals( "objects should be identical _ Initiatize Xml ", XmlHandler.initializeXml("Database").getFirstChild().getNodeName(), "Database" ); 
	}

	@Test
	public void testNewXml() {
		assertEquals( "objects should be identical _ New Xml  ", XmlHandler.newXml("Database").getFirstChild().getNodeName(), "Database" ); 
	}

	@Test
	public void testTransformerXml() {
		assertEquals( "objects should be identical _ transformer Xml", XmlHandler.getDocument().getFirstChild().getNodeName(), "Database" ); 
	}

	@Test
	public void testAddUser() {

		XmlHandler.addUser("Nassim", "MotDePasse"); 
		
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    try {
	        transformer = tf.newTransformer();

	        StringWriter writer = new StringWriter();
	 
	        //transform document to string 
	        transformer.transform(new DOMSource(XmlHandler.getDocument()), new StreamResult(writer));
	 
	        String xmlString = writer.getBuffer().toString();   
	        
	        
	        String ExpectedStringResult ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Database><Users><User id=\"0\"><UserName>Nassim</UserName><Password>MotDePasse</Password><GroupCodes/><Friends/></User></Users><Groups/></Database>";
	        
	    
	        
	        assertEquals( "objects should be identical _ Add User ", ExpectedStringResult, xmlString ); 
	        
	    } 
	    catch (TransformerException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	    
	    
		
		
	}

	@Test
	public void testGetLength() {
		int numberOfUser = XmlHandler.getLength(); 
		int expectedNumber = 1; 
		
		assertEquals( "objects should be identical _ Add User ", expectedNumber, numberOfUser ); 
	}

	@Test
	public void testAddFriend() {
        XmlHandler.addFriend("Nassim", "Marine"); 
		
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    try {
	        transformer = tf.newTransformer();

	        StringWriter writer = new StringWriter();
	 
	        //transform document to string 
	        transformer.transform(new DOMSource(XmlHandler.getDocument()), new StreamResult(writer));
	 
	        String xmlString = writer.getBuffer().toString();   

	        
	        String ExpectedStringResult ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Database><Users><User id=\"0\"><UserName>Nassim</UserName><Password>MotDePasse</Password><GroupCodes/><Friends><FriendName>Marine</FriendName></Friends></User></Users><Groups/></Database>";
	        

	        XmlHandler.deleteFriend("Nassim", "Marine"); 
	        
	        assertEquals( "objects should be identical _ Add User ", ExpectedStringResult, xmlString ); 
	        
	    } 
	    catch (TransformerException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}

	@Test
	public void testAddElement() {
		NodeList users = XmlHandler.getDocument().getElementsByTagName("User"); 
		Element user = (Element) users.item(0); 
		
		org.w3c.dom.Node node = XmlHandler.addElement(user,"UserName", "Marine");
		
		String result = "<"+node.getNodeName()+">"+node.getFirstChild().getNodeValue()+"</"+node.getNodeName()+">";
		String expectedResult = "<UserName>Marine</UserName>"; 
		

		assertEquals("objects should be identical _ add Element ", expectedResult, result); 
		
		
	}

	@Test
	public void testDeleteUser() {
		XmlHandler.addUser("Marine", "MotDePasse"); 
		XmlHandler.deleteUser("Marine"); 
		
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    try {
	        transformer = tf.newTransformer();

	        StringWriter writer = new StringWriter();
	 
	        //transform document to string 
	        transformer.transform(new DOMSource(XmlHandler.getDocument()), new StreamResult(writer));
	 
	        String xmlString = writer.getBuffer().toString();   

	        
	        String ExpectedStringResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Database><Users><User id=\"0\"><UserName>Nassim</UserName><Password>MotDePasse</Password><GroupCodes/><Friends/></User></Users><Groups/></Database>";

	        assertEquals( "objects should be identical _ Add User ", ExpectedStringResult, xmlString ); 
	        
	    } 
	    catch (TransformerException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}

	@Test
	public void testDeleteFriend() {
		XmlHandler.addFriend("Nassim", "Karina"); 
		XmlHandler.deleteFriend("Nassim", "Karina"); 
		
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    try {
	        transformer = tf.newTransformer();

	        StringWriter writer = new StringWriter();
	 
	        //transform document to string 
	        transformer.transform(new DOMSource(XmlHandler.getDocument()), new StreamResult(writer));
	 
	        String xmlString = writer.getBuffer().toString();   

	        
	        String ExpectedStringResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Database><Users><User id=\"0\"><UserName>Nassim</UserName><Password>MotDePasse</Password><GroupCodes/><Friends/></User></Users><Groups/></Database>";
	        

	        
	        assertEquals( "objects should be identical _ Add User ", ExpectedStringResult, xmlString ); 
	        
	    } 
	    catch (TransformerException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}

	@Test
	public void testUpdateUserName() {
		XmlHandler.addUser("Karina", "vert"); 
		XmlHandler.updateUserName("Karina", "Marine"); 
		
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    try {
	        transformer = tf.newTransformer();

	        StringWriter writer = new StringWriter();
	 
	        //transform document to string 
	        transformer.transform(new DOMSource(XmlHandler.getDocument()), new StreamResult(writer));
	 
	        String xmlString = writer.getBuffer().toString();   

	        
	        String ExpectedStringResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Database><Users><User id=\"0\"><UserName>Nassim</UserName><Password>MotDePasse</Password><GroupCodes/><Friends/></User><User id=\"1\"><UserName>Marine</UserName><Password>vert</Password><GroupCodes/><Friends/></User></Users><Groups/></Database>";

	        XmlHandler.deleteUser("Marine"); 
	        assertEquals( "objects should be identical _ Add User ", ExpectedStringResult, xmlString ); 
	        
	    } 
	    catch (TransformerException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}

	@Test
	public void testUpdatePassword() {
		XmlHandler.addUser("Julie", "vert"); 
		XmlHandler.updatePassword("Julie", "vert", "jaune"); 
		
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    try {
	        transformer = tf.newTransformer();

	        StringWriter writer = new StringWriter();
	 
	        //transform document to string 
	        transformer.transform(new DOMSource(XmlHandler.getDocument()), new StreamResult(writer));
	 
	        String xmlString = writer.getBuffer().toString();   

	        
	        String ExpectedStringResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Database><Users><User id=\"0\"><UserName>Nassim</UserName><Password>MotDePasse</Password><GroupCodes/><Friends/></User><User id=\"1\"><UserName>Julie</UserName><Password>jaune</Password><GroupCodes/><Friends/></User></Users><Groups/></Database>";
	        
	

	        XmlHandler.deleteUser("Julie"); 
	        assertEquals( "objects should be identical _ update password ", ExpectedStringResult, xmlString ); 
	        
	    } 
	    catch (TransformerException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}

	@Test
	public void testAddGroupCodeToUser() {
		XmlHandler.createGroup("FRUIT"); 
		XmlHandler.addGroupCodeToUser("FRUIT", "Nassim"); 
		
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    try {
	        transformer = tf.newTransformer();

	        StringWriter writer = new StringWriter();
	 
	        //transform document to string 
	        transformer.transform(new DOMSource(XmlHandler.getDocument()), new StreamResult(writer));
	 
	        String xmlString = writer.getBuffer().toString();   

	        
	        String ExpectedStringResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Database><Users/><Groups><Group><Groupcode>FRUIT</Groupcode></Group></Groups></Database>"; 



	        XmlHandler.deleteGroupcodeOfAUser("FRUIT", "Nassim");
	        XmlHandler.deleteGroup("FRUIT"); 
	        assertEquals( "objects should be identical _ update password ", ExpectedStringResult, xmlString ); 
	        
	    } 
	    catch (TransformerException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
		
		
		
		
	}

	@Test
	public void testAddMessage() {
		XmlHandler.createGroup("FRUIT"); 
		XmlHandler.addGroupCodeToUser("FRUIT", "Nassim"); 
		XmlHandler.addMessage("FRUIT", "Nassim", "Les Junit c'est chiant");
		
		
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    try {
	        transformer = tf.newTransformer();

	        StringWriter writer = new StringWriter();
	 
	        //transform document to string 
	        transformer.transform(new DOMSource(XmlHandler.getDocument()), new StreamResult(writer));
	 
	        String xmlString = writer.getBuffer().toString();   

	        
	        String ExpectedStringResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Database><Users/><Groups><Group><Groupcode>FRUIT</Groupcode><UserName>Nassim</UserName><Message>Les Junit c'est chiant</Message></Group></Groups></Database>"; 

	        

	        XmlHandler.deleteGroupcodeOfAUser("FRUIT", "Nassim");
	        XmlHandler.deleteGroup("FRUIT"); 
	        assertEquals( "objects should be identical _ update password ", ExpectedStringResult, xmlString ); 
	        
	    } 
	    catch (TransformerException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}

	@Test
	public void testCreateGroup() {
		XmlHandler.createGroup("PROPAGANDE");  
		
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    try {
	        transformer = tf.newTransformer();

	        StringWriter writer = new StringWriter();
	 
	        //transform document to string 
	        transformer.transform(new DOMSource(XmlHandler.getDocument()), new StreamResult(writer));
	 
	        String xmlString = writer.getBuffer().toString();   

	        String ExpectedStringResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Database><Users><User id=\"0\"><UserName>Nassim</UserName><Password>MotDePasse</Password><GroupCodes/><Friends/></User></Users><Groups><Group><Groupcode>PROPAGANDE</Groupcode></Group></Groups></Database>"; 

	        XmlHandler.deleteGroup("PROPAGANDE"); 
	        assertEquals( "objects should be identical _ update password ", ExpectedStringResult, xmlString ); 
	        
	    } 
	    catch (TransformerException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}

	@Test
	public void testDeleteMessage() {
		XmlHandler.createGroup("FRUIT"); 
		XmlHandler.addGroupCodeToUser("FRUIT", "Nassim"); 
		XmlHandler.addMessage("FRUIT", "Nassim", "Les Junit c'est chiant");
		XmlHandler.deleteMessage("FRUIT", "Nassim", "Les Junit c'est chiant"); 
		
		
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    try {
	        transformer = tf.newTransformer();

	        StringWriter writer = new StringWriter();
	 
	        //transform document to string 
	        transformer.transform(new DOMSource(XmlHandler.getDocument()), new StreamResult(writer));
	 
	        String xmlString = writer.getBuffer().toString();   

	        
	        String ExpectedStringResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Database><Users/><Groups><Group><Groupcode>FRUIT</Groupcode></Group></Groups></Database>"; 

	        

	        XmlHandler.deleteGroupcodeOfAUser("FRUIT", "Nassim");
	        XmlHandler.deleteGroup("FRUIT"); 
	        assertEquals( "objects should be identical _ update password ", ExpectedStringResult, xmlString ); 
	        
	    } 
	    catch (TransformerException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}

	@Test
	public void testDeleteGroupcodeOfAUser() {
		XmlHandler.createGroup("STALINE"); 
		XmlHandler.addGroupCodeToUser("STALINE", "Nassim"); 
		XmlHandler.deleteGroupcodeOfAUser("STALINE", "Nassim");
		
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    try {
	        transformer = tf.newTransformer();

	        StringWriter writer = new StringWriter();
	 
	        //transform document to string 
	        transformer.transform(new DOMSource(XmlHandler.getDocument()), new StreamResult(writer));
	 
	        String xmlString = writer.getBuffer().toString();   

	        
	        String ExpectedStringResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Database><Users/><Groups><Group><Groupcode>STALINE</Groupcode></Group></Groups></Database>"; 

	       
	        XmlHandler.deleteGroup("STALINE"); 
	        assertEquals( "objects should be identical _ update password ", ExpectedStringResult, xmlString ); 
	        
	    } 
	    catch (TransformerException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}

	@Test
	public void testDeleteGroup() {
		XmlHandler.createGroup("FRONT NATIONALE"); 
		XmlHandler.deleteGroup("FRONT NATIONALE"); 
		
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    try {
	        transformer = tf.newTransformer();

	        StringWriter writer = new StringWriter();
	 
	        //transform document to string 
	        transformer.transform(new DOMSource(XmlHandler.getDocument()), new StreamResult(writer));
	 
	        String xmlString = writer.getBuffer().toString();   

	        
	        String ExpectedStringResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Database><Users/><Groups/></Database>"; 

	       
	        XmlHandler.deleteGroup("STALINE"); 
	        assertEquals( "objects should be identical _ update password ", ExpectedStringResult, xmlString ); 
	        
	    } 
	    catch (TransformerException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}

	@Test
	public void testDisplayHistory() {
		XmlHandler.createGroup("REWING"); 
		XmlHandler.addGroupCodeToUser("REWING", "Nassim"); 
		XmlHandler.addMessage("REWING", "Nassim", "Les Junit c'est chiant");
		
		String[][] expectedResult = {{"Les Junit c'est chiant", "Nassim"}};  
		String[][] result = XmlHandler.displayHistory("REWING", 30); 
		
		XmlHandler.deleteGroupcodeOfAUser("REWING", "Nassim");
        XmlHandler.deleteGroup("REWING"); 

        if(expectedResult[0][0].equals(result[0][0]))
        	assertEquals("objects should be identical _ list of groups of a user ", expectedResult[0][1], result[0][1]); 
			
	}

	@Test
	public void testListUser() {
		String[] list = XmlHandler.listUser(); 
		int expectedResult = 1; 
		assertEquals("objects should be identical _ list of users", list.length, expectedResult); 
	}

	@Test
	public void testListFriend() {
		String[] list = XmlHandler.listFriend("Nassim"); 
		int expectedResult = 0; 
		assertEquals("objects should be identical _ list of friends of a user ", list.length, expectedResult); 
		
	}

	@Test
	public void testListOfGroupsOfAUser() {
		XmlHandler.addGroupCodeToUser("A", "Nassim"); 
		XmlHandler.addGroupCodeToUser("B", "Nassim"); 
		
		int expectedResult = 0;
		String[] list = XmlHandler.listOfGroupsOfAUser("Nassim"); 
		
		XmlHandler.deleteGroupcodeOfAUser("A", "Nassim"); 
		XmlHandler.deleteGroupcodeOfAUser("B", "Nassim"); 
		
		assertEquals("objects should be identical _ list of groups of a user ", expectedResult, list.length); 
	}

	@Test
	public void testListOfUsersInGroup() {
		XmlHandler.createGroup("COMPTEZ-VOUS"); 
		XmlHandler.addGroupCodeToUser("COMPTEZ-VOUS", "Nassim"); 
		
		String[] list = XmlHandler.listOfUsersInGroup("COMPTEZ-VOUS"); 
		int expectedResult = 1; 
		
		XmlHandler.deleteGroupcodeOfAUser("COMPTEZ-VOUS", "Nassim"); 
		XmlHandler.deleteGroup("COMPTEZ-VOUS"); 
		
		assertEquals("objects should be identical _ list of users in group ", list.length, expectedResult); 
		
	}

	@Test
	public void testListOfGroups() {
		XmlHandler.createGroup("COMPTEZ-VOUS"); 
		XmlHandler.createGroup("COMPTEZ-V"); 
		XmlHandler.createGroup("COMPTEZ"); 
		XmlHandler.createGroup("COMP"); 
		
		String[] list = XmlHandler.listOfGroups(); 
		int expectedResult = 4; 
		
		XmlHandler.deleteGroup("COMPTEZ-VOUS"); 
		XmlHandler.deleteGroup("COMPTEZ-V"); 
		XmlHandler.deleteGroup("COMPTEZ"); 
		XmlHandler.deleteGroup("COMP"); 
		
		assertEquals("objects should be identical _ list of groups ", list.length, expectedResult); 
		
		
	}

	@Test
	public void testInTheList() {
		String[] test = {"Marine", "Nassim", "Karina", "Ilyes", "Rebecca", "Celine"}; 
		boolean resultat1 = XmlHandler.inTheList(test, "Karina");
		boolean resultat2 = false; 
		
		for( int i = 0; i < test.length; i++) {
			if( test[i].equals("Karina"))
				resultat2 = true; 
		}

		assertEquals( "objects should be identical _ testInTheList", resultat1, resultat2 ); 
		
	}

	@Test
	public void testCheckingLogins() {
		boolean expectedResult = true; 
		boolean result = XmlHandler.checkingLogins(XmlHandler.getDocument(), "Nassim", "MotDePasse"); 
		
		assertEquals("objects should be identical _ list of users in group ", expectedResult, result); 
	}

}
