package com.FCI.SWE.ServicesModels;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */
public class UserEntity {
	public String name;
	public String email;
	private String password;
	private long id;

	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */
	public UserEntity(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public UserEntity() {
		this.name = null;
		this.email = null;
		this.password = null;
	}
	
	private void setId(long id){
		this.id = id;
	}
	
	public void setname(String m){
		this.name = m;
	}
	
	public long getId(){
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}

	
	/**
	 * 
	 * This static method will form UserEntity class using user name and
	 * password This method will serach for user in datastore
	 * 
	 * @param name
	 *            user name
	 * @param pass
	 *            user password
	 * @return Constructed user entity
	 */

	public static UserEntity getUser(String name, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("name").toString().equals(name)
					&& entity.getProperty("password").toString().equals(pass)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				returnedUser.setId(entity.getKey().getId());
				return returnedUser;
			}
		}

		return null;
	}
	public static boolean getUser(String name) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("name").toString().equals(name)) {
				
				return true;
			}
			
		}

		return false;
	}
	/**
	 * This method will be used to save user object in datastore
	 * 
	 * @return boolean if user is saved correctly or not
	 */
	public boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("users", list.size() + 1);

		employee.setProperty("name", this.name);
		employee.setProperty("email", this.email);
		employee.setProperty("password", this.password);
		datastore.put(employee);

		return true;

	}
	public static Vector<UserEntity> searchuser(String name){
		DatastoreService datastore=DatastoreServiceFactory.getDatastoreService();
		Query q1=new Query("users");
		PreparedQuery prepare=datastore.prepare(q1);
		Vector<UserEntity> users=new Vector<UserEntity>();
		
		for(Entity entity : prepare.asIterable()){
			String user=entity.getProperty("name").toString();
			if(user==name){
				UserEntity one=new UserEntity(entity.getProperty("name").toString(),entity.getProperty("password").toString(),entity.getProperty("email").toString());
				users.add(one);
			}
			
		}
		return users;
		
	}
	public static void addnotification(Notifications no){
		no.Notify();
	}
	/*public static boolean getusername(String name){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Filter filter1=new Filter("name",Filterpredicate.Equal(),name);
		package com.FCI.SWE.ServicesModels;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserEntityTest {

  
 
	}*/
	
	
	
}
