package com.FCI.SWE.Services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.ServicesModels.GMnotification;
import com.FCI.SWE.ServicesModels.GroupEntity;
import com.FCI.SWE.ServicesModels.GroupMessage;
import com.FCI.SWE.ServicesModels.Notifications;
import com.FCI.SWE.ServicesModels.UserEntity;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class GroupServices {

	@POST
	@Path("/CreateGroupService")
	public String createGroup(@FormParam("user_id") String userId,
			@FormParam("name") String name,
			@FormParam("desc") String desc,
			@FormParam("privacy") String privacy) {
		
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setDescription(desc);
		groupEntity.setName(name);
		groupEntity.setOwnerId(Long.parseLong(userId));
		groupEntity.setPrivacy(privacy);
		JSONObject json = new JSONObject();
		if(groupEntity.saveGroup())
			json.put("Status", "OK");
		else
			json.put("Status", "Failed");
		return json.toJSONString();
	}

	@POST
	@Path("/JoinGroupService")
	public String joinGroup(@FormParam("user_id") String userId,
			@FormParam("gname") String Gname ) {
		
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setName(Gname);
		JSONObject json = new JSONObject();
		if( groupEntity.joinGroup( userId ) )
			json.put("Status", "OK");
		else
			json.put("Status", "Failed");
		return json.toJSONString();
	}
	
	@POST
	@Path("/GroupChatService")
	public String GroupChat(@FormParam("user_name") String username
            , @FormParam("gname") String Gname,@FormParam("message") String msg) {
		
		GroupMessage g = new GroupMessage();
		g.setName(Gname);
		JSONObject json = new JSONObject();
		if(g.getGroup(Gname) ){
		g.setMessage2(msg);
		g.setSender(username);
		System.out.println("hell");
		if(g.updateGroupMessage(username)){
			ArrayList<String>nn=new ArrayList<String>();
			nn=g.getUsers();
			System.out.println(nn.get(0));
			for(int i=0;i<nn.size();i++){
			Notifications no=new GMnotification(nn.get(i),Gname);
			System.out.println(nn.get(i));
			UserEntity us=new UserEntity(Gname,username,"");
			us.addnotification(no);}
			
			json.put("Status", "OK");}
		else
			json.put("Status", "Failed");
		
		return json.toJSONString();
		}
		else{
			json.put("Status", "Failed");
			return json.toJSONString();
		}
		}
	
	@POST
	@Path("/CreateGroupConversationService")
	public String createGroupConversation(@FormParam("user_name") String user_name,
			@FormParam("name") String name) {
		
		GroupMessage groupMessages = new GroupMessage();
		
		groupMessages.setName(name);
		
		groupMessages.setOwner(user_name);

		
		JSONObject json = new JSONObject();
		if(groupMessages.saveGroupMessage())
			json.put("Status", "OK");
		else
			json.put("Status", "Failed");
		return json.toJSONString();
	}
	
	@POST
	@Path("/addService")
	public String addService(@FormParam("user_name") String username
            , @FormParam("Gname") String Gname,@FormParam("user") String user) {
		
		GroupMessage g = new GroupMessage();
		g.setName(Gname);
		UserEntity u=new UserEntity();
		u.setname(user);
		JSONObject json = new JSONObject();
		if(g.getGroup(Gname)&& u.getUser(user) ){
			
		g.setSender(user);
		g.setOwner2(username);
		
		if(g.updateGroupUsers()){
			
			json.put("Status", "OK");}
		else
			json.put("Status", "Failed");
		
		return json.toJSONString();
		}
		else{
			json.put("Status", "Failed");
			return json.toJSONString();
		}
		}
	
	
	@POST
	@Path("/showGroupChatService")
	public String showcreateGroupchatservice(@FormParam("Gname") String Gname , @FormParam("user_name") String user_name
			) {
		System.out.println("s1");
		GroupMessage groupMessages = new GroupMessage();
		ArrayList<String> myList=new ArrayList<String>();
		System.out.println("mki");
		myList = groupMessages.showGroupMessage(Gname, user_name);
		System.out.println(myList.get(0));
			JSONObject ob=new JSONObject();
	          ob.put("myList", myList);   	    
	return ob.toString();
	}	
}
