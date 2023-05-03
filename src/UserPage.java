import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;



public class UserPage extends JFrame{

	private JTextField name;
	private JTextField email;
	private JTextField newFriendField;
	
	private JButton backToLogInButton;
	
	private JTextArea comment;
	
	private JButton postButton;
	private JButton groupButton;
	private JButton addaFriendButton;
	
	private JLabel labelRecentPosts;
	private JLabel labelSuggestedF;
	private JLabel labelSpace;
	private JLabel labelSpace2;
	private JLabel groupsField;
	private JLabel newFriendLabel;
	
	private JTextArea postsofUserAndFriends;
	private JTextArea suggFriends;
	
	private JList listView;
	private DefaultListModel model;
	
	private JPanel panel;
	private User user;
	private ArrayList<User> ListofUsers;
	private ArrayList<Group> Listofgroups;
	
	public UserPage(User user, ArrayList<User> Users, ArrayList<Group> groups)
	{
		this.user = user;
		this.ListofUsers = Users;
		this.Listofgroups = groups; 
		
		panel = new JPanel();
		name = new JTextField(" "+this.user.getName()+"          ");
		email = new JTextField(" "+this.user.getEmail()+"     ");
		newFriendField = new JTextField("Add a new friend. . .");
		groupsField = new JLabel("Select group: ");
		newFriendLabel = new JLabel("New friend: ");
		addaFriendButton = new JButton("Add");
		backToLogInButton = new JButton("Back to Login Screen");
		
		listView = new JList();
		model = new DefaultListModel();
		for(Group group:groups) {
			model.addElement(group.getName());
		}
		listView.setModel(model);
		
		
		//ta pros8etw sto panel
		panel.add(name);
		panel.add(email);
		panel.add(backToLogInButton);
		
				
		//gia to button back to login screen.
		ButtonListenerback listenerback = new ButtonListenerback();
		backToLogInButton.addActionListener(listenerback);
		
		comment = new JTextArea(10,10);
		
		postButton  = new JButton("Post");
		groupButton = new JButton(" Ok ");
		
		//keno gia na emfanistei pio katw to Recent Posts by Friends sto para8uro moy.
		labelSpace = new JLabel("                   ");
		panel.add(labelSpace);
		labelRecentPosts = new JLabel("Recent Posts by Friends ");
		
		postsofUserAndFriends = new JTextArea(10,10);
		
		//emfanizontai ta post twn filwn sto postsofUserAndFriends(pou einai JTextArea)
		for(Post post : user.ReturnPosts())
		{
			//metatrepw apo Date se String to Timestamp (dateString)
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = format.format(post.getTimestamp());
			postsofUserAndFriends.append(post.getUser().getName()+ " " + dateString + " " +post.getUserPost() + "\n");
		}
		
		//keno gia na emfanistei pio katw to Suggested Friends sto para8uro moy.
		labelSpace2 = new JLabel("                               ");
		
		labelSuggestedF = new JLabel("Suggested Friends ");
		suggFriends = new JTextArea(3,3);
		
		for (int i=0; i<user.SuggestedFriends().size(); i++)
			suggFriends.append(user.SuggestedFriends().get(i).getName() + "\n");
		
		
		
		
		//ta pros8etw sto panel
		panel.add(comment);
		panel.add(postButton);
		
	    //user.getListofPosts()
		panel.add(labelRecentPosts);
		panel.add(postsofUserAndFriends);
		panel.add(labelSpace2);
		panel.add(labelSuggestedF);
		panel.add(suggFriends);
		
		panel.add(groupsField);
		panel.add(listView);
		panel.add(groupButton);
		panel.add(newFriendLabel);
		panel.add(newFriendField);
		panel.add(addaFriendButton);
		
		//gia thn egrafh se group.
		ButtonListenerOK listenerOK = new ButtonListenerOK();
		groupButton.addActionListener(listenerOK);
		
		
		//gia to button Post.
		ButtonListenerpost listenerPost = new ButtonListenerpost();
		postButton.addActionListener(listenerPost);
		
		//gia ton button addaFrinedButton
		ButtonListeneradd listeneradd = new ButtonListeneradd();
		addaFriendButton.addActionListener(listeneradd);
		
		this.setContentPane(panel);
		
		this.setVisible(true);
		this.setSize(500, 500);
		this.setTitle("Σελίδα Χρήστη");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class ButtonListenerOK implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String SelectedGroupname = (String) listView.getSelectedValue();
			
			Group SelectedGroup = null;
			//an epelekse group kai poio, h oxi.
			for(Group group:Listofgroups) {
				if(group.getName().equals(SelectedGroupname))
					SelectedGroup = group;
			}
			//an den epelekse kanena group
			if (SelectedGroup == null)
				JOptionPane.showMessageDialog(null, "You didn't choose any group!");
			//an epelekse
			else
			{
				if ( SelectedGroup.MemberOrNot(user) )
					JOptionPane.showMessageDialog(null, "You already are a member of this group!");
				else
					SelectedGroup.addMember(user);		
			}
			
			
		}
			
	}
	
	class ButtonListenerback implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new CentralPage(ListofUsers, Listofgroups);
		}
			
	}
	
	class ButtonListenerpost implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String Post = comment.getText();
			
			Post userPost = new Post(Post, user, new Date());
			
			user.AddPost(userPost);
			
			//gia na emfanistei katey8eian san sxolio
			postsofUserAndFriends.setText("");
	
			for(Post post : user.ReturnPosts())
			{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = format.format(post.getTimestamp());
				postsofUserAndFriends.append(post.getUser().getName()+ " " + dateString + " " + post.getUserPost()  + "\n");
			}
			
		}
			
		
	}
	class ButtonListeneradd implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ArrayList<Integer> count1 = new ArrayList<>();
			int element;
			
			ArrayList<User> usersofdiameter = new ArrayList<>();
			
			// flag1 elegxos an einai user
			// flag2 elegxos an eiani hdh filoi h oxi
			// read einai autos pou diabasthke,plhktrologh8hke(name of new friend)
			
			boolean flag1 = false;
			String  read = newFriendField.getText();
			User NewFriend = new User(" ", " ");
			
			//anazhtw an uparxei san user.
			for (int i=0; i<ListofUsers.size(); i++)
			{
				if (ListofUsers.get(i).getName().equals(read))
				{
					flag1 = true;
					NewFriend = ListofUsers.get(i);
					//System.out.println(NewFriend.getName());
				}
				
			}
			//an nai (an uparxei san user)
			if (flag1 == true)
			{
				//an einai hdh filoi
				boolean flag2 = false;
				int j=0;
				while ( j<user.getListofFriends().size() && flag2 == false)
				{
					if( user.getListofFriends().get(j).equals(NewFriend) )
					{
						flag2 = true;
						//JOptionPane.showMessageDialog(null, "You already are friends!");
					}
					j++;
				}
				//an einai filoi telika
				if (flag2 == true)
					JOptionPane.showMessageDialog(null, "You already are friends!");
				else
				//an den einai, na ginoun filoi
				{
					user.addAfriend(NewFriend);
					JOptionPane.showMessageDialog(null, user.getName() + ", you and " + NewFriend.getName() + " are friends now!");
					for (int k=0; k<ListofUsers.size(); k++)
					{
						//krataw users 
						usersofdiameter.add(ListofUsers.get(k));
						
						
						//parallhlos pinakas me ListofUsers
						count1.add(k, 0);
						element = ListofUsers.get(k).Step(usersofdiameter, k);
						count1.add(k, element);
						
						//ka8arizw ton pinaka ka8e fora pou allazw user.
						usersofdiameter.clear();
						
					}
					int max = -1;
					for (int i=0; i<ListofUsers.size(); i++)
					{
						if ( count1.get(i)>max && count1.get(i)!=0) {
							max = count1.get(i);
						}
					}
					if (max!=0)
						System.out.println("makriterh elaxisth diadromh :" + max);
				}
				
			}
			//an oxi (an den uparxei san user)
			else
				JOptionPane.showMessageDialog(null, "This user doesn't exist!");

		}
	}
}
 