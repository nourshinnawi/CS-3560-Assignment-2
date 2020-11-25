
package assignment.pkg2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class twitterInfo implements Observer {
    
    //part of Singleton pattern
    private static twitterInfo instance = null;
    
    private Map<String, User> user;
    private Map<String, Groups> group;
    private Map<String, UserView> userView;
    private ArrayList<Tweets> tweet;
    
    private twitterInfo() {
        user = new HashMap<>();
        group = new HashMap<>();
        userView = new HashMap<>();
        tweet = new ArrayList<>();
    }
    
    //part of Singleton pattern
    public static twitterInfo getInstance() {
        if(instance == null) {
            instance = new twitterInfo();
        }
        return instance;
    }
    
    public User getUser(String users) {
        return user.get(users);
    }
    
    public void setUser(Map<String, User> user) {
        this.user = user;
    }
    
    public Groups getGroup(String groups) {
        return group.get(groups);
    }

    public void setGroup(Map<String, Groups> group) {
        this.group = group;
    }
    
    public UserView getUserView(String view) {
        return userView.get(view);
    }

    public void setUserView(Map<String, UserView> userView) {
        this.userView = userView;
    }

    public ArrayList<Tweets> getTweet() {
        return tweet;
    }
    
    public void setTweet(ArrayList<Tweets> tweet) {
        this.tweet = tweet;
    }
    
    public int tweetTotal() {
        return tweet.size();
    }
    
    public boolean containsUser(String users) {
        return user.containsKey(users);
    }
    
    public boolean containsGroup(String groups) {
        return group.containsKey(groups);
    }
    
    public boolean addUsers(String users, String groups) {
        if(containsUser(users)) {
            return false;
        }
        user.put(users, new User(users, groups));
        
        if(containsGroup(groups)) {
            getGroup(groups).addUser(users);
        }
        UserView view = new UserView(users);
        view.createWindow();
        userView.put(users, view);
        return true;
    }
    
    public boolean addGroups(String groups) {
        if (containsGroup(groups)) {
            return false;
        }
        else {
            group.put(groups, new Groups(groups));
            return true;
        }
    }
    
    public void addTweets(Tweets tweets) {
        tweet.add(tweets);
    }
    
    //part of Observer pattern
    @Override
    public void update() {
        userView.keySet().forEach((users) -> {
            userView.get(users).update();
        });
    }
}
