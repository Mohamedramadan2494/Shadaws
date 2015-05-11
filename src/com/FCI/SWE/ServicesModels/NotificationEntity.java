package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class NotificationEntity {
	
	private String to;
	private String from;
	private String text;
	NotificationEntity(String to,String from,String text){
		this.to=to;
		this.from=from;
		this.text=text;
		
	}
	
	public boolean savenotifiction(){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Notifivationstore");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity Notifivations = new Entity("Notifivationstore", list.size() + 1);

		Notifivations .setProperty("owner", this.to);
		Notifivations .setProperty("from", this.from);
		Notifivations .setProperty("notfication", this.text);
		datastore.put(Notifivations );

		return true;		
	}

}
