package com.XmlTest;

import static org.junit.Assert.*;
import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;

import com.dataBase.XmlHandler;
	
public class XmlHandlerTest {

	XmlHandler xml = new XmlHandler("Database"); 
	
	@Test
	public void testXmlHandler() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testGetDocument() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFilepath() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitializeXml() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewXml() {
		fail("Not yet implemented");
	}

	@Test
	public void testTransformerXml() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddUser() {
		XmlHandler.addUser("Marine", "MotDePasse"); 
		
		TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    try {
	        transformer = tf.newTransformer();

	        StringWriter writer = new StringWriter();
	 
	        //transform document to string 
	        transformer.transform(new DOMSource(XmlHandler.getDocument()), new StreamResult(writer));
	 
	        String xmlString = writer.getBuffer().toString();   
	        System.out.println(xmlString); 
	        
	        String ExpectedStringResult ="<?xml version=\"1.0\" encoding =\"UTF-8\" standalone=\"no\"?>\n"
	                   + "<Database>\n"
	                   + "\t<Users>\n"
	                   + "\t\t<UserName>Nassim</UserName>\n"
	                   + "\t\t\t<Password>MotDePasse</Password>\n"
	                   + "\t</Users>\n"
	                   + "\t<Groups/>\n"
	                   + "</Database>\n";
	        
	        
	        if(!xmlString.equals(ExpectedStringResult))
	        		fail("Not yet implemented");
	        
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
		fail("Not yet implemented");
	}

	@Test
	public void testAddFriend() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteFriend() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUserName() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddGroupCodeToUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddMessage() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteMessage() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteGroupcodeOfAUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisplayHistory() {
		fail("Not yet implemented");
	}

	@Test
	public void testListUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testListFriend() {
		fail("Not yet implemented");
	}

	@Test
	public void testListOfGroupsOfAUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testListOfUsersInGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testListOfGroups() {
		fail("Not yet implemented");
	}

	@Test
	public void testInTheList() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckingLogins() {
		fail("Not yet implemented");
	}

}
