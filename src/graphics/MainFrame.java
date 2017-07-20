package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import users.Group;
import users.SingleUser;
import users.Users;

public class MainFrame extends JPanel {

	private static MainFrame mf;
	private Group root;
	private DefaultMutableTreeNode jTreeRoot;
	private JScrollPane treePane;
	private JTree testTree;
	private Users selectedUser;
	private DefaultMutableTreeNode selectedNode;
	private JTextField userID;
	private JTextField groupID;
	private JFrame UserViewFrame;
	private LinkedList<UserViewPanel> panelList = new LinkedList<UserViewPanel>();
	private JButton showUserTotalButton;
	private JButton showGroupTotalButton;
	private JButton showTotalMessagesButton;
	private JButton checkIDButton;
	private JButton showPositivePercentageButton;
	private JButton addUserButton;
	private JButton addGroupButton;
	private JButton openUserViewButton;
	
	
	
    private MainFrame(){
        this.root = new Group("root");
        selectedUser = root;
        jTreeRoot = new DefaultMutableTreeNode(root);
        selectedNode = jTreeRoot;
        testTree = new JTree(jTreeRoot);
    	setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.LIGHT_GRAY);
    	addComponents();
    	addListeners();
    	this.setLayout(null);
    	  addUsersForTesting();
    }
    public void updateListModel(){
    	
    	
    }
    public void addComponents(){
    	Font labelFont = new Font("TimesRoman", Font.BOLD, 18);
    	
    	treePane = new JScrollPane(testTree);
    	treePane.setBounds(10, 10, 250, 380);
        treePane.setBackground(Color.white);
        treePane.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    	

    	
    	add(treePane);
    	
    	userID = new JTextField();
    	userID.setFont(labelFont);
    	userID.setHorizontalAlignment(SwingConstants.CENTER);
    	userID.setBounds(new Rectangle(300,10,235,60));
    	userID.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    	
    	groupID = new JTextField();
    	groupID.setFont(labelFont);
    	groupID.setHorizontalAlignment(SwingConstants.CENTER);
    	groupID.setBounds(new Rectangle(300,80,235,60));
    	groupID.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
    	addUserButton = new JButton("Add User");
    	addUserButton.setFont(labelFont);
    	addUserButton.setVerticalAlignment(SwingConstants.CENTER);
    	addUserButton.setHorizontalAlignment(SwingConstants.CENTER);
    	addUserButton.setBounds(new Rectangle(555,10,235,60));
    	
    	addGroupButton = new JButton("Add Group");
    	addGroupButton.setFont(labelFont);
    	addGroupButton.setVerticalAlignment(SwingConstants.CENTER);
    	addGroupButton.setHorizontalAlignment(SwingConstants.CENTER);
    	addGroupButton.setBounds(new Rectangle(555,80,235,60));
    	
    	openUserViewButton = new JButton("Open User View");
    	openUserViewButton.setFont(labelFont);
    	openUserViewButton.setVerticalAlignment(SwingConstants.CENTER);
    	openUserViewButton.setHorizontalAlignment(SwingConstants.CENTER);
    	openUserViewButton.setBounds(new Rectangle(300,150,490,60));
    	
    	showUserTotalButton = new JButton("Show User Total");
    	showUserTotalButton.setFont(labelFont);
    	showUserTotalButton.setVerticalAlignment(SwingConstants.CENTER);
    	showUserTotalButton.setHorizontalAlignment(SwingConstants.CENTER);
    	showUserTotalButton.setBounds(new Rectangle(300,240,235,60));
    	
    	showGroupTotalButton = new JButton("Show Group Total");
    	showGroupTotalButton.setFont(labelFont);
    	showGroupTotalButton.setVerticalAlignment(SwingConstants.CENTER);
    	showGroupTotalButton.setHorizontalAlignment(SwingConstants.CENTER);
    	showGroupTotalButton.setBounds(new Rectangle(555,240,235,60));
    	
    	showTotalMessagesButton = new JButton("Show Total Messages");
    	showTotalMessagesButton.setFont(labelFont);
    	showTotalMessagesButton.setVerticalAlignment(SwingConstants.CENTER);
    	showTotalMessagesButton.setHorizontalAlignment(SwingConstants.CENTER);
    	showTotalMessagesButton.setBounds(new Rectangle(300,310,235,60));

    	showPositivePercentageButton = new JButton("Show Positive Percentage");
    	showPositivePercentageButton.setFont(labelFont);
    	showPositivePercentageButton.setVerticalAlignment(SwingConstants.CENTER);
    	showPositivePercentageButton.setHorizontalAlignment(SwingConstants.CENTER);
    	showPositivePercentageButton.setBounds(new Rectangle(555,310,235,60));
    	
    	checkIDButton = new JButton("Check for Invalid ID");
    	checkIDButton.setFont(labelFont);
    	checkIDButton.setVerticalAlignment(SwingConstants.CENTER);
    	checkIDButton.setHorizontalAlignment(SwingConstants.CENTER);
    	checkIDButton.setBounds(new Rectangle(300,380,235,60));
    	
    	
    	add(checkIDButton);
    	add(openUserViewButton);
    	add(userID);
    	add(groupID);
    	add(addUserButton);
    	add(addGroupButton);
    	add(showUserTotalButton);
    	add(showGroupTotalButton);
    	add(showTotalMessagesButton);
    	add(showPositivePercentageButton);
    }
    public void addListeners(){
    	testTree.addMouseListener(new MouseAdapter() {
    	      public void mouseClicked(MouseEvent me) {
    	          treeEntryClicked(me);
    	        }
    	      });
    	checkIDButton.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent me) {
    			checkIDButtonPressed();
    		}
    	});
    	addUserButton.addActionListener(new ActionListener() { 
    		  public void actionPerformed(ActionEvent e) { 
    			    addUserButtonPressed();
    			  } 
    			} );
    	addGroupButton.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
			    addGroupButtonPressed();
			  }
			} );
    	openUserViewButton.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) { 
    			openUserViewButtonPressed();
    		}
    	} );
    	showUserTotalButton.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
			    showUserTotalButtonPressed();
			  } 
			} );
    	showGroupTotalButton.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
			    showGroupTotalButtonPressed();
			  } 
			} );
    	showTotalMessagesButton.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
			    showTotalMessagesButtonPressed();
			  } 
			} );
    	showPositivePercentageButton.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
			    showPositivePercentageButtonPressed();
			  } 
			} );
    	
    }
    public void checkIDButtonPressed(){
    	Group g = (Group)root;
    	JOptionPane.showMessageDialog(null, "# of Invalid IDs:" + Integer.toString(g.getInvalidIDs()));
    }
    public void treeEntryClicked(MouseEvent me){
    	TreePath tp = testTree.getPathForLocation(me.getX(), me.getY());
    	try{
    	System.out.println(tp.getLastPathComponent());
    	DefaultMutableTreeNode t = (DefaultMutableTreeNode)tp.getLastPathComponent();
    	Users u = (Users)t.getUserObject();
    	selectedUser = u;
    	
    	
    	selectedNode = t;
    	}
    	catch(Exception e){
    		
    	}
    }
    public void addUserButtonPressed(){
    	if(userID.getText() != null){
	    	if(selectedUser.getAllowsChildren()){
	    		Users u = new SingleUser(userID.getText());
	    		DefaultMutableTreeNode n = new DefaultMutableTreeNode(u);
	    		addWithRespectToTree((Group)selectedUser,u,selectedNode,n);
	    		 updateTree();
	    	}
	    	else{
	    		Users u = new SingleUser(userID.getText());
	    		DefaultMutableTreeNode n = new DefaultMutableTreeNode(u);
	    		addWithRespectToTree(selectedUser.getParent(),u,selectedNode,n);
	    		 updateTree();
	    	}
    	}
    	
    }
    public void addGroupButtonPressed(){
    }
    public void showUserTotalButtonPressed(){
    	Group g = (Group)root;
    	JOptionPane.showMessageDialog(null, Integer.toString(g.getTotalUsers()));
    }
    public void showGroupTotalButtonPressed(){
    	Group g = (Group)root;
    	JOptionPane.showMessageDialog(null, Integer.toString(g.getTotalGroups()));
    }
    public void showTotalMessagesButtonPressed(){
    	Group g = (Group)root;
    	JOptionPane.showMessageDialog(null, Integer.toString(g.getTotalMessages()));
    }
    public void showPositivePercentageButtonPressed(){
    	Group g = (Group)root;
    	try{
    	JOptionPane.showMessageDialog(null, Integer.toString(g.getPositive()/g.getTotalMessages()));
    	}catch(Exception e){
    		JOptionPane.showMessageDialog(null, "0");
    	}
    }
    public void openUserViewButtonPressed(){
    	if(!selectedUser.getAllowsChildren()){
	    	UserViewFrame = new JFrame("UserView");
	    	UserViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	SingleUser u = (SingleUser)selectedUser;
	    	System.out.println(u.getFollowings().size()+"AAAAAAAAAAAAAAAAAAAA");
	    	UserViewPanel tempPanel = new UserViewPanel(u,root);
	    	panelList.add(tempPanel);
	    	UserViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	UserViewFrame.add(tempPanel);
	    	UserViewFrame.pack();
	    	UserViewFrame.setVisible(true);
	    	UserViewFrame.setLocationRelativeTo(null);
    	}
    }
    
    //observer method
    public void updateNewsFeed(String s, HashMap<String,Users> hm){
    	System.out.println("UpdateNewsFeedCalled");
    	for(UserViewPanel j: panelList){
    		for (Entry<String, Users> entry : hm.entrySet())
            {
    			System.out.println("ASDASDASD");
    			SingleUser u = (SingleUser) entry.getValue();
	    		if(j.getThisUser()==u){
	    			System.out.println("unf2");
	    			j.updateNewsFeedListModel(s);
	    		}
	    		else{
	    			u.addToNewsFeed(s);
	    		}
            }
    	}
    }
    
    
    public void nodeClicked(MouseEvent me){

    }
    
   
    
    //Singleton
    public static MainFrame getInstance(){
    	if(mf == null ){
    		mf = new MainFrame();
    	}
    	return mf;
    }


    @Override
    public Dimension getPreferredSize() {

        return new Dimension(800,600);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       // addButtons();
        
      
       
        
       
        
        
        revalidate();
        repaint();
    }
    
    public void updateTree(){
    	DefaultTreeModel model = (DefaultTreeModel) testTree.getModel();
    	root.getTree().add(new DefaultMutableTreeNode("test"));
    	model.reload(root.getTree());
    	System.out.println("update TReE");
    }
    public void addUsersForTesting(){
    	SingleUser u1 = makeSingleUser("1");
    	DefaultMutableTreeNode u1t = new DefaultMutableTreeNode(u1);
        SingleUser u2 = makeSingleUser("2");
        DefaultMutableTreeNode u2t = new DefaultMutableTreeNode(u2);
        SingleUser u3 = makeSingleUser("3");
        DefaultMutableTreeNode u3t = new DefaultMutableTreeNode(u3);
        SingleUser u4 = makeSingleUser("4");
        DefaultMutableTreeNode u4t = new DefaultMutableTreeNode(u4);
        SingleUser u5 = makeSingleUser("5");
        DefaultMutableTreeNode u5t = new DefaultMutableTreeNode(u5);
        SingleUser u6 = makeSingleUser("6");
        DefaultMutableTreeNode u6t = new DefaultMutableTreeNode(u6);
        SingleUser u7 = makeSingleUser("7");
        DefaultMutableTreeNode u7t = new DefaultMutableTreeNode(u7);
        SingleUser u11 = makeSingleUser("11");
        DefaultMutableTreeNode u11t = new DefaultMutableTreeNode(u11);
        
        u1.addFollowing(u2);
        u2.addFollower(u1);
        u1.addFollowing(u3);
        u3.addFollower(u1);
        u1.addFollowing(u4);
        u4.addFollower(u1);
        u1.addFollowing(u5);
        u5.addFollower(u1);
        u1.addFollowing(u6);
        u6.addFollower(u1);
        u1.addFollowing(u7);
        u7.addFollowing(u1);
        
        
        
        
        Group g1 = makeGroup("g1");
        DefaultMutableTreeNode g1t = new DefaultMutableTreeNode(g1);
        Group g2 = makeGroup("g2");
        DefaultMutableTreeNode g2t = new DefaultMutableTreeNode(g2);
        
        
        addWithRespectToTree(root,g1,jTreeRoot,g1t);
        addWithRespectToTree(root,u1,jTreeRoot,u1t);
        
        addWithRespectToTree(g1,g2,g1t,g2t);
        addWithRespectToTree(g1,u2,g1t,u2t);

        addWithRespectToTree(g2,u3,g2t,u3t);
        addWithRespectToTree(g2,u3,g2t,u3t);
        addWithRespectToTree(g1,u4,g1t,u4t);

        addWithRespectToTree(g1,u5,g1t,u5t);
        addWithRespectToTree(g2,u6,g2t,u6t);
        addWithRespectToTree(g2,u7,g2t,u7t);
        addWithRespectToTree(g2,u11,g2t,u11t);
        System.out.print(root.findChild("11", root) + "Result");
        updateTree();
    }
    public void addWithRespectToTree(Group parent, Users child, DefaultMutableTreeNode treeParent,DefaultMutableTreeNode treeChild){
    	parent.add(child);
    	treeParent.add(treeChild);
    }
    
    
    public SingleUser makeSingleUser(String id){
    	return new SingleUser(id);
    }	
    public Group makeGroup(String id){
    	return new Group(id);
    }
    public void addUser(Group group, String id){
    	group.add(new SingleUser(id));
    }
    public void addGroup(Group group, String id){
    	group.add(new Group(id));
    }
}
