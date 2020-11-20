
package a2;


public final class PercentPositiveTweets implements Count{
    
    private int totalTweets;
    private int count;
    
    private final String positiveWords[]  = { "fun", "excited", "happy", 
                                    "great", "amazing", "awesome", 
                                    "cool", "wonderful", "nice" };
    
    public PercentPositiveTweets() {
        setTotalTweets(0);
        setCount(0);
    }
    
    public int getTotalTweets() {
        return totalTweets;
    }
    
    public void setTotalTweets (int totalTweets) {
        this.totalTweets = totalTweets;
    }
    
    public int getCount () {
        return count;
    }
    
    public void setCount (int count) {
        this.count = count;
    }
    
    public float calculatePercent () {
        float percent = (count / totalTweets) * 100;
        return percent;
    }
    
    @Override
    public void countUsers(UserView user) {
        for(Object twts: user.getNewsFeed().toArray()) {
            for (int i = 0; i < positiveWords.length; i++) {
                if (twts.toString().toLowerCase().contains(positiveWords[i])) {
                    setCount(getCount() + 1);
                }
            }
            setTotalTweets(getTotalTweets() + 1);
        }
    }
    
    @Override
    public void countGroups(UserGroup group) {}
}
