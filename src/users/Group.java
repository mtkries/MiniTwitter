package users;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import users.Users;

public class Group implements Users {
	private String id;
	private HashMap<String,Users> children;
	
	public Group(String id){
		this.id = id;
		this.children = new HashMap<String,Users>();
	}

	public void add(Users u) {
		children.put(u.getID(), u);
	}    

	@Override
	public String getID() {
		return this.id;
	}
	
	@Override
	public void setID(String id) {
		// TODO Auto-generated method stub
		
	}
	public String toString(){
		return this.id;
	}

	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public HashMap<String, Users> getChildren() {
		return children;
	}

	@Override
	public DefaultMutableTreeNode getTree() {
		return makeTree(this);
	}

	public Users find(String id, Users u){
		if(u.getID().equals(id)){
			return u;
		}
		HashMap<String, Users> hm = u.getChildren();
		for (Entry<String, Users> entry : hm.entrySet())
        {
			Users current = entry.getValue();
			if(current.getID().equals(id)){
				return current;
			}
			else{
				return find(id, current);
			}
        }
		return null;
	}
	
	
	private DefaultMutableTreeNode makeTree(Users n){
    	DefaultMutableTreeNode current = new DefaultMutableTreeNode(n);
    	HashMap<String,Users> hm = n.getChildren();
    	for (Entry<String, Users> entry : hm.entrySet())
        {
    		Users u = entry.getValue();
    		if(u.getAllowsChildren() && u.getChildren().size()>0){
    			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(u);
    			current.add(makeTreeHelper(newNode));
    		}
    		else{
    			current.add(new DefaultMutableTreeNode(u));
    		}
    		
        }
    	return current;
    	
    }
	
    private DefaultMutableTreeNode makeTreeHelper(DefaultMutableTreeNode n){
    	Users u = (Users) n.getUserObject();
    	HashMap<String,Users> hm = u.getChildren();
    	for (Entry<String, Users> entry : hm.entrySet())
            {
    			Users cu = entry.getValue();
    			if(cu.getAllowsChildren()&& cu.getChildren().size()>0){
    				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(cu);
        			n.add(makeTreeHelper(newNode));
    			}
    			else{
    				n.add(new DefaultMutableTreeNode(cu));
    			}
    		
            }
            
    	return n;
    }

	

}
