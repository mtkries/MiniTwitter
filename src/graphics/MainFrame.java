package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import users.Group;
import users.SingleUser;
import users.Users;

public class MainFrame extends JPanel {

	private static MainFrame mf;
	private JTree tree;
	private Group root;
	private JScrollPane treePane;
    private MainFrame(){
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.LIGHT_GRAY);
        
    }
    
    public static MainFrame getInstance(){
    	if(mf == null ){
    		mf = new MainFrame();
    		mf.root = new Group("root");
    	}
    	return mf;
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800,400);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        addUsersForTesting();
        
        getTree();
        tree.scrollRectToVisible(new Rectangle(10,10,280,370));
        treePane.setBounds(10, 10, 280, 370);
        treePane.setBackground(Color.white);
        treePane.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        add(treePane);
        revalidate();
        repaint();
    }
    
    
    public void addUsersForTesting(){
    	SingleUser u1 = makeSingleUser("1");
        SingleUser u2 = makeSingleUser("2");
        SingleUser u3 = makeSingleUser("3");
        SingleUser u4 = makeSingleUser("4");
        SingleUser u5 = makeSingleUser("5");
        SingleUser u6 = makeSingleUser("6");
        SingleUser u7 = makeSingleUser("7");
        SingleUser u8 = makeSingleUser("8");
        SingleUser u9 = makeSingleUser("9");
        SingleUser u10 = makeSingleUser("10");
        SingleUser u11 = makeSingleUser("11");
        Group g1 = makeGroup("g1");
        Group g2 = makeGroup("g2");
        Group g3 = makeGroup("g3");
        Group g4 = makeGroup("g4");
        Group g5 = makeGroup("g5");
        
        root.add(g1);
        root.add(u1);
        g1.add(g2);
        g1.add(u2);
       g2.add(u3);
        g1.add(g2);
        g1.add(u4);
        g1.add(u5);
        g2.add(u6);
        g2.add(u7);
        
    }
    
    public SingleUser makeSingleUser(String id){
    	return new SingleUser(id);
    }
    public Group makeGroup(String id){
    	return new Group(id);
    }
    public void getTree(){
    	if(tree == null){
    		tree = new JTree(root.getTree());
    		tree.setVisibleRowCount(20);
    		treePane = new JScrollPane(tree);
    		//add(treePane);
    	}
    }
    public void addUser(Group group, String id){
    	group.add(new SingleUser(id));
    }
    public void addGroup(Group group, String id){
    	group.add(new Group(id));
    }
}
