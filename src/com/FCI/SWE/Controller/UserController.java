package com.FCI.SWE.Controller;

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
import java.util.Set;
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
import com.FCI.SWE.ServicesModels.MyTimelinePost;
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
@Produces("text/html")
public class UserController {
	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@GET
	@Path("/signup")
	public Response signUp() {
		return Response.ok(new Viewable("/jsp/register")).build();
	}

	/**
	 * Action function to render home page of application, home page contains
	 * only signup and login buttons
	 * 
	 * @return enty point page (Home page of this application)
	 */
	@GET
	@Path("/")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}

	/**
	 * Action function to render login page this function will be executed using
	 * url like this /rest/login
	 * 
	 * @return login page
	 */
	@GET
	@Path("/login")
	public Response login() {
		return Response.ok(new Viewable("/jsp/login")).build();
	}
	
	@GET
	@Path("/search")
	public Response search(){
		return Response.ok(new Viewable("/jsp/search")).build();
	}
	@GET
	@Path("/signout")
	public Response signout1() {
		User.setCurrentActiveUser();
		return Response.ok(new Viewable("/jsp/login")).build();
	}
	@GET
	@Path("/Addfriend")
	public Response Addfriend() {
		return Response.ok(new Viewable("/jsp/Addfriend")).build();
	}
	
	@GET
	@Path("/request")
	public Response request() {
		return Response.ok(new Viewable("/jsp/request")).build();
	}
	
	
	@GET
	@Path("/reject")
	public Response reject() {
		return Response.ok(new Viewable("/jsp/reject")).build();
	}
	
	@GET
	@Path("/sendmessage")
	public Response sendmessage() {
		return Response.ok(new Viewable("/jsp/sendmessage")).build();
	}
	///////////////////////////////
	@GET
	@Path("/news")
	public Response news() {
		return Response.ok(new Viewable("/jsp/news")).build();
	}
	
	/*@GET
	@Path("/SendPost")
	public Response SendPost() {
		return Response.ok(new Viewable("/jsp/Sendpost")).build();
	}*/
	@GET
	@Path("/postFriend")
	public Response postFriend() {
		return Response.ok(new Viewable("/jsp/postFriend")).build();
	}
	@GET
	@Path("/SendPostFriend")
	public Response SendPostFriend() {
		return Response.ok(new Viewable("/jsp/SendPostFriend")).build();
	}
	////////////////////
	@GET
	@Path("/ShowPosts")
	public Response ShowPosts() {
		return Response.ok(new Viewable("/jsp/ShowPosts")).build();
	}
	
	/*@GET
	@Path("/Sendpost")
	public Response Sendpost() {
		return Response.ok(new Viewable("/social/responseMypost")).build();
	}*/
	
	
	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/response")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {

		String serviceUrl = "http://localhost:8888/rest/RegistrationService";
		String urlParameters = "uname=" + uname + "&email=" + email
				+ "&password=" + pass;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Registered Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}

	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return Home page view
	 */
	@POST
	@Path("/home")
	@Produces("text/html")
	public Response home(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		String urlParameters = "uname=" + uname + "&password=" + pass;

		String retJson = Connection.connect(
				"http://localhost:8888/rest/LoginService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			Map<String, String> map = new HashMap<String, String>();
			User user = User.getUser(object.toJSONString());
			map.put("name", user.getName());
			map.put("email", user.getEmail());
			return Response.ok(new Viewable("/jsp/home", map)).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;

	}
	
	@POST
	@Path("/responses")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("uname") String uname) {

		String serviceUrl = "http://localhost:8888/rest/social/Addfriend/";
		User u=User.getCurrentActiveUser();
		String urlParameters =  "sname="+ u.getName() +"&uname=" + uname ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		
	
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK")){
			
				
				return "sented sucessfully";
				
			
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Error Name not exist";
	}
	@POST
	@Path("/responsess")
	@Produces(MediaType.TEXT_PLAIN)
	public String request(@FormParam("uname") String uname) {

		String serviceUrl = "http://localhost:8888/rest/requestService";
		User u=User.getCurrentActiveUser();
		String urlParameters =  "sname="+ u.getName() +"&uname=" + uname ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		
	
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK")){
			
				
				return "Ok operation Done";
				
			
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}
	
	@POST
	@Path("/responsesss")
	@Produces(MediaType.TEXT_PLAIN)
	public String reject(@FormParam("uname") String uname) {

		String serviceUrl = "http://localhost:8888/rest/rejectService";
		User u=User.getCurrentActiveUser();
		String urlParameters =  "sname="+ u.getName() +"&uname=" + uname ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		
	
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK")){
			
				
				return "Ok operation Done";
				
			
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}
	

	@POST
	@Path("/message")
	@Produces(MediaType.TEXT_PLAIN)
	public String sendmessage(@FormParam("rname") String rname,@FormParam("message") String message) {

		String serviceUrl = "http://localhost:8888/rest/sendmessage";
		User u1=User.getCurrentActiveUser();
		String urlParameters = "sname=" + u1.getName()+"&rname=" + rname +"&message=" + message ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		
	
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK")){
			
				
				return "Ok message sent";
				
			
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed";
	}

	@POST
	@Path("/join")
	@Produces(MediaType.TEXT_PLAIN)
	public String join(@FormParam("rname") String rname,@FormParam("user") String user) {

		String serviceUrl = "http://localhost:8888/rest/joinService";
		User u1=User.getCurrentActiveUser();
		String urlParameters = "sname=" + u1.getName()+"&rname=" + rname +"&user=" +user ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK")){
			
				
				return "Ok message sent";
				
			
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed";
	}
	@POST
	@Path("/searches")
	@Produces("text/html")
	public Response search(@FormParam("fname") String fname) {
       
		String serviceUrl = "http://localhost:8888/rest/searchuser";
		User u1=User.getCurrentActiveUser();
		String urlParameters = "fname=" + fname  ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Map<String,Vector<User>>users=new HashMap<String,Vector<User>>();
		
	
		try {
			
			JSONArray obj = (JSONArray) parser.parse(retJson);
			Vector<User>users1=new Vector<User>();
			for(int i=0;i<obj.size();i++){
				JSONObject object=(JSONObject) obj.get(i);
				
				users1.add(User.parseinfo(object.toString()));
				
			}
			users.put("users", users1);
		
				System.out.println(users1.size());
			
			return Response.ok(new Viewable("/jsp/userwall", users)).build();
			

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



@POST
@Path("/notifications")
@Produces("text/html")
public String notifications() {
   
	String serviceUrl = "http://localhost:8888/rest/notificationsService";
	User u=User.getCurrentActiveUser();
	String urlParameters =  "lname="+ u.getName() ;
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	JSONParser parser = new JSONParser();
	Object obj;
	

	try {
		// System.out.println(retJson);
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		if (object.get("Status").equals("OK")){
		
			
			return "Ok operation Done";
			
		
		}

	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

return "failed";
}

///////////////////////////////////////////////////////////////////////

  @POST
  @Path("/postresponse")
  @Produces(MediaType.TEXT_PLAIN)
  public String post(@FormParam("content") String content,@FormParam("privacy")
  String privacy,@FormParam("feeling") String feeling) {
	
	  
	
	if(!feeling.equals("no"))
		content=content+"        (Feel "+feeling+")";

	
	String serviceUrl = "http://localhost:8888/rest/postService";
	User u1=User.getCurrentActiveUser();
	String urlParameters = "owner=" + u1.getName() +"&to=" + u1.getName() +"&content=" + content+"&privacy=" + privacy ;
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	JSONParser parser = new JSONParser();
	Object obj;
	

	try {
		// System.out.println(retJson);
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		if (object.get("Status").equals("OK")){
		
			
			return "Ok post published";
			
		
		}

	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "Failed";
}

@POST
@Path("/postFriendresponse")
@Produces(MediaType.TEXT_PLAIN)
public String postFriend(@FormParam("friend") String friend, @FormParam("content") String content,@FormParam("privacy") String privacy) {

	String serviceUrl = "http://localhost:8888/rest/postService";
	User u1=User.getCurrentActiveUser();
	String urlParameters = "owner=" + u1.getName() +"&to=" + friend+"&content=" + content+"&privacy=" + privacy ;
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	JSONParser parser = new JSONParser();
	Object obj;
	

	try {
		// System.out.println(retJson);
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		if (object.get("Status").equals("OK")){
		
			
			return "Ok post published";
			
		
		}

	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "Failed";
}
///////////////////////////////////////////like
@POST
@Path("/Likeresponse")
@Produces(MediaType.TEXT_PLAIN)
   public String Like(@FormParam("posted") String post) {
	
	String serviceUrl = "http://localhost:8888/rest/LikeService";
	User u1=User.getCurrentActiveUser();
	String urlParameters = "owner=" + u1.getName() +"&posted=" + post;
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	JSONParser parser = new JSONParser();
	Object obj;
	

	try {
		// System.out.println(retJson);
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		if (object.get("Status").equals("OK")){
		
			
			return "Ok post liked";
			
		
		}

	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "Failed";
}
////////////////////////////////////////////////////share

@POST
@Path("/shareresponse")
@Produces(MediaType.TEXT_PLAIN)
   public String share(@FormParam("posted") String post) {
	System.out.println(post);
	String serviceUrl = "http://localhost:8888/rest/shareService";
	User u1=User.getCurrentActiveUser();
	String urlParameters = "owner=" + u1.getName() +"&posted=" + post;
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	JSONParser parser = new JSONParser();
	Object obj;
	

	try {
		// System.out.println(retJson);
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		if (object.get("Status").equals("OK")){
		
			
			return "Ok post shared";
			
		
		}

	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "Failed";
}










@POST
@Path("/responseMypost")
@Produces("text/html")
public Response mypost() {

	String serviceUrl = "http://localhost:8888/rest/mypostService";
	User u1=User.getCurrentActiveUser();
	String urlParameters = "owner=" + u1.getName();
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	JSONParser parser = new JSONParser();
	Object obj;


	try {
		obj= parser.parse(retJson);
       JSONObject object=(JSONObject)obj;
		return Response.ok(new Viewable("/jsp/Sendpost", object)).build();
		
	    
		
		
	

	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}




@POST
@Path("/hashtagetrendsresponse")
@Produces("text/html")
public Response hashtageTrend() {

	String serviceUrl = "http://localhost:8888/rest/hashtageTrendService";
	User u1=User.getCurrentActiveUser();
	String urlParameters = "owner=" + u1.getName();
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
			"application/x-www-form-urlencoded;charset=UTF-8");
	JSONParser parser = new JSONParser();
	Object obj;
	
	System.out.println("asdhakdy");

	try {
		obj= parser.parse(retJson);
       JSONObject object=(JSONObject)obj;
       System.out.println("asdhakdy2");
		return Response.ok(new Viewable("/jsp/Hashtagetrends", object)).build();
		
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("asdhakdy3");
	}
	return null;
}


}