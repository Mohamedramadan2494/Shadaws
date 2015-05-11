package command;

import java.util.ArrayList;

public class NotifyFriend extends Command {

	private Reciever r;
	 public NotifyFriend(Reciever rr){
		 
		 r=rr;
	 }
	
	@Override
	public ArrayList<String> excute() {
		
		return r.NotifyFriend();
		// TODO Auto-generated method stub
		
	}

}
