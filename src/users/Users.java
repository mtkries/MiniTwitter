package users;

import java.util.HashMap;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public interface Users {
	public String getID();
	public void setID(String id);
	public boolean getAllowsChildren();
	public HashMap<String,Users> getChildren();
	public DefaultMutableTreeNode getTree();

}
