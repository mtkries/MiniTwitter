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
        //g.setColor(Color.white);
        //g.fillRect(10, 10, 290, 380);	
        for(int i =0; i<25; i++){
        	if(i%5==0){
        		if(i==0)
        			addGroup(root,Integer.toString(i));
        		else
    			addGroup(,Integer.toString(i));
        	}
        }
        
        getTree();
        tree.scrollRectToVisible(new Rectangle(10,10,290,380));
        treePane.setBounds(10, 10, 290, 380);
        //tree.setBounds(10,10,290,380);
        treePane.setBackground(Color.white);
        treePane.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        add(treePane);
        revalidate();
        repaint();
        
      

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
