
package a2;

public final class TotalGroups implements Count {
 
    private int count;
    
    public TotalGroups () {
        setCount(0);
    }
    
    public int getCount () {
        return count;
    }
    
    public void setCount (int count) {
        this.count = count;
    }
    
    @Override
    public void countUsers(UserView user) {}
    
    @Override
    public void countGroups(UserGroup group) {
        this.setCount(getCount() + 1);
    }
}
