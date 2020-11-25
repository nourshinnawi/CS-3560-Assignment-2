
package assignment.pkg2;

import java.util.Enumeration;
import javax.swing.tree.MutableTreeNode;

public class Tree extends TreeView {
    
    //part of Singleton pattern
    private static Tree instance = null;
    
    private Tree() {
        super();
    }
    
    //part of Singleton pattern
    public static Tree getInstance() {
        if (instance == null){
            instance = new Tree();
        }
        return instance;
    }

    private int getUserTotal(MutableTreeNode node) {
        int i = 0;
        for (Enumeration<MutableTreeNode> e = node.children(); e.hasMoreElements();) {
            MutableTreeNode child = e.nextElement();
            if (child.toString().substring(0,4).equals("User")){
                i = i + 1;
            }
            else {
                i = i + getUserTotal(child);
            }   
        }
        return i;
    }
    
    public int setUserTotal() {
        return getUserTotal(getRoot());
    }
    
    private int getGroupTotal(MutableTreeNode node) {
        int i = 0;
        for (Enumeration<MutableTreeNode> e = node.children(); e.hasMoreElements();) {
            MutableTreeNode child = e.nextElement();
            if (child.toString().substring(0, 5).equals("Group")) {
               i = i + 1;
            }
            else {
                i = i + getGroupTotal(child);
            }
        }
        return i;
    }
    
    public int setGroupTotal() {
        return getGroupTotal(getRoot());
    }
}
