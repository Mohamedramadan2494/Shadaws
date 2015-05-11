package com.FCI.SWE.ServicesModels;

import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class MyTimelinePost extends Posts {


	   public MyTimelinePost(){
	    	
	    	this.owner="";
	    	this.content="";	
	    	
	    }
	    
	public MyTimelinePost(String owner,String content,String privacy){
		
		this.privacy=privacy;
    	this.owner=owner;
    	this.content=content;	
    	
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
	public String getsetContent() {
		// TODO Auto-generated method stub
		return content;
	}



	@Override
	public String getowner() {
		// TODO Auto-generated method stub
		return owner;
	}



	@Override
	public int getLikes() {
		// TODO Auto-generated method stub
		return nLike;
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
		employee.setProperty("To", this.owner);
		employee.setProperty("content", this.content);
		employee.setProperty("nLikes", this.nLike);
		employee.setProperty("privacy", this.privacy);
		
		this.postid = datastore.put(employee).getId();

		return true;
	}
	
	
	/*public static Vector<MyTimelinePost> searchPosts(String name){
		DatastoreService datastore=DatastoreServiceFactory.getDatastoreService();
		Query q1=new Query("posts");
		PreparedQuery prepare=datastore.prepare(q1);
		Vector<MyTimelinePost> posts=new Vector<MyTimelinePost>();
		
		
		for(Entity entity : prepare.asIterable()){
			String own=entity.getProperty("owner").toString();
			
			if(own.equals(name)){
				MyTimelinePost one=new MyTimelinePost(entity.getProperty("owner").toString(),entity.getProperty("content").toString());
				//System.out.println("ffs");
		one.setLikes(Integer.valueOf(entity.getProperty("nLikes").toString()));
				posts.add(one);
			}
			
		}
		return posts;
		
	}*/
	
	public static Vector<String> searchPost(String name){
		DatastoreService datastore=DatastoreServiceFactory.getDatastoreService();
		//Filter filter1= new FilterPredicate("owner",FilterOperator.EQUAL,name);
		
		Vector<String> posts=new Vector<String>();
		
		Query gaeQuery = new Query("posts");
		PreparedQuery prepare = datastore.prepare(gaeQuery);
		
		//Query q1=new Query("posts").setFilter(filter1);
		//PreparedQuery prepare=datastore.prepare(q1);
		for(Entity entity : prepare.asIterable()){
			String one = null;
			
			
			if(entity.getProperty("owner").toString().equals(name)||entity.getProperty("privacy").toString().equals("public"))
			 one=entity.getProperty("content").toString();
			
			else if ( entity.getProperty("privacy").toString().equals("private")&&Friend.checkFriend(name,entity.getProperty("owner").toString()) )
			one=entity.getProperty("content").toString();
			
			
				posts.add(one);
			}
			
		return posts;
		
	}


	public static MyTimelinePost parseinfo(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			MyTimelinePost p1=new MyTimelinePost();
			p1.setowner(object.get("owner").toString());
			p1.setContent(object.get("content").toString());
			//p1.setLikes(Integer.valueOf(object.get("content").toString()));
			p1.setLikes(0);
			return p1;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	
	public static boolean updatePosts(String cont) {
		
		
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		

		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (  entity.getProperty("content").toString().equals(cont) ) {
			
				int n =Integer.valueOf(entity.getProperty("nLikes").toString());
				n++;
			
				entity.setProperty("nLikes",n);
				datastore.put(entity);
				return true;
			}
			
		}
		return false;
	}


	public static boolean share(String cont,String own) {
		
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		

		Query gaeQuery = new Query("posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (  entity.getProperty("content").toString().equals(cont) ) {
				
				int n =Integer.valueOf(entity.getProperty("nLikes").toString());
				String priv=entity.getProperty("privacy").toString();
				MyTimelinePost p=new MyTimelinePost(own, cont, priv);
				p.savePost();
				
				
				return true;
			}
			
		}
		return false;
	}
	


}
