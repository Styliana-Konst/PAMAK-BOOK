import java.io.Serializable;
import java.util.*;

import javax.swing.JOptionPane;


public class User implements Serializable {

	private String name;
	private String email;
	private ArrayList<User> ListofFriends = new ArrayList<User>();
	private ArrayList<Group> Groups = new ArrayList<Group>();
	private ArrayList<Post> ListofPosts = new ArrayList<Post>();
	//private ArrayList<User> patient = new ArrayList<User>();
	
	
	public User(String name, String email)
	{
		this.name = name;
		this.email = email;
		//elegos gia akadhmaiko email
		/*if ( this.email.endsWith("uom.edu.gr") == false)
			System.out.println("CONSOLE:\r\n" + 
					 this.name + " has not been created. Email format is not acceptable. ");*/
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public ArrayList<User> getListofFriends() {
		return ListofFriends;
	}



	public void setListofFriends(ArrayList<User> listofFriends) {
		ListofFriends = listofFriends;
	}

	


	public ArrayList<Group> getGroups() {
		return Groups;
	}

	public void setGroups(ArrayList<Group> groups) {
		Groups = groups;
	}

	public ArrayList<Post> getListofPosts() {
		return ListofPosts;
	}

	public void setListofPosts(ArrayList<Post> listofPosts) {
		ListofPosts = listofPosts;
	}

	//h me8odos ayth epistrefei true h false, an einai 2 filoi h oxi.
	public boolean Friends(User otherUser)
	{
		if (this.ListofFriends.contains(otherUser))
		{
			return true;
		}
		return false;
	}
	
//pros8etei ena filo 	
	public void addAfriend(User otherUser)
	{
		boolean flag = false;
		for (int i=0; i<this.ListofFriends.size(); i++)
		{
			if (this.Friends(otherUser) || otherUser.getName() == this.name)
				flag = true;
		}
		if ( flag == false )
		{
			ListofFriends.add(otherUser);
			otherUser.ListofFriends.add(this);
			//System.out.println(this.name+" and "+ otherUser.getName()+ " are now friends!");
		}
	}
	
//koinoi filoi
	public ArrayList<User> CommonFriends(User otherUser)
	{
		ArrayList<User> comfriends = new ArrayList<User>();
		
		for (int i=0; i<this.ListofFriends.size(); i++)
		{
			String element = this.ListofFriends.get(i).getName();
			for (int j=0; j<otherUser.ListofFriends.size(); j++)
			{
				if ( otherUser.ListofFriends.get(j).getName() == element )
					comfriends.add(otherUser.ListofFriends.get(j));
			}
		}
		/*System.out.println("**************************************");
		//System.out.println("--------------------------------------");
		System.out.println("Common friends of "+ this.name +" and "+ otherUser.getName());
		System.out.println("**************************************");*/
		
	    
		return comfriends;
		
	}
	
//typwnei tous filous tou xrhsth
	public void PrintUsersFriends()
	{
		System.out.println("**************************************");
		System.out.println("Friends of "+ this.getName());
		System.out.println("**************************************");
		for (int i=0; i<this.ListofFriends.size(); i++)
		{
			System.out.println((i+1)+ ": "+ this.ListofFriends.get(i).getName());
		}
		System.out.println("--------------------------------------");
		//System.out.println("**************************************");
	}
	
	public void addToGroup(Group g)
	{
	
		//pros8etei melos se group
		g.addMember(this);
		if (g.members.contains(this))
		{
			//System.out.println(this.getName() +" has been successfully enrolled in group "+ g.getName());
			//pros8etei tis omades pou anhkei o xrhsths
			this.Groups.add(g);
		}
		
	}

	
//emfanizei ta groups sta opoia exei graftei o xrhsths
	public void PrintGroups()
	{
		System.out.println("Groups that "+ this.name+ " has been enrolled in");
		System.out.println("**************************************");
		for (int i=0; i<Groups.size(); i++)
			System.out.println(i+1 +": "+Groups.get(i).getName());
		System.out.println("--------------------------------------");
		System.out.println("**************************************");
	}
	
	public ArrayList<User> covid()
	{
		ArrayList<User> patient = new ArrayList<User>();
		
		//System.out.println(this.getName() +" has been infected. The following users have to be tested");
		//System.out.println("**************************************");

		for (int i=0; i<this.ListofFriends.size(); i++)
		{
			// o ka8e filos tou arrwstou
			User f = this.ListofFriends.get(i);
			patient.add(this.ListofFriends.get(i));
		    for (int j=0; j<f.ListofFriends.size(); j++)
		    {
		    	//o ka8e filos tou f ( filos filou rou arrwstou)
		    	if (patient.contains(f.ListofFriends.get(j)) == false && f.ListofFriends.get(j).getName() != this.getName() && this.ListofFriends.contains(f.ListofFriends.get(j))==false )
		    		patient.add(f.ListofFriends.get(j));
		    	
		    }
			
		}
		
		//emfanizei tous pi8anous as8eneis
		/*for (int i=0; i<patient.size(); i++)
		{
			System.out.println(patient.get(i).getName());
		}
		System.out.println("--------------------------------------");*/
		
		return patient;
	}
	
	public void AddPost(Post p)
	{
		this.ListofPosts.add(p);
	}
	
	public ArrayList<Post> ReturnPosts()
	{
		ArrayList<Post> Postsoffriendsanduser = new ArrayList<Post>();
		
		for (int i=0; i<ListofFriends.size(); i++)
			Postsoffriendsanduser.addAll(ListofFriends.get(i).ListofPosts);
		
		Postsoffriendsanduser.addAll(this.ListofPosts); 
		
		Collections.sort(Postsoffriendsanduser);
		
		return Postsoffriendsanduser;
		
	}
	
	public ArrayList<User> SuggestedFriends()
	{
		ArrayList<User> Suggested = new ArrayList<User>();
		for (int i=0; i< ListofFriends.size() ; i++)
		{
			User f = ListofFriends.get(i);
			for (int j=0; j<f.ListofFriends.size(); j++)
			{
				User ff = f.ListofFriends.get(j);
				if ( (Suggested.contains(ff) || ff.getName() == this.getName() || this.ListofFriends.contains(ff)) == false)
				{
					Suggested.add(ff);
				}
			}
		}
		return Suggested;
	}

 // PROSPA8EIA LYSHS TOY ZHTHMATOS THS DIAMETROY TOY GRAFOY TWN FILWN. (LA8OS APOTELESMATA) !!
 // !!!
	public Integer Step(ArrayList<User> usersofdiameter, int k)
	{
		//pinakas int pou krataei oles tis diadromes gia ka8e user.
		ArrayList<Integer> count2 = new ArrayList<>();
		int count=0,sum=0;
		for (int i=0; i<ListofFriends.size(); i++)
		{
			boolean flag = false;
			int j=0;
			count2.add(i, 0);
			
			while (j<usersofdiameter.size() && flag == false)
			{
				//an uparxei hdh stous usersofdiameter ?
				if ( usersofdiameter.get(j).equals((ListofFriends.get(i))) )
					flag = true;
				j++;
			}
			//an den uparxei balton 
			if (flag == false)
			{
				usersofdiameter.add(i, ListofFriends.get(i));
				
				count = usersofdiameter.size()-1;
				count = this.Step(usersofdiameter, k);
				sum = count+sum;
				count2.add(i, sum);

				
				//count = usersofdiameter.size()-1;
				/*count = this.Step(usersofdiameter, count1, k) ;
				sum += count;
				System.out.println(count +" count? ");
				
				count2.add(i, sum);*/
				
				
			}
		}
		int max = -1;
		for ( int j=0; j<ListofFriends.size(); j++)
			if ( count2.get(j) > max  )
				max = count2.get(j);
		
		return max;
		
	
	}

}
