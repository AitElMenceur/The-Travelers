package com.Reseau.Server;

import static org.junit.Assert.assertEquals;

import com.Reseau.Data.Message;

import org.junit.Test;

public class TestConnectionHandler {

    @Test
    public void Testjoin() {
        Message expected = new Message("username", "groupcode", "join", "You join the chat " + "groupcode");
        Message msg = new Message("username", "groupcode", "join", "message");
        MockConnectionHandler mock = new MockConnectionHandler(msg);
        mock.SetInncomingMessage((Message) msg);
        mock.handle();
        Message actual = (Message) mock.GetOutput();
        assertEquals(expected.getCommand(), actual.getCommand());
        assertEquals(expected.getGroupCode(), actual.getGroupCode());
        assertEquals(expected.getMessage(), actual.getMessage());
        assertEquals(expected.getUsername(), actual.getUsername());
    }

    @Test
    public void Testleave() {
        Message expected = new Message("username", "groupcode", "leave", "You leave the chat " + "groupcode");
        Message msg = new Message("username", "groupcode", "leave", "message");
        MockConnectionHandler mock = new MockConnectionHandler(msg);
        mock.SetInncomingMessage((Message) msg);
        mock.handle();
        Message actual = (Message) mock.GetOutput();
        assertEquals(expected.getCommand(), actual.getCommand());
        assertEquals(expected.getGroupCode(), actual.getGroupCode());
        assertEquals(expected.getMessage(), actual.getMessage());
        assertEquals(expected.getUsername(), actual.getUsername());
    }

    @Test
    public void Testsend() {
        Message expected = new Message("username", "groupcode", "send", "message");
        Message msg = new Message("username", "groupcode", "send", "message");
        MockConnectionHandler mock = new MockConnectionHandler(msg);
        mock.SetInncomingMessage((Message) msg);
        mock.handle();
        Message actual = (Message) mock.GetOutput();
        assertEquals(expected.getCommand(), actual.getCommand());
        assertEquals(expected.getGroupCode(), actual.getGroupCode());
        assertEquals(expected.getMessage(), actual.getMessage());
        assertEquals(expected.getUsername(), actual.getUsername());
    }

    @Test
    public void Testdisconnect() {
        Message expected = new Message("username", "groupcode", "disconnect", "bye username");
        Message msg = new Message("username", "groupcode", "disconnect", "");
        MockConnectionHandler mock = new MockConnectionHandler(msg);
        mock.SetInncomingMessage((Message) msg);
        mock.handle();
        Message actual = (Message) mock.GetOutput();
        assertEquals(expected.getCommand(), actual.getCommand());
        assertEquals(expected.getGroupCode(), actual.getGroupCode());
        assertEquals(expected.getMessage(), actual.getMessage());
        assertEquals(expected.getUsername(), actual.getUsername());
    }
    @Test
    public void Testconnect() {
        Message expected = new Message("username", "groupcode", "connect", "Welcome to the server username");
        Message msg = new Message("username", "groupcode", "connect", "");
        MockConnectionHandler mock = new MockConnectionHandler(msg);
        mock.SetInncomingMessage((Message) msg);
        mock.handle();
        Message actual = (Message) mock.GetOutput();
        assertEquals(expected.getCommand(), actual.getCommand());
        assertEquals(expected.getGroupCode(), actual.getGroupCode());
        assertEquals(expected.getMessage(), actual.getMessage());
        assertEquals(expected.getUsername(), actual.getUsername());
    }

}
