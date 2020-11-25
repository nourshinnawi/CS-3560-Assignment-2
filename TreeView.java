
package assignment.pkg2;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class TreeView extends JPanel{
    private DefaultMutableTreeNode root;
    private final DefaultTreeModel treeModel;
    private final JTree tree;
    
    public DefaultMutableTreeNode getRoot() {
        return root;
    }

    public void setRoot(DefaultMutableTreeNode root) {
        this.root = root;
    }
    
    public TreeView() {
        super(new GridLayout(1, 0));
        
        root = new DefaultMutableTreeNode("Root");
        treeModel = new DefaultTreeModel(root);
        
        tree = new JTree(treeModel);
        tree.setEditable(false);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);
        
        JScrollPane scrollPane = new JScrollPane(tree);
        add(scrollPane);
    }
    
    public DefaultMutableTreeNode getSelected() {
        DefaultMutableTreeNode parent = null;
        TreePath parentPath = tree.getSelectionPath();
        if (parentPath == null) {
            parent = root;
        }
        else {
            parent = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
        }
        return parent;
    }
    
    public DefaultMutableTreeNode addObject(Object child) {
        DefaultMutableTreeNode parent = getSelected();  
        return addObject(parent, child, true);
    }
    
    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
                                            Object child) {
        return addObject(parent, child, false);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
                                            Object child, 
                                            boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
        if (parent == null) {
            parent = root;
        }
        treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
        if (shouldBeVisible) {
            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }
    
    class MyTreeModelListener implements TreeModelListener {
        @Override
        public void treeNodesChanged(TreeModelEvent e) {
            DefaultMutableTreeNode node;
            node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());
            int index = e.getChildIndices()[0];
            node = (DefaultMutableTreeNode) (node.getChildAt(index));
        }
        @Override
        public void treeNodesInserted(TreeModelEvent e) {}
        @Override
        public void treeNodesRemoved(TreeModelEvent e) {}
        @Override
        public void treeStructureChanged(TreeModelEvent e) {}
    }
}
