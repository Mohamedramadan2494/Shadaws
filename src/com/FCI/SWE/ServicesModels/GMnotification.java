package com.FCI.SWE.ServicesModels;

public class GMnotification implements Notifications{
		public String to;
		public String from;
		public GMnotification(String to , String from){
			this.to=to;
			this.from=from;
		}

		@Override
		public void Notify() {
		String text="you have a message from group";
		NotificationEntity no = new NotificationEntity(to,from,text);
		no.savenotifiction();
		}

	}


