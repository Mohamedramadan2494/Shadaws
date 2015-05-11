package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javassist.bytecode.Descriptor.Iterator;

import org.apache.jasper.tagplugins.jstl.core.Set;
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

public class Hashtage {
	String Name ;
	private long ID;
	int postsNum;
	ArrayList<Long>postsID;
	
	Hashtage (String Nam ,int pn,int id){
		this.Name=Nam;
		this.postsNum=pn;
		this.ID=id;
		postsID=new ArrayList<Long>();
	}
	public Hashtage() {
		postsID=new ArrayList<Long>();
		// TODO Auto-generated constructor stub
	}
	public void setID(long iD) {
		ID = iD;
	}
	public void setName(String name) {
		Name = name;
	}
	public void setPostsID(long pos) {
		this.postsID.add(pos);
	}
	
	
	public void setPostsNum(int postsNum) {
		this.postsNum = postsNum;
	}
	public long getID() {
		return ID;
	}
	public String getName() {
		return Name;
	}
	public ArrayList<Long> getPostsID() {
		return postsID;
	}
	public int getPostsNum() {
		return postsNum;
	}
	
	public boolean saveHashtage() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Hashtages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity hashtage = new Entity("Hashtages", list.size() + 1);

		hashtage.setProperty("name", this.Name);
		hashtage.setProperty("PostsID", this.postsID);
		hashtage.setProperty("PostsNum", this.postsNum);
		
		
		if(datastore.put(hashtage).isComplete())
			return true;
		else return false;

	}
	
	public boolean getHashtage(String name) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		

		Query gaeQuery = new Query("Hashtages");
		
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("name").toString().equals(name)) {
				return true;
			}
			
		}
		return false;
	}
	
	
	public boolean updateHashtage(String name,long postid) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Hashtages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			System.out.println(-1);
			if (entity.getProperty("name").toString().equals(name)) {
			
				postsNum=Integer.valueOf(entity.getProperty("PostsNum").toString());
				postsNum++;
				
				postsID= (ArrayList<Long>) entity.getProperty("PostsID");
				
				postsID.add(postid);
				
				entity.setProperty("PostsID", this.postsID);
				entity.setProperty("PostsNum", this.postsNum);
				datastore.put(entity);
				return true;
			}
			}
		return false;
	}
	
	
	public static ArrayList<String> hashtageTrends() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Hashtages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		int counter=1;
		
		ArrayList<Hashtage> myList=new ArrayList<Hashtage>();
		ArrayList<String> mytrends=new ArrayList<String>();
		
		for (Entity entity : pq.asIterable()) {
			Hashtage currenthash=new Hashtage();
			Hashtage minHash=new Hashtage();
			minHash.postsNum=100000000;
			minHash.Name="no Hashtage";
			int index = 0;
			currenthash.Name=entity.getProperty("name").toString();
			currenthash.postsNum=Integer.valueOf(entity.getProperty("PostsNum").toString());
			
			
			if(counter<=10){
		    	
				
				myList.add(currenthash);
				
				counter++;
			}
			
			else{
				//minHash=myList.get(1);
				for(int i=0;i<myList.size();i++)
				System.out.println(myList.get(i).postsNum);
				
				for(int i=0;i< myList.size();i++)
				{
					if(myList.get(i).postsNum < minHash.postsNum){
						minHash.postsNum = myList.get(i).postsNum;
						index = myList.indexOf(myList.get(i));		
				}
					
				}
				
				System.out.println(myList.get(index).Name);
				System.out.println(currenthash.Name);
				
				if(currenthash.postsNum>minHash.postsNum){
				myList.get(index).Name=currenthash.Name;
				myList.get(index).postsNum=currenthash.postsNum;
				
				System.out.println(myList.get(index).Name);
				System.out.println(currenthash.Name);
				}
			}
		}
		for(int i=0;i<myList.size();i++){
			mytrends.add(myList.get(i).Name+" : "+myList.get(i).postsNum+"  posts");
		}
		
		for(int i=0;i<myList.size();i++){
			System.out.println(mytrends.get(i));
		}
		
		
		return mytrends;
	}
	
}
	
