package users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class SingleUser implements Users {
	private String id;
	private HashMap<String,Users> followers;
	private HashMap<String,Users> followings;
	private List<String> newsFeed;
	
	public SingleUser(String id){
		this.id = id;
		followers = new HashMap<String,Users>();
		followings = new HashMap<String,Users>();
		newsFeed = new ArrayList<String>();
	}
	
	public void addFollower(Users u) {
		followers.put(u.getID(), u);
	}

	public void addFollowing(Users u) {
		followings.put(u.getID(), u);
	}
	
	public HashMap<String,Users> getFollowers() {
		return followers;
	}

	public HashMap<String,Users> getFollowings() {
		return followings;
	}
	
	@Override
	public String getID() {
		return id;
	}

	@Override
	public void setID(String id) {
		this.id =id;
	}

	public void addToNewsFeed(String s) {
		newsFeed.add(s);
	}

	

	public List<String> getNewsFeed() {
		return newsFeed;
	}

	public boolean getAllowsChildren() {
		return false;
	}
	public String toString(){
		return this.id;
	}

	@Override
	public HashMap<String, Users> getChildren() {
		return null;
	}

	@Override
	public DefaultMutableTreeNode getTree() {
		return new DefaultMutableTreeNode(this);
	}

}
