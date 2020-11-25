
package assignment.pkg2;

public class PercentPositiveTweets {
    private static final String positiveWords[]  = { "fun", "excited", "happy", 
                                                     "great", "amazing", "awesome", 
                                                     "cool", "wonderful", "nice" };
    
    public static float calculatePercent() {
        twitterInfo twt = twitterInfo.getInstance();
        float positiveTweets = 0;
        for(Tweets tweet : twt.getTweet()) {
            for (String positive: positiveWords) {
                if (tweet.getPosts().toLowerCase().contains(positive)) {
                    positiveTweets = (float) (positiveTweets + 1.0);
                    break;
                }
            }
        }
        if (twt.tweetTotal() == 0) {
            return 0;
        }
        else {
            float percent = (positiveTweets / twt.tweetTotal()) * 100;
            return percent;
        }
    }
}
