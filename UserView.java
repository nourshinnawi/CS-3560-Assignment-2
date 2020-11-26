
package assignment.pkg2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UserView extends JPanel implements ActionListener, Observer {
    
    private final twitterInfo twt;
    private final User users;
    
    private JFrame frame;
    
    private final JPanel contentPanel;
    private final JPanel newsFeedPanel;
    private final JPanel tweetsPanel;
    
    private final JTextArea newsFeed;
    private final JTextArea tweets;
    
    private final JScrollPane newsFeedScroll;
    private final JScrollPane tweetsScroll;
    
    public UserView(String users) {
        super(new BorderLayout());
        
        this.twt = twitterInfo.getInstance();
        this.users = twt.getUser(users);
        
        contentPanel = new JPanel();
        JLabel userLabel = new JLabel(users + "'s Profile");
        userLabel.setFont(new Font("Plain", Font.BOLD, 30));
        contentPanel.add(userLabel, BorderLayout.NORTH);
        
        newsFeedPanel = new JPanel();
        newsFeed = new JTextArea();
        newsFeed.setEditable(false);
        newsFeedScroll = new JScrollPane(newsFeed);
        newsFeedScroll.setPreferredSize(new Dimension(450, 200));
        newsFeedPanel.add(new JLabel("Following: "), BorderLayout.NORTH);
        newsFeedPanel.add(newsFeedScroll, BorderLayout.CENTER);
        contentPanel.add(newsFeedPanel, BorderLayout.CENTER);
         
        tweetsPanel = new JPanel();
        tweets = new JTextArea();
        tweets.setEditable(false);
        tweetsScroll = new JScrollPane(tweets);
        tweetsScroll.setPreferredSize(new Dimension(480, 300));
        tweetsPanel.add(new JLabel("Tweets: "), BorderLayout.WEST);
        tweetsPanel.add(tweetsScroll, BorderLayout.CENTER);
        contentPanel.add(tweetsPanel, BorderLayout.SOUTH);
        
        JButton followButton = new JButton("Follow");
        followButton.setActionCommand(followButton.getText());
        followButton.addActionListener(this);
        
        JButton tweetButton = new JButton("Tweet");
        tweetButton.setActionCommand(tweetButton.getText());
        tweetButton.addActionListener(this);
        
        contentPanel.setPreferredSize(new Dimension(550, 600));
        add(contentPanel, BorderLayout.CENTER);
        
        JPanel panel = new JPanel(new GridLayout(7,1));
        panel.add(new JLabel());
        panel.add(followButton);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(tweetButton);
        add(panel, BorderLayout.EAST);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (command.equals("Follow")) {
            String follow = JOptionPane.showInputDialog(null, "Enter user: ");
            follow = "User " + follow;
            if (follow.equals("User null")) {
                JOptionPane.showMessageDialog(null,
                                      "Invalid user. Try again.", "Error",
                                      JOptionPane.ERROR_MESSAGE);
            }
            else if (!twt.containsUser(follow)) {
                JOptionPane.showMessageDialog(null,
                                      "User entered does not exist", "Error",
                                      JOptionPane.ERROR_MESSAGE);
            }
            else if (users.following(follow)) {
                JOptionPane.showMessageDialog(null,
                                      "Already following " + follow, "Error",
                                      JOptionPane.ERROR_MESSAGE);
            }
            users.follow(follow);
        }
        else if (command.equals("Tweet")) {
            String tweet = JOptionPane.showInputDialog(null, "Enter tweet:");
            if (tweet == null) {
                JOptionPane.showMessageDialog(null, "Invalid tweet", "Error",
                                      JOptionPane.ERROR_MESSAGE);
            }
            else if (tweet.length() > 280) {
                JOptionPane.showMessageDialog(null,
                                      "Tweet is too long",
                                      "It must be shorter than 280 characters",
                                      JOptionPane.ERROR_MESSAGE);
            }
            else {
                twt.addTweets(new Tweets(users, tweet));
            }
        } 
        twt.update();
    }
    
    public void createWindow() {
        frame = new JFrame("User View");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setOpaque(true);
        frame.setContentPane(this);
        frame.pack();
    }
    
    public void windowOn() {
        frame.setVisible(true);
    }
    
    public void windowOff() {
        frame.setVisible(false);
    }
    
    //part of Observer pattern
    @Override
    public void update() {
        newsFeed.setText("");
        for (Iterator<User> it = users.getFollowers().iterator(); it.hasNext();) {
            User userName = it.next();
            if (userName != users) {
                newsFeed.append(userName.getID() + "\n");
            }
            tweets.setText("");
            users.getNewsFeed().forEach((tweet) -> {
                tweets.append(tweet.getTweets() + "\n");
            });
        }
    }
}
