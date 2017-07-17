package testing;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import users.Group;
import users.SingleUser;
@SuppressWarnings("serial")
public class TestJTree extends JFrame
{
    private JTree tree;
    private DefaultMutableTreeNode rootNode;
    public TestJTree()
    {
    	Group root = new Group("root");
        SingleUser john = new SingleUser("john");
        SingleUser bob = new SingleUser("bob");
        SingleUser steve = new SingleUser("steve");
        Group cs356 = new Group("CS256");
        SingleUser stu1 = new SingleUser("stu1");
        SingleUser stu2 = new SingleUser("stu2");
        SingleUser stu3 = new SingleUser("stu3");
        Group cs356Session1 = new Group("CS356Session01");
        SingleUser stu8 = new SingleUser("stu8");
        SingleUser stu9 = new SingleUser("stu9");
        SingleUser stu10 = new SingleUser("stu10");
        SingleUser stu4 = new SingleUser("stu4");
        SingleUser oostu = new SingleUser("oostu");
        SingleUser ppstu2 = new SingleUser("ppstu2");
    	
    	root.add(john);
    	root.add(bob);
    	root.add(steve);
    	root.add(cs356);
    	cs356.add(stu1);
    	cs356.add(stu2);
    	cs356.add(stu3);
    	root.add(cs356Session1);
    	cs356Session1.add(stu8);
    	cs356Session1.add(stu9);
    	cs356Session1.add(stu10);
    	cs356.add(stu4);
    	root.add(oostu);
    	root.add(ppstu2);
    	
    	
    	setRootNode(new DefaultMutableTreeNode(root));

    	tree = new JTree(root.getTree());
        add(tree);
        this.setBounds(100, 100, 300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JTree Example");       
        this.pack();
        this.setVisible(true);
    }
	public DefaultMutableTreeNode getRootNode() {
		return rootNode;
	}
	public void setRootNode(DefaultMutableTreeNode rootNode) {
		this.rootNode = rootNode;
	}
    
}
