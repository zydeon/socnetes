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
import java.util.Date;
import java.util.Iterator;

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
	/* Post 'ID' is the key to the hash map */
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

	/**
	 * Adds a new post to the chatroom
	 *
	 * @param p 		The post to be added in the chatroom
	 */
	public void addPost(Post p){
		this.posts.put( p.getID(), p );
	}

	/**
	 * Retrieves the posts written in this chatroom
	 * ordered by date and by its parent recursively.
	 * The first ones are the most up to date.
	 *
	 * @return				An array with the ordered posts
	 */
	public synchronized Post[] getPosts(){
		Date now = new Date();
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
				if( p.getDate().compareTo(now)<0 )		// remove delayed posts
					getChildren( orderedPosts, p );
			}
		}

		return orderedPosts.toArray(new Post[0]);		// arrays are more efficient (type cast)
	}

	/**
	 * Recursive function used by getPosts()
	 * adds a post and its children by order in 
	 * the specified ArrayList of posts
	 *
	 * @param orderedPosts 		The target ArrayList of the sorted posts
	 * @param post 				The post from which start recursion
	 *
	 * @see getPosts()
	 */
	private synchronized void getChildren( ArrayList<Post>orderedPosts, Post post ){
		orderedPosts.add( post );				// Add parent post first
		for( int replyID : post.getReplyIDs() )
			getChildren( orderedPosts, this.posts.get( replyID ));
	}   	

	/**
	 * Edits a post from this chatroom with the possibility of altering
	 * its content and image.
	 *
	 * @param postID 		The id of the post to be alterer
	 * @param text 			The new content of the post
	 * @param imagePath 	The new path to the image
	 * @return 				<code>true</code> if the edit was successful
	 * 						<code>false</code> otherwise (post does not exist)
	 *
	 * @see Post
	 */
	public synchronized Boolean editPost(int postID, String text, String imagePath){
		Post p = posts.get(postID);
		if(p!=null){
			p.setText(text);
			p.setImagePath(imagePath);
			return true;
		}
		return false;
	}

	/**
	 * Deletes a post from this chatroom and the
	 * corresponding replies.
	 *
	 * @param postID 		The id of the post to be removed
	 * @return 				<code>true</code> if the post was successfully deleted
	 * 						<code>false</code> otherwise (specified post does not exist)
	 */
	public Boolean deletePost(int postID){
		return (posts.remove(postID) != null);
	}	

	/**
	 * Adds a reply to a specified post in this chatroom.
	 * The reply cannot be delayed or be attached
	 * to an image.
	 *
	 * @param parentID		The identifier of the parent post
	 * @param text 			The content of the reply
	 * @param source 		The User identifier (login) that wrote the reply
	 *
	 * @return 				<code>true</code> if the reply was successfully added
	 * 						<code>false</code> otherwise (specified parent post ID does not exist)
	 */
	public Boolean addReply(int parentID, String text, String source){
		Post p = posts.get(parentID);
		if(p!=null){
			int rLevel = p.getReplyLevel()+1;
			Post reply = new Post(source, text, parentID, rLevel);
			addPost(reply);
			return true;
		}
		return false;
	}

	/**
	 * Gets the last ID attributed to a post in this chatroom,
	 * which is equivalent to the greater ID.
	 *
	 * @return				the maximum post ID in this chatroom
	 */
	public int getMaxPostID(){
		int maxID = 0, tmp;
		Iterator<Post> it = posts.values().iterator();
		while(it.hasNext()){
			tmp = it.next().getID();
			if( tmp > maxID )
				maxID = tmp;
		}
		return maxID;
	}
}