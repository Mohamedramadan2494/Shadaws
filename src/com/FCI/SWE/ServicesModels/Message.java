package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Message {

String from,to,message;


public Message(String f, String t, String s) {
	this.from=f;;
	this.to=t;
	this.message=s;
}

public String getFrom() {
	return from;
}

public String getTo() {
	return to;
}

public String getStatus() {
	return message;
}

public Boolean savemessage() {
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	Query gaeQuery = new Query("Messages");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

	Entity employee = new Entity("Messages", list.size() + 1);

	employee.setProperty("SentFrom", this.from);
	employee.setProperty("SentTo", this.to);
	employee.setProperty("Message", this.message);
	datastore.put(employee);

	return true;

}
}
