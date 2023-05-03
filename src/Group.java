import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Group implements Serializable{

	protected String name;
	protected String description;
	protected  ArrayList<User> members;
	
	public Group(String name, String description)
	{
		this.name = name;
		this.description = description;
		members = new ArrayList<>();
		
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public ArrayList<User> getMembers() {
		return members;
	}


	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}

//elegxos an einai melos h oxi
	public boolean MemberOrNot(User user)
	{
		if (this.members.contains(user))
		{
			return true;
		}
		return false;
	}
	
//pros8etei melh
	public void addMember(User user)
	{
		
		if ( this.MemberOrNot(user) == false)
		{
			this.members.add(user);
			JOptionPane.showMessageDialog(null, "You are a member now!");
			
		}
	}
	
	/*
//emfanizei ta melh apo group
	public void printMembers()
	{
	
		System.out.println("Members of group "+ this.getName());
		System.out.println("**************************************");
		for (int i=0; i<this.members.size(); i++)
		{
			System.out.println((i+1)+ ": "+ this.members.get(i).getName());
		}
		System.out.println("--------------------------------------");
		System.out.println("**************************************");
	}
	*/
}
