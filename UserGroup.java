
package a2;

import java.util.ArrayList;
import java.util.Iterator;

public class UserGroup implements TotalTweets {
    
    private String ID;
    ArrayList<TotalTweets> tweet;
    
    public UserGroup (String ID) {
        this.setID(ID);
        tweet = new ArrayList<>();
    }
    
    public void setID(String ID) {
        this.ID = ID;
    }
    
    public String getID() {
        return ID;
    }
    
    public void addTweet(TotalTweets post) {
        if(!tweet.contains(post)) {
            tweet.add(post);
        }
    }
    
    @Override
    public void add(Count twt) {
        twt.countGroups(this);
        for (Iterator<TotalTweets> it = tweet.iterator(); it.hasNext();) {
            TotalTweets t = it.next();
            t.add(twt);
        }
    }
    
    @Override
    public String toString() {
        return this.ID;
    }    
}
