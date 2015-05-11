package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class GroupMessage {
	String owner,sender,name,message;
	ArrayList<String> users ;
	ArrayList<String>messages;
	public GroupMessage(){
		users=new ArrayList<String>();
		messages=new ArrayList<String>();
//		users=null;
	}
	
	
	/**
	 * Constructor accepts request data
	 * 
	 * @param s
	 *            message sender
	 * @param r
	 *           message receiver group
	 * @param m
	 *         text message 
	 */
	
	/*public GroupMessage(String s, String r, String m,String n) {
		this.sender=s;
		this.reciever=r;
		this.message=m;
		this.notify=n;
	}*/
	
	public void setSender(String s){
		this.sender = s;
	}
	
	
	
	public void setMessage(String s){
		this.messages.add(s);
	}
	
	public void setMessage2(String s){
		this.message=s;
	}
	
	public void setName(String s){
		this.name=s;
	}
	
	public void setOwner(String s){
		this.owner=s;
		this.users.add(s);
		this.messages.add("groupcreated");
	}
	public void setOwner2(String s){
		this.owner=s;
	}
	public void setUsers(String s){
		this.users.add(s);
	}
	

	public String getsender() {
		return sender;
	}
	
	public String getOwner() {
		return owner;
	}

	public ArrayList getmessage() {
		return messages;
	}
	
	public ArrayList getUsers() {
		return users;
	}
	
	public String getName() {
		return name;
	}
	
	
	public boolean saveGroupMessage() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("groupMessages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity gmessage = new Entity("groupMessages", list.size() + 1);

		gmessage.setProperty("MessageSender", this.sender);
		gmessage.setProperty("MessagegroupReciever", this.name);
		gmessage.setProperty("TextMessage", this.messages);
		gmessage.setProperty("UsersNotify", users);
		gmessage.setProperty("groupOwner", this.owner);
		datastore.put(gmessage);

		return true;

	}
	
	public Boolean updateGroupMessage(String user) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("groupMessages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("MessagegroupReciever").toString().equals(name)) {
				
				owner=(String) entity.getProperty("groupOwner");
				
				users= (ArrayList<String>) entity.getProperty("UsersNotify");
				if(users.contains(user)){
					messages=(ArrayList<String>) entity.getProperty("TextMessage");
					
				
				messages=(ArrayList<String>) entity.getProperty("TextMessage");
				
				messages.add(user+" : "+message);
				
				entity.setProperty("UsersNotify", this.users);
				entity.setProperty("MessageSender", this.sender);
				entity.setProperty("TextMessage", this.messages);
				datastore.put(entity);
				
				return true;
				}
			}
			}
		
		return false;

	}
	
	public Boolean updateGroupUsers() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("groupMessages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("MessagegroupReciever").toString().equals(name)&&entity.getProperty("groupOwner").toString().equals(owner)) {
				
				users= (ArrayList<String>) entity.getProperty("UsersNotify");
				messages=(ArrayList<String>) entity.getProperty("TextMessage");
				users.add(sender);
				
				entity.setProperty("UsersNotify", this.users);
				entity.setProperty("MessageSender", this.sender);
				entity.setProperty("TextMessage", this.messages);
				datastore.put(entity);
				
				return true;
			}
			}
		
		return false;


	}
	
	public static boolean getGroup(String name) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("groupMessages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("MessagegroupReciever").toString().equals(name)) {
				return true;
			}				
		}
		return false;
	}
	
	
	public ArrayList<String> showGroupMessage(String Gname,String username) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("groupMessages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			
			if (entity.getProperty("MessagegroupReciever").toString().equals(Gname)) {
				users= (ArrayList<String>) entity.getProperty("UsersNotify");
				
			if(users.contains(username)){
				messages=(ArrayList<String>) entity.getProperty("TextMessage");
				
			}
			}				
		}
		return messages;
	}
	
}
