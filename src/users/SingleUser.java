package users;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.tree.DefaultMutableTreeNode;

public class SingleUser implements Users {
	private String id;
	private HashMap<String,Users> followers;
	private HashMap<String,Users> followings;
	private ArrayList<String> newsFeed;
	private Group parent;
	private long creationTime;
	private long lastUpdateTime;
	
	public void setParent(Group u){
		this.parent = u;
	}
	public void setLastUpdateTime(long lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	public long getLastUpdateTime(){
		return this.lastUpdateTime;
	}
	public Group getParent(){
		return parent;
	}
	public SingleUser(String id){
		this.creationTime = System.currentTimeMillis();
		this.lastUpdateTime = System.currentTimeMillis();
		this.id = id;
		followers = new HashMap<String,Users>();
		followings = new HashMap<String,Users>();
		newsFeed = new ArrayList<String>();
	}
	
	public boolean addFollower(Users u) {
		SingleUser u2 = (SingleUser)u;
		if(followers.containsValue(u2)){
			return false;
		}
		followers.put(u.getID(), u);
		return true;
	}

	public boolean addFollowing(Users u) {
		SingleUser u2 = (SingleUser)u;
		if(followings.containsValue(u2)){
			return false;
		}
		followings.put(u.getID(), u);
		
		return true;
	}
	
	public HashMap<String,Users> getFollowers() {
		return followers;
	}

	public HashMap<String,Users> getFollowings() {
		return followings;
	}
	
//	public void printFollowers(){
//		for (Entry<String, Users> entry : followings.entrySet())
//        {
//			
//			
//        
//        }
//	}
	
	@Override
	public String getID() {
		return id;
	}

	@Override
	public void setID(String id) {
		this.id =id;
	}

	public void addToNewsFeed(String s) {
		this.lastUpdateTime = System.currentTimeMillis();
		newsFeed.add(s);
	}

	public ArrayList<String> getNewsFeed() {
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
	@Override
	public long getCreationTime() {
		return creationTime;
	}

}
