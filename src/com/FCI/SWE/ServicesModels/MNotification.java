package com.FCI.SWE.ServicesModels;

public class MNotification implements Notifications  {
	public String to;
	public String from;
	public MNotification(String to , String from){
		this.to=to;
		this.from=from;
	}

	@Override
	public void Notify() {
	String text="you have a message";
	NotificationEntity no = new NotificationEntity(to,from,text);
	no.savenotifiction();
		
	}

}
