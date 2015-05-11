package command;

import java.util.ArrayList;

public class Inv {
	Command c;
	

	public void SetNotifyFriend(Command m){
		c=m;
	}
	

	public ArrayList<String> handle(){
		
		return c.excute();
	}
	
	

}
