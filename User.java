
package a2;

import java.util.ArrayList;

public interface User {
    
    public ArrayList<User> getFollowers();
    public void follow(User targetID);
    public void post(String message);
    public void notification();
    public void update(User user);
    public String getID();
    public String getMessage();  
}
