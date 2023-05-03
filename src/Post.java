
import java.io.Serializable;
import java.util.*;


public class Post implements Comparable, Serializable
{
	private String userPost;
	private User user;
	private Date timestamp;
	
	public Post(String userPost, User user, Date timestamp) 
	{
		this.userPost = userPost;
		this.user = user;
		this.timestamp = timestamp;
		
		
		
	}
	
	

	public String getUserPost() {
		return userPost;
	}



	public void setUserPost(String userPost) {
		this.userPost = userPost;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Date getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}



	@Override
	public int compareTo(Object temp) {
		// TODO Auto-generated method stub
		Post other = (Post) temp;
		return ((other.timestamp).compareTo(this.timestamp));
		
	}
	
}
