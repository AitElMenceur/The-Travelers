package com.Reseau.Server;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import com.Reseau.Data.Group;

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
        server.addGroup(new Group("Groupcode"));
        server.addGroup(new Group("a"));

        for(Group g: Server.LIST_GROUP){
        if(g.getGroupCode().equals("Groupcode")){
            Server.LIST_GROUP.remove(g);
        }
        }
        //server.removeGroup(test);
        assertThat(Server.LIST_GROUP, not(hasItem(test)));
    }

}
