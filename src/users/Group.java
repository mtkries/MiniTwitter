package users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;

import users.Users;

public class Group implements Users {
	private String id;
	private long creationTime;
	private Group parent;
	private HashMap<String,Users> children;
	
	public Group(String id){
		this.id = id;
		this.children = new HashMap<String,Users>();
		parent = null;
		creationTime=System.currentTimeMillis();
	}
	public int getPositive(){
		return getPositivePercentageHelper(0,this);
	}
	
	public int getPositivePercentageHelper(int i, Users u){
		int returnVal =i;
		HashMap<String, Users> hm = u.getChildren();
		for (Entry<String, Users> entry : hm.entrySet())
        {
			Users current = entry.getValue();
			if(current.getAllowsChildren()&&current.getChildren().size() >0){
				returnVal+=getPositivePercentageHelper(returnVal, current);
			}
			else{
				SingleUser u2 = (SingleUser) current;
				ArrayList<String> al = u2.getNewsFeed();
					for(String s : al){
						if(isPositive(s)){
							returnVal++;
						}
					}
			}
        }
		return returnVal;
	}
	private boolean isPositive(String s){
		if(s.toLowerCase().contains("good") ||s.toLowerCase().contains("great") ||s.toLowerCase().contains("awesome") ||s.toLowerCase().contains("cool")){
			return true;
		}
		return false;
	}
	
	public int getTotalMessages(){
		return getTotalMessagesHelper(0,this);
	}
	
	public int getTotalMessagesHelper(int i, Users u){
		int returnVal =i;
		HashMap<String, Users> hm = u.getChildren();
		for (Entry<String, Users> entry : hm.entrySet())
        {
			Users current = entry.getValue();
			if(current.getAllowsChildren()&&current.getChildren().size() >0){
				returnVal=getTotalMessagesHelper(returnVal, current);
			}
			else{
				SingleUser u2 = (SingleUser) current;
				returnVal+=u2.getNewsFeed().size();
			}
        }
		return returnVal;
	}
	
	public void add(Users u) {
		children.put(u.getID(), u);
		u.setParent(this);
	}    
	public void setParent(Group u){
		this.parent = u;
	}
	public Group getParent(){
		return parent;
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

	public int getTotalUsers(){
		return getTotalUsersHelper(0,this);
	}

	private int getTotalUsersHelper(int i, Users u){
		int returnVal = i;
		HashMap<String, Users> hm = u.getChildren();
		for (Entry<String, Users> entry : hm.entrySet())
        {
			Users current = entry.getValue();
			if(current.getAllowsChildren()){
				returnVal=getTotalUsersHelper(returnVal, current);
			}
			else{
				returnVal++;
			}
        }
		
		return returnVal;
	}
	public int getTotalGroups(){
		return getTotalGroupsHelper(0,this)+1;
	}
	public int getTotalGroupsHelper(int i, Users u){
		int returnVal =i;
		HashMap<String, Users> hm = u.getChildren();
		for (Entry<String, Users> entry : hm.entrySet())
        {
			Users current = entry.getValue();
			if(current.getAllowsChildren()&&current.getChildren().size() >0){
				returnVal++;
				returnVal=getTotalGroupsHelper(returnVal, current);
			}
			else{
			}
        }
		return returnVal;
	}
	
	
	public Users findChild(String id, Users u){
		System.out.println("findchild");
		HashMap<String, Users> hm = u.getChildren();
		
		for (Entry<String, Users> entry : hm.entrySet())
        {
			Users current = entry.getValue();
			System.out.println(current + "F");
			if(current.getID().equals(id)){
				return current;
			}
			if(current.getAllowsChildren()){
				System.out.println("getting "+current +"Children");
				return findChild(id, current);
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
    public int getInvalidIDs(){
    	return getInvalidIDsHelper(0,this);
    }
    private int getInvalidIDsHelper(int i, Users u){
		int returnVal = i;
		HashMap<String, Users> hm = u.getChildren();
		for (Entry<String, Users> entry : hm.entrySet())
        {
			Users current = entry.getValue();
			if(current.getID().contains(" ")){
				returnVal++;
			}
			if(current.getAllowsChildren()){
				returnVal=getInvalidIDsHelper(returnVal, current);
			}
			else{
				//returnVal++;
			}
        }
		
		return returnVal;
	}
    
    

	@Override
	public long getCreationTime() {
		return creationTime;
	}

	
	

}
