package assignment.pkg2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Groups {
    private twitterInfo twt;
    private String ID;
    private Map<String, User> users;
    //Assignment #3 addtiton - 2. Add creation time attribute to User and Group.
    private long creationTime;

    public Groups(String ID) {
        this.twt = twitterInfo.getInstance();
        this.ID = ID;
        this.users = new HashMap<>();
        //Assignment #3 addtiton - 2. Add creation time attribute to User and Group.
        this.creationTime = System.currentTimeMillis();
    }
    
    public ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();
        users.keySet().forEach((user) -> {
            userList.add(users.get(user));
        });
        return userList;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
    
    public boolean addUser(String user) {
        if (twt.containsUser(user)) {
            users.put(user,twt.getUser(user));
            return true;
        }
        else {
            return false;
        }
    }
    
    //Assignment #3 addtiton - 2. Add creation time attribute to User and Group.
    public long getCreationTime() {
        return creationTime;
    }
}
