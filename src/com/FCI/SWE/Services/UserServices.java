package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.Friend;
import com.FCI.SWE.ServicesModels.FriendTimelinePost;
import com.FCI.SWE.ServicesModels.Hashtage;
import com.FCI.SWE.ServicesModels.MNotification;
import com.FCI.SWE.ServicesModels.Message;
import com.FCI.SWE.ServicesModels.MyTimelinePost;
import com.FCI.SWE.ServicesModels.Notifications;
import com.FCI.SWE.ServicesModels.Post;
import com.FCI.SWE.ServicesModels.Posts;
import com.FCI.SWE.ServicesModels.SRNotification;
import com.FCI.SWE.ServicesModels.UserEntity;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class UserServices {
	
	
	/*@GET
	@Path("/index")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}*/


		/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
			object.put("id", user.getId());
			
		}
		return object.toString();

	}
	@POST
	@Path("/AddfriendService")
	public String addService(@FormParam("sname") String sname,@FormParam("uname") String uname) {
		JSONObject object = new JSONObject();
		boolean user = UserEntity.getUser(uname);
		if (user == false) {
			object.put("Status", "Failed");

		} else {
			Friend F = new Friend(sname,uname , "waiting");
			F.saveFriend();
			Notifications no=new SRNotification(uname,sname);
			   UserEntity us=new UserEntity(uname,sname,"");
			   us.addnotification(no);
			   
			  
			object.put("Status", "OK");
			
			
			
			
		}
		return object.toString();

	}
	@POST
	@Path("/requestService")
	public String requestService(@FormParam("sname") String sname,@FormParam("uname") String uname) {
		JSONObject object = new JSONObject();
		boolean friend=Friend.request(sname,uname,"accept");
		if (friend == false) {
			object.put("Status", "Failed");

		} else {
			
			
			object.put("Status", "OK");
		}
		return object.toString();

	}
	@POST
	@Path("/rejectService")
	public String rejectService(@FormParam("sname") String sname,@FormParam("uname") String uname) {
		JSONObject object = new JSONObject();
		boolean friend=Friend.request(sname,uname,"reject");
		if (friend == false) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			
		}
		return object.toString();

	}
	@POST
	@Path("/sendmessage")
	public String requestService(@FormParam("sname") String sname,
			@FormParam("rname") String rname,@FormParam("message") String message) {
		JSONObject object = new JSONObject();
		boolean f=UserEntity.getUser(rname);
		if (f == false) {
			object.put("Status", "Failed");

		} else {
			
			Message m = new Message(sname,rname ,message );
			m.savemessage();
			Notifications no=new MNotification(rname,sname);
			   UserEntity us=new UserEntity(rname,sname,"");
			   us.addnotification(no);
			
			object.put("Status", "OK");
			}
		return object.toString();

	}
    @POST
	@Path("/searchuser")
	public String searchService(@FormParam("rname") String rname) {
		JSONArray object = new JSONArray();
		Vector<UserEntity> users=UserEntity.searchuser(rname);
		boolean f=UserEntity.getUser(rname);
		if (f == false) {
			for(UserEntity u: users){
				JSONObject ob=new JSONObject();
			ob.put("name", null);
			ob.put("password", null);
			ob.put("email", null);
			object.add(ob);
			}

		} else {
			for(UserEntity u: users){
				JSONObject ob=new JSONObject();
			ob.put("name", u.getName());
			ob.put("password", u.getPass());
			ob.put("email", u.getEmail());
			object.add(ob);
			}
			
			
			
			
		
			
		}
		return object.toString();

	}
    
   
	
	
	 /*@POST
	@Path("/searchuser")
	public String searchService(@FormParam("rname") String rname) {
		JSONArray object = new JSONArray();
		Vector<UserEntity> users=UserEntity.searchuser(rname);
   for(int i=0;i<users.size();i+
	   System.out.println(users.elementAt(i).getName());
			for(UserEntity u: users){
				JSONObject ob=new JSONObject();
			ob.put("name", u.getName());
			ob.put("password", u.getPass());
			ob.put("email", u.getEmail());
			object.add(ob);
			}
			
		return object.toString();

	}
	*/
////////////////////////////////////////////////////////////////////////////////
    
    @POST
	@Path("/postService")
	public String post(@FormParam("owner") String owner,
			@FormParam("to") String to,@FormParam("content") String content
			,@FormParam("privacy") String privacy) {
		JSONObject object = new JSONObject();
	//	Vector<MyTimelinePost> posts=MyTimelinePost.searchPosts(owner);
		
		//JSONArray array = new JSONArray();
		
		/*for(MyTimelinePost p: posts){
			JSONObject ob=new JSONObject();
		ob.put("owner", p.getowner());
		ob.put("content",p.getsetContent());
		ob.put("nLikes", p.getLikes());

		array.add(ob);
		}*/
	    //System.out.println("ss");
		
		
		
	    boolean f=Friend.checkFriend(owner, to);
	    
		if (f == false) {
			object.put("Status", "Failed");

		} else {
			Posts p;
			String hashtageName=new String();
			
			if(owner.equals(to))
			    p=new MyTimelinePost(owner,content,privacy);
			else
			    p=new FriendTimelinePost(owner,to,content,privacy);
	        
			p.savePost();
			
			if(content.contains("#")){	
				
				for(int i=content.indexOf("#");i<content.length();i++){
					if(content.charAt(i)==' ')
						break;
					else
						hashtageName += content.charAt(i);
				}
			
			Hashtage hash = new Hashtage();
		    
			if(hash.getHashtage(hashtageName)== false){
		    	hash.setName(hashtageName);
		    	hash.setPostsID(Posts.postid);
		    	hash.setPostsNum(1);
		    	hash.saveHashtage();
		    	
		    	
		    }
		    else{
		    	hash.updateHashtage(hashtageName, Posts.postid);
		    }
		}
			object.put("Status", "OK");	
		}
	    return object.toString();
	}
    
 
    
    ////////////////////////////////////////////////////
    @POST
   	@Path("/mypostService")
   	public String mypost(@FormParam("owner") String owner) {
   		JSONObject object = new JSONObject();
   		Vector<String> posts=new Vector<String>();
   		posts=MyTimelinePost.searchPost(owner);
   		
   		//System.out.println(posts.size());
   		//for(MyTimelinePost p: posts){
   	JSONObject ob=new JSONObject();
    ob.put("posts", posts);
   		//System.out.println(ob.toString());
   		//System.out.println(array.get(0).toString());
      
   		
   		
   		//System.out.println("ss");

   		
   	    //boolean f=Friend.checkFriend(owner, to);
   	    
   	return ob.toString();
   		    
   			

   	}


/////////////////////////////////////////LikeService
    @POST
   	@Path("/LikeService")
   	public String likepost(@FormParam("owner") String owner
   			,@FormParam("posted") String post) {
   
    	
    	
    	JSONObject object = new JSONObject();
    	
		boolean f=MyTimelinePost.updatePosts(post);
		
   	//JSONObject ob=new JSONObject();
   	
    //ob.put("posts", posts);
   		//System.out.println(ob.toString());
   		//System.out.println(array.get(0).toString());
      
   		
   		
   		//System.out.println("ss");

   		
   	    //boolean f=Friend.checkFriend(owner, to);
   	    
		if(f==true)
		object.put("Status", "OK");
		
		else
			object.put("Status", "Failed");
		
		return object.toString();
   		    
   			

   	}
//////////////////////////////////////////////////share
    
    @POST
   	@Path("/shareService")
   	public String sharepost(@FormParam("owner") String owner,@FormParam("posted") String post) {
   
    	
    	
    	JSONObject object = new JSONObject();
    	
		boolean f=MyTimelinePost.share(post,owner);
		
   	//JSONObject ob=new JSONObject();
   	
    //ob.put("posts", posts);
   		//System.out.println(ob.toString());
   		//System.out.println(array.get(0).toString());
      
   		
   		
   		//System.out.println("ss");

   		
   	    //boolean f=Friend.checkFriend(owner, to);
   	    
		if(f==true)
		object.put("Status", "OK");
		
		else
			object.put("Status", "Failed");
		
		return object.toString();
   		    
   			

   	}
    

    @POST
   	@Path("/hashtageTrendService")
   	public String hashtageTrendServic(@FormParam("owner") String owner) {
   		System.out.println("mkkhn j");
   		ArrayList<String> myList=new ArrayList<String>();
   		System.out.println(Hashtage.hashtageTrends());
   				myList = Hashtage.hashtageTrends();
   				for(int i=0;i<myList.size();i++){
   					System.out.println(i+"--");
   					System.out.println(myList.get(i));
   				}
   				System.out.println("mkkhn j");
   			JSONObject ob=new JSONObject();
   	          ob.put("myList", myList);   	    
   	return ob.toString();
   		    
   	}
    



}


