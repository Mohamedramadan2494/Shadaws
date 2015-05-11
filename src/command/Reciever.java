package command;
import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Reciever {
String name;
	

    public Reciever(String nam){
    	name=nam;
    }
	public void SetName(String nam){
		
		name=nam;
	}
	
	public ArrayList<String> NotifyFriend(){
		
		ArrayList<String>r = new ArrayList<String>();
		String rr;
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("RequestTo").toString().equals(name)&&
			   entity.getProperty("Status").toString().equals("waiting")) {
				
				rr=entity.getProperty("RequestFrom").toString();
				r.add(rr);
				
			}
			
		}

		return r;
		
	
	
	}
	
}
