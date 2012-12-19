/**
 * Carlos Alberto Martins Ferreira = 2010146877
 * João dos Santos Valença         = 2010130607
 * Pedro Ascensão Ferreira Matias  = 2010120038
 */

package socnet;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import socnet.Post;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

/**
 * Chatroom represents the place in system 
 * where the users can post/see messages.
 * Here, the post is visible to all users
 * that are currently in the chatroom.
 *
 * @author Carlos Ferreira
 * @author João Valença
 * @author Pedro Matias
 *
 * @see 	Post
 */

public class Chatroom implements Serializable{

	private String theme;
	private ConcurrentHashMap<Integer, Post> posts;

	/**
	 * Chatroom constructor, creates a new chatroomm
	 *
	 * @param theme
	 */
	public Chatroom(String theme){
		this.theme = theme;
		this.posts = new ConcurrentHashMap<Integer, Post>();
	}

	/**
	 * Retrieves the chatroom theme
	 * @return Chatroom theme
	 */
	public String getTheme(){
		return theme;
	}
	/**
	 * Sets the chatroom theme name
	 * @param theme 	chatroom's new theme
	 */	
	public synchronized void setTheme(String theme){
		this.theme = theme;
	}

	public void addPost(Post p){
		this.posts.put( p.getID(), p );
	}

	public synchronized Post[] getPosts(){
		ArrayList<Post> posts_ = new ArrayList<Post> (this.posts.values());
		

		// First order by date
		Collections.sort(posts_, new Comparator<Post>(){
			public int compare(Post p1, Post p2) {
				return p1.getDate().compareTo(p2.getDate());
			}
		});
		
		ArrayList<Post> orderedPosts = new ArrayList<Post>();
		// Recursively get reply posts
		for( Post p : posts_ ){
			if(p.getParentID()==-1){		// Call the recursive function for the parents only
				getChildren( orderedPosts, p );
			}
		}

		return orderedPosts.toArray(new Post[0]);		// arrays are more efficient (type cast)
	}

	private synchronized void getChildren( ArrayList<Post>orderedPosts, Post post ){
		orderedPosts.add( post );				// Add parent post first
		for( int replyID : post.getReplyIDs() )
			getChildren( orderedPosts, this.posts.get( replyID ));
	}   	


}