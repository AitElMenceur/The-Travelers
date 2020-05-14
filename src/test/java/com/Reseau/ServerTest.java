package com.Reseau;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ServerTest {
    private String ip = "localhost";
    Server server = new Server(ip);

    /**
     * 
     */
    @Test
    public void TestaddGroup() {
        Group test = new Group("Groupcode");
        server.addGroup(test);
        assertThat(Server.LIST_GROUP, hasItem(test));
    }
    @Test
    public void TestremoveGroup() {
        Group test = new Group("Groupcode");
        server.addGroup(test);
        server.removeGroup(test);
        assertThat(Server.LIST_GROUP, not(hasItem(test)));
    }

}
