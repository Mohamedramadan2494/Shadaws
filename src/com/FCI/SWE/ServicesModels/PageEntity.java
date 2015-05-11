package com.FCI.SWE.ServicesModels;
import java.util.ArrayList;
import java.util.List;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.search.query.QueryParser.value_return;
public class PageEntity {
	private String name;
	private String type;
	private String category;
	private int numOfLikes;
	private int reach;
	private String ownerName;
	ArrayList <String>fans;
	
	public PageEntity(){
		fans=new ArrayList<String>();
		}
	
	public PageEntity(String na,String t,String c,int n,int r,String o,ArrayList <String>f){
		this.name=na;
		this.type=t;
		this.category=c;
		this.numOfLikes=n;
		this.reach=r;
		this.ownerName=o;
		this.fans=f;
		//fans=new ArrayList<String>();
		}
	
	public String getName(){
		return name;
	}
	   
	public String getType(){
		return type;
	}
	
	public String getCategory(){
		return category;
	}
	
	public String getOwnerName(){
		return ownerName;
	}
	public int getNumOfLikes(){
		return numOfLikes;
	}
	public long getReach(){
		return reach;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setType(String ty){
		this.type = ty;
	}
	
	public void setCategory(String cate){
		this.category = cate;
	}
	
	public void setOwnerName(String na){
		this.ownerName = na;
	    fans.add(na);
	}
	
	public void setNumOfLikes(int numOfLi){
		this.numOfLikes = numOfLi;
	}
	
	public void setReach(int re){
		this.reach = re;
	}

	public boolean savePage(PageEntity p) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Pages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity Page = new Entity("Pages", list.size() + 1);

		Page.setProperty("name", this.name);
		Page.setProperty("type", this.type);
		Page.setProperty("category", this.category);
		Page.setProperty("owner_name",this.ownerName);
		Page.setProperty("numOfLikes",0);
		Page.setProperty("reach",0);
		Page.setProperty("fans",this.fans);
		if(datastore.put(Page).isComplete())
			return true;
		else return false;

	}
	
	
	public boolean likepage( String pname) {
		int n;
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Pages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		  String dd=User.getCurrentActiveUser().getName();
		  ArrayList<String>fan;
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("name").toString().equals(pname)) {
				n=Integer.valueOf(entity.getProperty("numOfLikes").toString());
				fan=(ArrayList<String>) entity.getProperty("fans");
				fan.add(dd);
				entity.setProperty("numOfLikes",n+1);
				entity.setProperty("reach",n+1);
				entity.setProperty("fans",fan);
				datastore.put(entity);
				return true;
			}
			}
		return false;
	}
	
	/*public static boolean getPage(String name) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Pages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("name").toString().equals(name)) {
				
				return true;
			}
			}
         return false;
	}*/
	public static boolean check(String owner,String page) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Pages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("owner_name").toString().
					equals(owner)&&entity.getProperty("name").
					toString().equals(page)) {
				
				return true;
			}
			
		}

		return false;
	}
		
}
