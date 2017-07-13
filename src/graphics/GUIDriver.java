package graphics;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import testing.TestJTree;

public class GUIDriver {
	private JFrame f;
	private MainFrame mf;
	
	public static void main(String args[]){
		{
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	GUIDriver gui = new GUIDriver();
	            	gui.createAndShowGUI();
	            }
	        });
	    }
	}
	
	public void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+ SwingUtilities.isEventDispatchThread());
        f = new JFrame("Main Screen");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(mf.getInstance());
        f.pack();
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
	
}
