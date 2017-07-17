package users;

import java.util.HashMap;

import javax.swing.tree.DefaultMutableTreeNode;

//Composite
public interface Users {
	public String getID();
	public void setID(String id);
	public boolean getAllowsChildren();
	public HashMap<String,Users> getChildren();
	public DefaultMutableTreeNode getTree();
	public void setParent(Group g);
	public Group getParent();
	
}
