
package assignment.pkg2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminControlPanel extends JPanel implements ActionListener {
    
    private static AdminControlPanel instance = null;
    private static final Tree tree = Tree.getInstance();;
    private static final twitterInfo twt = twitterInfo.getInstance();
    
    //part of Singleton pattern
    public static AdminControlPanel getInstance() {
        if (instance == null) {
            instance = new AdminControlPanel();
        }
        instance.displayWindow();
        return instance;
    }
      
    private AdminControlPanel() {
        super(new BorderLayout());
        
        JButton addUserButton = new JButton("Add User");
        addUserButton.setActionCommand(addUserButton.getText());
        addUserButton.addActionListener(this);
        
        JButton addGroupButton = new JButton("Add Group");
        addGroupButton.setActionCommand(addGroupButton.getText());
        addGroupButton.addActionListener(this);
        
        JButton openUserButton = new JButton("Open User View");
        openUserButton.setActionCommand(openUserButton.getText());
        openUserButton.addActionListener(this);
        
        JButton userTotalButton = new JButton("Show Total Users");
        userTotalButton.setActionCommand(userTotalButton.getText());
        userTotalButton.addActionListener(this);
        
        JButton groupTotalButton = new JButton("Show Total Groups");
        groupTotalButton.setActionCommand(groupTotalButton.getText());
        groupTotalButton.addActionListener(this);
        
        JButton tweetTotalButton = new JButton("Show Total Messages");
        tweetTotalButton.setActionCommand(tweetTotalButton.getText());
        tweetTotalButton.addActionListener(this);
        
        JButton positivePercentageButton = new JButton("Show Positive Percentage");
        positivePercentageButton.setActionCommand(positivePercentageButton.getText());
        positivePercentageButton.addActionListener(this);
        
        tree.setPreferredSize(new Dimension(300, 500));
        add(tree, BorderLayout.CENTER);
        
        JPanel panel = new JPanel(new GridLayout(7, 1));
        panel.add(addUserButton);
        panel.add(addGroupButton);
        panel.add(openUserButton);
        panel.add(userTotalButton);
        panel.add(groupTotalButton);
        panel.add(tweetTotalButton);
        panel.add(positivePercentageButton);
        add(panel, BorderLayout.EAST);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String command = e.getActionCommand();
        String select = tree.getSelected().toString();
        
        switch (command) {
            case "Add User":
                {
                    if (!select.equals("Root")) {
                        if (select.substring(0, 4).equals("User")) {
                            JOptionPane.showMessageDialog(null,
                                    "Cannot add a usser to a user."
                                    + "Please select a group to add a user to.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }       
                    String user = JOptionPane.showInputDialog(
                                              null, "Enter username:");
                    user = "User " + user;
                    if (user.equals("User null")) {
                        JOptionPane.showMessageDialog(null, 
                                "User does not exist",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else if (twt.containsUser(user)) {
                        JOptionPane.showMessageDialog(null,
                                user + " already exists", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        twt.addUsers(user, select);
                        tree.addObject(user);
                    }       
                    break;
                }
            case "Add Group":
                if (!select.equals("Root")) {
                    if (select.substring(0,4).equals("User")) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot add a group to a user. "
                                + "Please select a group to add to.", 
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }   
                String group = JOptionPane.showInputDialog(
                                           null, "Enter group:");
                group = "Group " + group;
                if (group.equals("Group null")) {
                    JOptionPane.showMessageDialog(null,
                            "Group could not be created",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (twt.containsGroup(group)) {
                    JOptionPane.showMessageDialog(null,
                            "That group already exists",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    twt.addGroups(group);
                    tree.addObject(group);
                }   
                break;
            case "Open User View":
                {
                    if (select.equals("Root") || 
                            !select.substring(0, 4).equals("User")) {
                        JOptionPane.showMessageDialog(null, 
                                "Please select a user",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }       
                    String user = select;
                    twt.getUserView(user).windowOn();
                    break;
                }
            case "Show Total Users":
                JOptionPane.showMessageDialog(null, "Total Users: " +
                        tree.setUserTotal());
                break;
            case "Show Total Groups":
                JOptionPane.showMessageDialog(null, "Total Groups: " +
                        tree.setGroupTotal());
                break;
            case "Show Total Messages":
                JOptionPane.showMessageDialog(null, "Total Messages: " +
                        twt.tweetTotal());
                break;
            case "Show Positive Percentage":
                JOptionPane.showMessageDialog(null,
                        "Percent Positive Tweets: " +
                        PercentPositiveTweets.calculatePercent() + "%");
                break;
            default:
                break;
        }
        twt.update();
    }

    public void displayWindow() {
        JFrame frame = new JFrame("Twitter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setOpaque(true);
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);
    }
}
