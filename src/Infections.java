import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Infections extends JFrame{
	
	private JPanel panel;
	private JButton backToLogInButtom;
	private User user;
	private ArrayList<User> ListofUsers;
	private JTextArea area;
	private ArrayList<Group> Listofgroups;
	
	public Infections(User user, ArrayList<User> Users, ArrayList<Group> groups)
	{
		this.user = user;
		this.ListofUsers = Users;
		this.Listofgroups = groups;
		
		panel = new JPanel();
		
		String s = "**********************************************************************\n";
				
		area = new JTextArea(s + user.getName() +" has been infected. The following users have to be tested\n" );
		area.append(s);
	    for (int i=0; i<user.covid().size(); i++)
	    	area.append(user.covid().get(i).getName() + "\n");
		
	    backToLogInButtom = new JButton("Back to Login Screen");
	    
		panel.add(area);
		panel.add(backToLogInButtom);
		
		//gia to button back to login screen.
		ButtonListenerback listenerback = new ButtonListenerback();
		backToLogInButtom.addActionListener(listenerback);
		
		this.setContentPane(panel);
		
		this.setVisible(true);
		this.setSize(400, 300);
		this.setTitle("Πιθανή Μετάδοση Ιού");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class ButtonListenerback implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new CentralPage(ListofUsers, Listofgroups);
		}
			
		
	}

}
