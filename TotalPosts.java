
package a2;

public final class TotalPosts implements Count{
    
    private int count;
    
    public TotalPosts () {
        setCount(0);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void countUsers(UserView user) {
        this.setCount(getCount()+user.getNewsFeed().size());
    }

    @Override
    public void countGroups(UserGroup group) {}
}
