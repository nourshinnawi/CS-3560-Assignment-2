
package assignment.pkg2;

public class Tweets {
    
    private User users;
    private String posts;

    public Tweets(User users, String posts) {
        this.users = users;
        this.posts = posts;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }
    
    public String getTweets() {
        return (users.getID() + " said: " + posts);
    }
}
