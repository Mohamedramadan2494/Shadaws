package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;

public abstract class Posts {
	String owner,content,privacy;
	ArrayList<String> UsersShare ;
	int nLike,seen;
	UserEntity u;
    static public Long postid;
	
    public abstract void setContent(String content);
    public abstract void setowner(String owner);
    public abstract void setLikes(int nLike);
    public abstract String getsetContent();
    public abstract String  getowner();
    public abstract int   getLikes();
	public abstract boolean savePost();
	
	public long getPostID(String content) {
		// TODO Auto-generated method stub
		return 0;
	}
}

