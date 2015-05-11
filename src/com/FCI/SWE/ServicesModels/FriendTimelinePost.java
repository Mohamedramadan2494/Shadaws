package com.FCI.SWE.ServicesModels;

import java.util.List;

import javax.ws.rs.FormParam;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class FriendTimelinePost extends Posts {
	String to;
	
	 public FriendTimelinePost(String owner,String to,String content,String privacy){
	    	
	    	this.owner=owner;
	    	this.to=to;
	    	this.content=content;	
	    	this.privacy=privacy;
	    }
	
	
	


	@Override
	public void setContent(String content) {
		this.content=content;
		
	}

	@Override
	public void setowner(String owner) {
		this.owner=owner;
		
	}

	@Override
	public void setLikes(int nLike) {
		this.nLike=nLike;
		
	}
	
	@Override
	public boolean savePost() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("posts", list.size() + 1);

		employee.setProperty("owner", this.owner);
		employee.setProperty("To", this.to);
		employee.setProperty("content", this.content);
		employee.setProperty("nLikes", this.nLike);
		employee.setProperty("privacy", this.privacy);
		this.postid = datastore.put(employee).getId();

		return true;
	}





	@Override
	public String getsetContent() {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public String getowner() {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public int getLikes() {
		// TODO Auto-generated method stub
		return 0;
	}






}
