
package assignment.pkg2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    
    private final twitterInfo twt;
    private String ID;
    private Map<String, User> followers;
    private Groups group;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ArrayList<User> getFollowers() {
        if (group != null) {
            this.group.getUsers().stream().filter((user) -> 
                    (!followers.containsValue(user))).forEachOrdered((user) -> {
                followers.put(user.getID(), user);
            });
        }
        ArrayList<User> followersList = new ArrayList<>();
        followers.keySet().forEach((user) -> {
            followersList.add(followers.get(user));
        });
        return followersList;
    }
    
    public boolean following(String users) {
        return getFollowers().contains(twt.getUser(users));
    }
    
    public boolean follow(String users) {
        if(!twt.containsUser(users)) {
            return false;
        }
        else if (following(users)) {
            return false;
        }
        else {
            followers.put(users, twt.getUser(users));
            return true;
        }
    }
    
    public ArrayList<Tweets> getNewsFeed() {
        ArrayList<Tweets> tweets = twt.getTweet();
        ArrayList<Tweets> newsFeed = new ArrayList<>();
        tweets.stream().filter((userTweet) -> 
                (following(userTweet.getUsers().getID()))).
                forEachOrdered((userTweet) -> {
            newsFeed.add(userTweet);
        });
        return newsFeed;
    }
    
    public User(String ID, String group) {
        this.twt = twitterInfo.getInstance();
        this.ID = ID;
        this.followers = new HashMap<>();
        followers.put(ID, this);
        if(!group.equals("Root") && twt.containsGroup(group)) {
            this.group = twt.getGroup(group);
            this.group.getUsers().forEach((users) -> {
                followers.put(users.getID(), users);
            });
        }
    }    
}
