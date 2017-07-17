package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import users.Group;
import users.Users;
import users.SingleUser;

public class UserViewPanel extends JPanel {

	private JLabel userIDLabel;
	private JLabel followingsLabel;
	private JLabel newsFeedLabel;
	private JButton followUser;
	private JScrollPane followingScrollPane;
	private JScrollPane newsFeedScrollPane;
	private JList<String> currentFollowing;
	private DefaultListModel<String> followingListModel;
	private DefaultListModel<String> newsFeedListModel;
	private JList<String> newsFeed;
	private JTextField tweetMessageTextField;
	private JTextField addUserTextField;
	private JButton postTweet;
	private SingleUser thisUser;
	private JLabel errorLabel;
	private Group root;
	
	
	public UserViewPanel(SingleUser u, Group root){
		
		this.setLayout(null);
		if(u.getAllowsChildren()){
			addComponentsError();
			
		}
		else{
			this.root = root;
			thisUser =  u;
			addComponents();
			addListeners();
		}
	}
	public SingleUser getThisUser(){
		return thisUser;
	}
	public void addComponentsError(){
    	Font labelFont = new Font("TimesRoman", Font.BOLD, 14);
		errorLabel = new JLabel("This function is only allowed for users; Not Groups.");
    	errorLabel.setFont(labelFont);
    	errorLabel.setVerticalAlignment(SwingConstants.CENTER);
    	errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	errorLabel.setBounds(new Rectangle(15,10,350,350));
    	add(errorLabel);
    	
	}
	
	public void addComponents(){
		
    	Font labelFont = new Font("TimesRoman", Font.BOLD, 18);
    	
    	followingsLabel = new JLabel("Followings:");
    	followingsLabel.setFont(labelFont);
    	followingsLabel.setVerticalAlignment(SwingConstants.CENTER);
    	followingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	followingsLabel.setBounds(new Rectangle(140,120,120,40));
    	add(followingsLabel);
    	
    	userIDLabel = new JLabel(thisUser.getID());
    	userIDLabel.setFont(labelFont);
    	userIDLabel.setVerticalAlignment(SwingConstants.CENTER);
    	userIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	userIDLabel.setBounds(new Rectangle(165,10,60,40));
   
    	
    	followUser = new JButton("Follow User");
    	followUser.setFont(labelFont);
    	followUser.setVerticalAlignment(SwingConstants.CENTER);
    	followUser.setHorizontalAlignment(SwingConstants.CENTER);
    	followUser.setBounds(new Rectangle(205,60,180,60));
    	followUser.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    	
    
    	
    	buildFollowingsListModel();
		currentFollowing = new JList<String>(followingListModel);
    	followingScrollPane = new JScrollPane(currentFollowing);
    	 followingScrollPane.setBounds(15, 170, 370, 100);
         followingScrollPane.setBackground(Color.white);
         followingScrollPane.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    	
    	addUserTextField = new JTextField();
    	addUserTextField.setFont(labelFont);
    	addUserTextField.setHorizontalAlignment(SwingConstants.CENTER);
    	addUserTextField.setBounds(new Rectangle(10,60,180,60));
    	addUserTextField.setBorder(BorderFactory.createLineBorder(Color.black, 2));

    	tweetMessageTextField = new JTextField();
    	tweetMessageTextField.setFont(labelFont);
    	tweetMessageTextField.setHorizontalAlignment(SwingConstants.CENTER);
    	tweetMessageTextField.setBounds(new Rectangle(10,280,180,60));
    	tweetMessageTextField.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    	
    	postTweet = new JButton("Post Tweet");
    	postTweet.setFont(labelFont);
    	postTweet.setVerticalAlignment(SwingConstants.CENTER);
    	postTweet.setHorizontalAlignment(SwingConstants.CENTER);
    	postTweet.setBounds(new Rectangle(205,280,180,60));
    	postTweet.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    	

    	newsFeedLabel = new JLabel("News Feed:");
    	newsFeedLabel.setFont(labelFont);
    	newsFeedLabel.setVerticalAlignment(SwingConstants.CENTER);
    	newsFeedLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	newsFeedLabel.setBounds(new Rectangle(140,350,120,40));
    	
    	buildNewsFeedListModel();
		newsFeed = new JList<String>(newsFeedListModel);
		newsFeedScrollPane = new JScrollPane(newsFeed);
    	newsFeedScrollPane.setBounds(15, 400, 370, 100);
    	newsFeedScrollPane.setBackground(Color.white);
    	newsFeedScrollPane.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    	
//		private JButton followUser;
//		private JList<String> currentFollowing;
//		private JTextField tweetMessageTextField;
//		private JButton postTweet;
		
    	add(addUserTextField);
    	add(followingScrollPane);
    	add(newsFeedScrollPane);
    	add(userIDLabel);
    	add(followUser);
//    	add(currentFollowing);
    	add(tweetMessageTextField);
    	add(postTweet);
    	add(newsFeedLabel);
//    	add(newsFeed);
    	
//    	HashMap<String, Users> hm = thisUser.getChildren();
//    	for (Entry<String, Users> entry : hm.entrySet())
//        {
//    		System.out.println(entry.getKey());
//    		
//        }
    	
    	
	}
	
	public void addListeners(){
		followUser.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
  			  System.out.println("FollowUserButtonPressed");
  			    followUserButtonPressed();
  			  } 
  			} );
		postTweet.addActionListener(new ActionListener() { 
	  		  public void actionPerformed(ActionEvent e) { 
	  			   postTweetButtonPressed();
	  			   
	  			  } 
	  			} );
	}
	public void postTweetButtonPressed(){
		HashMap<String,Users> hm = thisUser.getFollowings();
		System.out.println(hm.size() + "HM");
		String s = tweetMessageTextField.getText();	
		MainFrame mf = MainFrame.getInstance();
		mf.updateNewsFeed(s, hm);
		
	}
	public void followUserButtonPressed(){
		SingleUser user = (SingleUser)thisUser;
		Users followerToAdd = root.findChild(addUserTextField.getText(), root);
		if(followerToAdd == null){
			System.out.println("This user does not exist");
		}
		else if(followerToAdd.getAllowsChildren()){
			System.out.println("You cannot follow a group");
		}
		else{
			user.addFollowing(followerToAdd);
			SingleUser followerToAdd1 =(SingleUser)followerToAdd;
			if(followerToAdd1.addFollower(user)){
				updateFollowingsListModel(followerToAdd1.getID());
			}
		}
//		user.addFollowing(u);
	}
	public void updateFollowingsListModel(String s){
		followingListModel.addElement(s);
		System.out.println("Update List Model " +s);
//		fireContentsChanged(followingListModel);
	}
	public void updateNewsFeedListModel(String s){
		newsFeedListModel.addElement(s);
		System.out.println("Update List Model " +s);
//		fireContentsChanged(followingListModel);
	}
	
	public void buildFollowingsListModel(){
		followingListModel  = new DefaultListModel<>();
		SingleUser user = (SingleUser)thisUser;
		HashMap<String,Users> hm = user.getFollowings();
		if(hm.size() != 0){
			System.out.println("Following Not Null");
			for (Entry<String, Users> entry : hm.entrySet())
		        {
				//System.out.println(entry.getKey());
					followingListModel.addElement(entry.getKey());
		        }
		}
		else{
			System.out.println("Following 0");
		}
	}
	public void buildNewsFeedListModel(){
		newsFeedListModel  = new DefaultListModel<>();
		SingleUser user = (SingleUser)thisUser;
		ArrayList<String> news = user.getNewsFeed();
		if(news.size() != 0){
			for(String s: news){
				newsFeedListModel.addElement(s);
			}
			
		}
		else{
			System.out.println("Following 0");
		}
	}
	
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
	}
	
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(400,600);
    }
	
	
}
