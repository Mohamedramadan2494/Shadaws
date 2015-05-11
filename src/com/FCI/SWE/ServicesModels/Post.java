package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Post {

	String owner,to,content,privacy;
	int nLike;
	static public long postID;
	
    public Post(){
    	
    	owner="";
    	to="";
    	content="";
    	nLike=0;
    	privacy="public";
    	
    	
    }
  public Post(String owner,String to,String content,String privacy){
    	
    	this.owner=owner;
    	this.to=to;
    	this.content=content;
    	this.privacy=privacy;
    	
    	
    }
	public void setOwner(String own){
		
		owner=own;
	}
    public void setContent(String con){
		
    	content=con;
	}
    public void setLike(int likes){
		
    	nLike=likes;
	}


    public String getContent(){
		
    	return content;
	}
     public String Owner(){
		
    	return owner;
	}
  
    public int getLike(){	
  	return nLike;
	}


    
	public Boolean savePost() {
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
		datastore.put(employee);

		return true;

	}
	


}
