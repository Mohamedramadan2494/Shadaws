package com.FCI.SWE.Controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;

@Path("/")
@Produces("text/html")
public class GroupController {
	
	@GET
	@Path("/groupManager")
	public Response groupManagr() {
		return Response.ok(new Viewable("/jsp/groupManager")).build();
	}
	
	@GET
	@Path("/createGroup")
	public Response createGroup() {
		return Response.ok(new Viewable("/jsp/createGroup")).build();
	}
	@GET
	@Path("/groupchat")
	public Response groupchat() {
		return Response.ok(new Viewable("/jsp/groupchat")).build();
	}
	
	@GET
	@Path("/joinGroup")
	public Response joinGroup() {
		return Response.ok(new Viewable("/jsp/joinGroup")).build();
	}
	@GET
	@Path("/AddGroupMember")
	public Response joinservice() {
		return Response.ok(new Viewable("/jsp/AddGroupMember")).build();
	}
	
	@GET
	@Path("/showmessagegroup")
	public Response showmessagegroup() {
		return Response.ok(new Viewable("/jsp/showmessagegroup")).build();
	}


	@POST
	@Path("/responsegg")
	public String createGroup(@FormParam("name") String name,
			@FormParam("desc") String desc, @FormParam("privacy") String privacy) {

		String serviceUrl = "http://localhost:8888/rest/CreateGroupService";
		String urlParameters = "user_id=" + User.getCurrentActiveUser().getId()
				+ "&name=" + name + "&desc=" + desc + "&privacy=" + privacy;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Group created Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	@POST
	@Path("/responsesg")
	public String joinGroup( @FormParam("gname") String Gname) {

		String serviceUrl = "http://localhost:8888/rest/JoinGroupService";
		String urlParameters = "user_id=" + User.getCurrentActiveUser().getId() + "&gname=" + Gname ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "user joint group Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	@POST
	@Path("/GroupChatr")
	public String GroupChat(@FormParam("gname") String Gname,@FormParam("message") String msg) {

		String serviceUrl = "http://localhost:8888/rest/GroupChatService";
		String urlParameters = "user_name=" + User.getCurrentActiveUser().getName() + "&gname=" + Gname + "&message=" + msg ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "message sent to group Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	@POST
	@Path("/responseg")
	public String createConversation(@FormParam("name") String name) {

		String serviceUrl = "http://localhost:8888/rest/CreateGroupConversationService";
		String urlParameters = "user_name=" + User.getCurrentActiveUser().getName()
				+ "&name=" + name ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Group Conversation created Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@POST
	@Path("/adder")
	@Produces(MediaType.TEXT_PLAIN)
	public String addService(@FormParam("Gname") String Gname,@FormParam("user") String user) {

		
		String serviceUrl = "http://localhost:8888/rest/addService";
		User u1=User.getCurrentActiveUser();
		String urlParameters = "user_name=" + u1.getName()+"&Gname=" + Gname +"&user=" +user ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		JSONParser parser = new JSONParser();
		Object obj;
		
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK")){
			
				
				return "Ok user add";
				
			
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed";
	}
	
	@POST
	@Path("/showgroupmessageresponse")
	public Response showgroup(@FormParam("Gname") String Gname) {
		
		//Gname="mki";
		String serviceUrl = "http://localhost:8888/rest/showGroupChatService";
		String urlParameters = "user_name=" + User.getCurrentActiveUser().getName()+"&Gname=" + Gname ;
		System.out.println(Gname);
		
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		System.out.println("c1");
			try {
				obj= parser.parse(retJson);
		       JSONObject object=(JSONObject)obj;
		       
		       
				return Response.ok(new Viewable("/jsp/showgroupmessages", object)).build();
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("c3");
				e.printStackTrace();
			}
			
		return null;
	}
	

}

