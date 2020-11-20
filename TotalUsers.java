
package a2;

public final class TotalUsers implements Count{
    
    private int count;
    
    public TotalUsers() {
        this.count = 0;
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
        this.setCount(getCount() + 1);
    }
    
    @Override
    public void countGroups(UserGroup group) {}
}
