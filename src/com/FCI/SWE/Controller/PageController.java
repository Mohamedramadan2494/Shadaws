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
public class PageController {
	
	@GET
	@Path("/Page")
	public Response Createpage() {
		return Response.ok(new Viewable("/jsp/Page")).build();
	}

	@GET
	@Path("/Like")
	public Response LikePage() {
		return Response.ok(new Viewable("/jsp/Like")).build();
	}
	@GET
	@Path("/PagePost")
	public Response postpage() {
		return Response.ok(new Viewable("/jsp/PagePost")).build();
	}
	@POST
	@Path("/responsem")
	public String createPage(@FormParam("name") String name,
			@FormParam("type") String typee, @FormParam("category") String cate) {
		
		String serviceUrl = "http://localhost:8888/rest/CreatePageService";
		String urlParameters = "owner_name=" + User.getCurrentActiveUser().getName()
				+ "&name=" + name + "&type=" + typee + "&category=" + cate;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Page created Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	@POST
	@Path("/res")
	public String likepage(@FormParam("pname") String pname) {
		
		String serviceUrl = "http://localhost:8888/rest/LikePageSevice";
		String urlParameters ="pname=" + pname ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "page liked Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
//////////////////////////////////////////
@POST
@Path("/pagepooost")
@Produces(MediaType.TEXT_PLAIN)
public String postPage(@FormParam("page") String page, @FormParam("content") String content,
		@FormParam("privacy") String privacy) {

String serviceUrl = "http://localhost:8888/rest/PagePostService";
User u1=User.getCurrentActiveUser();
String urlParameters = "owner=" + u1.getName() +"&page=" + page+"&content=" + content + "&privacy=" + privacy;
String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
"application/x-www-form-urlencoded;charset=UTF-8");
JSONParser parser = new JSONParser();
Object obj;

try {
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
	

}
