package com.FCI.SWE.ServicesModels;

public class SRNotification implements Notifications {
	
	public String to;
	public String from;
	public SRNotification(String to , String from){
		this.to=to;
		this.from=from;
	}

	@Override
	public void Notify() {
	String text="you have friend request";
	NotificationEntity no = new NotificationEntity(to,from,text);
	no.savenotifiction();
		
	}

}
