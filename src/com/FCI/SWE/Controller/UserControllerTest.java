package com.FCI.SWE.Controller;

import junit.framework.Assert;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class UserControllerTest {
	UserController u=new  UserController();
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }


  @Test
  public void Like() {
	  Assert.assertEquals("Ok post liked",u.Like("mmnnn"));
    //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void hashtageTrend() {
     Assert.assertNotNull(u.hashtageTrend());
	  //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void mypost() {
	  Assert.assertNotNull(u.mypost());
    //throw new RuntimeException("Test not implemented");
  }



  @Test
  public void notifications() {
	  Assert.assertEquals("Ok operation Done",u.notifications());
    //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void post() {
	  Assert.assertEquals("Ok post published",u.post("elhamdlellah","public","happy"));
    //throw new RuntimeException("Test not implemented");
  }


  @Test
  public void postFriendStringStringString() {
	  Assert.assertEquals("Ok post published",u.postFriend("salama","welcomeeee","private"));
    //throw new RuntimeException("Test not implemented");
  }



  @Test
  public void rejectString() {
	  Assert.assertEquals("Ok operation Done",u.reject("ramadan"));
	  throw new RuntimeException("Test not implemented");
  }


  @Test
  public void requestString() {
	  Assert.assertEquals("Ok operation Done",u.request("Mostafa"));
    //throw new RuntimeException("Test not implemented");
  }


  @Test
  public void responseString() {
	  Assert.assertEquals("sented sucessfully", u.response("Mai"));
    //throw new RuntimeException("Test not implemented");
  }

///////////////////////////////////////////////
  @Test
  public void searchString() {
	  Assert.assertNotNull(u.search());
    //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void sendmessageStringString() {
	  Assert.assertEquals("Ok message sent",u.sendmessage("salama","welcome"));
    //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void share() {
      Assert.assertEquals("Ok post shared",u.share("mmnnn"));
	  //throw new RuntimeException("Test not implemented");
  }

 
}
