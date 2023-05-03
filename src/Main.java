
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//ArrayList<User> comfriends = new ArrayList<User>();
		ArrayList<User> Users = new ArrayList<User>();
		ArrayList<Group> groups = new ArrayList<Group>();
		
		try {
			FileInputStream fileIn = new FileInputStream("PamakBook.ser");
		    ObjectInputStream  in = new ObjectInputStream(fileIn);
		    Users = (ArrayList<User>) in.readObject();
		    in.close();
		    fileIn.close();
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Group g1 = new Group("WebGurus","A group for web passionates");
		ClosedGroup g2 = new ClosedGroup("ExamSolutions","Solutions to common exam questions");
			
		groups.add(g1);
		groups.add(g2);
		
		new CentralPage(Users, groups);
		
	}

}
