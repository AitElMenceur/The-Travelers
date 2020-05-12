package com.Reseau;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class TestConnectionHandler {

    @Test
    public void Testjoin() {
        Message expected = new Message("username", "groupcode", "join", "You join the chat " + "groupcode");
        Message msg = new Message("username", "groupcode", "join", "message");
        MockConnectionHandler mock = new MockConnectionHandler(msg);
        mock.SetInncomingMessage((Message) msg);
        mock.Handle();
        Message actual = (Message) mock.GetOutput();
        assertEquals(expected.GetCommand(), actual.GetCommand());
        assertEquals(expected.GetGroupCode(), actual.GetGroupCode());
        assertEquals(expected.GetMessage(), actual.GetMessage());
        assertEquals(expected.GetUsername(), actual.GetUsername());
    }

    @Test
    public void Testleave() {
        Message expected = new Message("username", "groupcode", "leave", "You leave the chat " + "groupcode");
        Message msg = new Message("username", "groupcode", "leave", "message");
        MockConnectionHandler mock = new MockConnectionHandler(msg);
        mock.SetInncomingMessage((Message) msg);
        mock.Handle();
        Message actual = (Message) mock.GetOutput();
        assertEquals(expected.GetCommand(), actual.GetCommand());
        assertEquals(expected.GetGroupCode(), actual.GetGroupCode());
        assertEquals(expected.GetMessage(), actual.GetMessage());
        assertEquals(expected.GetUsername(), actual.GetUsername());
    }

    @Test
    public void Testsend() {
        Message expected = new Message("username", "groupcode", "send", "message");
        Message msg = new Message("username", "groupcode", "send", "message");
        MockConnectionHandler mock = new MockConnectionHandler(msg);
        mock.SetInncomingMessage((Message) msg);
        mock.Handle();
        Message actual = (Message) mock.GetOutput();
        assertEquals(expected.GetCommand(), actual.GetCommand());
        assertEquals(expected.GetGroupCode(), actual.GetGroupCode());
        assertEquals(expected.GetMessage(), actual.GetMessage());
        assertEquals(expected.GetUsername(), actual.GetUsername());
    }

    @Test
    public void Testdisconnect() {
        Message expected = new Message("username", "groupcode", "disconnect", "bye username");
        Message msg = new Message("username", "groupcode", "disconnect", "");
        MockConnectionHandler mock = new MockConnectionHandler(msg);
        mock.SetInncomingMessage((Message) msg);
        mock.Handle();
        Message actual = (Message) mock.GetOutput();
        assertEquals(expected.GetCommand(), actual.GetCommand());
        assertEquals(expected.GetGroupCode(), actual.GetGroupCode());
        assertEquals(expected.GetMessage(), actual.GetMessage());
        assertEquals(expected.GetUsername(), actual.GetUsername());
    }
    @Test
    public void Testconnect() {
        Message expected = new Message("username", "groupcode", "connect", "Welcome to the server username");
        Message msg = new Message("username", "groupcode", "connect", "");
        MockConnectionHandler mock = new MockConnectionHandler(msg);
        mock.SetInncomingMessage((Message) msg);
        mock.Handle();
        Message actual = (Message) mock.GetOutput();
        assertEquals(expected.GetCommand(), actual.GetCommand());
        assertEquals(expected.GetGroupCode(), actual.GetGroupCode());
        assertEquals(expected.GetMessage(), actual.GetMessage());
        assertEquals(expected.GetUsername(), actual.GetUsername());
    }

}
