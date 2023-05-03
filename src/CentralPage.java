import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CentralPage extends JFrame{
	
	private JTextField nameField;
	private JTextField emailField;
	private JButton enterButtom;
	private JButton showInfectionsButton;
	private JButton newUserButton;
	private JButton SaveButton;
	private JPanel panel;
	private ArrayList<User> ListofUsers;
	private ArrayList<Group> Listofgroups;

	
	public CentralPage(ArrayList<User> Users, ArrayList<Group> groups)
	{
		this.ListofUsers = Users;
		this.Listofgroups = groups;
		
		panel = new JPanel();
		nameField = new JTextField("user name");
		emailField = new JTextField("user email");
		
		enterButtom = new JButton("Enter User Page");
		showInfectionsButton = new JButton("Show Potential Infections");
		newUserButton = new JButton("New User");
		SaveButton = new JButton("Save PamakBook");
		
		panel.add(newUserButton);
		panel.add(nameField);
		panel.add(emailField);
		panel.add(enterButtom);
		panel.add(showInfectionsButton);
		panel.add(SaveButton);
		
		this.setContentPane(panel);
		
		ButtonListener1 listener1 = new ButtonListener1();
		enterButtom.addActionListener(listener1);
		
		ButtonListener2 listener2 = new ButtonListener2();
		showInfectionsButton.addActionListener(listener2);
		
		ButtonListener3 listenerNewUser = new ButtonListener3();
		newUserButton.addActionListener(listenerNewUser);
		
		ButtonListener4 listenerSave = new ButtonListener4();
		SaveButton.addActionListener(listenerSave);
		
		this.setVisible(true);
		this.setSize(280, 250);
		this.setTitle("Κεντρική σελίδα");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	class ButtonListener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String name = nameField.getText();
			
			int flag = -1;
			for (int i=0; i< ListofUsers.size(); i++)
			{
				if ( ListofUsers.get(i).getName().equals(name) )
				{
					flag = i;
				}
				
			}
			if ( flag == -1 )
			{
				JOptionPane.showMessageDialog(null, "User " + name + " Not Found");
			}
			else 
			{
				dispose();
				User user = ListofUsers.get(flag);
				new UserPage(user, ListofUsers, Listofgroups);
			}
			
		}
		
	}
	
	class ButtonListener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String name = nameField.getText();
			User user = ListofUsers.get(0);
			
			for (int i=0; i< ListofUsers.size(); i++)
			{
				if ( ListofUsers.get(i).getName().equals(name) )
				{
					
					user = ListofUsers.get(i);
				}
				
			}
			
			dispose();
			//elegxw an uparxei o user.
			int flag = -1;
			for (int i=0; i< ListofUsers.size(); i++)
			{
				if ( ListofUsers.get(i).getName().equals(name) )
				{
					flag = i;
				}
				
			}
			if ( flag == -1 )
			{
				dispose();
				JOptionPane.showMessageDialog(null, "User " + name + " Not Found");
			}
			else
				new Infections(user, ListofUsers, Listofgroups);
			
		}
	}

	class ButtonListener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			boolean flag = false;
			String name = nameField.getText();
			String email = emailField.getText();
			int i=0;
			while ( i<ListofUsers.size() && flag == false )
			{
				if ( (ListofUsers.get(i).getName()).equals(name) )
				{
					flag = true;
				}
				i++;
			}
			if (flag == true  )
			{
				JOptionPane.showMessageDialog(null, "Υπάρχει ήδη χρήστης με το όνομα αυτό!");
			}
			else
			{
				User newUser = new User(name, email);
				
				ListofUsers.add(newUser);
				System.out.println("Pros8ethke!");
			}
		}
		
	}
	
	class ButtonListener4 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				FileOutputStream fileOut = new FileOutputStream("PamakBook.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(ListofUsers);
				out.close();
				fileOut.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
