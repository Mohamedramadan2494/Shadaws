package com.FCI.SWE.Services;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.Friend;
import com.FCI.SWE.ServicesModels.FriendTimelinePost;
import com.FCI.SWE.ServicesModels.GroupEntity;
import com.FCI.SWE.ServicesModels.Hashtage;
import com.FCI.SWE.ServicesModels.MyTimelinePost;
import com.FCI.SWE.ServicesModels.PageEntity;
import com.FCI.SWE.ServicesModels.PageTimelinePost;
import com.FCI.SWE.ServicesModels.Posts;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)

public class PageServices {
	@POST
	@Path("/CreatePageService")
	public String createPage(@FormParam("owner_name") String ownerName,
			@FormParam("name") String name,
			@FormParam("type") String typee,
			@FormParam("category") String cate) {
	
		
		PageEntity pageEntity = new PageEntity();
		pageEntity.setName(name);
		pageEntity.setType(typee);
		pageEntity.setOwnerName(ownerName);
		pageEntity.setCategory(cate);
		pageEntity.setNumOfLikes(0);
		pageEntity.setReach(0);
		JSONObject json = new JSONObject();
		if(pageEntity.savePage(pageEntity))
			json.put("Status", "OK");
		else
			json.put("Status", "Failed");
		return json.toJSONString();
	}
	
	@POST
	@Path("/LikePageSevice")
	public String likepage(@FormParam("pname") String pname) {
		PageEntity page = new PageEntity();
		JSONObject json = new JSONObject();
		if( page.likepage( pname ) )
			json.put("Status", "OK");
		else
			json.put("Status", "Failed");
		return json.toJSONString();
	}
	
	  //////////////////////////////////////////////////// salama work
    @POST
   	@Path("/PagePostService")
   	public String postPage(@FormParam("owner") String owner,@FormParam("page") String to
   			,@FormParam("content") String content,@FormParam("privacy") String privacy) {
   		JSONObject object = new JSONObject();

   	    PageEntity p=new PageEntity();
   
   		if (!(p.check(owner,to))) {
   		
   			object.put("Status", "Failed");

   		} else {
   			//Posts p;
   			PageTimelinePost t=new PageTimelinePost(owner,content,to,privacy);;
   			String hashtageName=new String();
   			
   	        
   			t.savePost();
   			
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
		
}
