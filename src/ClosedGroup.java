import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClosedGroup extends Group {
	
	
	public ClosedGroup(String name, String description)
	{
		super(name, description);
		members = new ArrayList<>();
	}
	

	public void addMember(User user)
	{
			boolean flag = false;
			int i = 0;
			
			//gia na pros8e8ei to prwto melos
			if ( this.members.isEmpty() == true )
			{
				this.members.add(user);
				flag = true;
			}
			else 
			{
				while ( i < this.members.size() && flag == false )
				{
					//an o upo eggrafh xrhsths exei filo pou einai hdh xrhsths
					if ( this.members.get(i).Friends(user))
					{
						this.members.add(user);
						flag = true;
					
					}
					i++;
				}
			}
			if ( flag == false )
			{
				JOptionPane.showMessageDialog(null, "You don't qualify the conditions!");
				//System.out.println("FAILED: "+user.getName() +" cannot be enrolled in group "+ this.getName());
			}
			else
				JOptionPane.showMessageDialog(null, "You are a member now!");
			
			
	}
	
}
