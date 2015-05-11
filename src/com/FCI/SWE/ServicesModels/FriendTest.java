package com.FCI.SWE.ServicesModels;

import junit.framework.Assert;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class FriendTest {
	Friend f=new Friend("Ahmed", "Ali", "accept");
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }


  @Test
  public void checkFriend() {
	  Assert.assertEquals(true, f.checkFriend("mohamed", "ramadan"));
    //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void request() {
	  Assert.assertEquals(true, f.request("mohamed", "ramadan", "ac"));
    //throw new RuntimeException("Test not implemented");
  }

  
@Test
  public void saveFriend() {
	  Assert.assertEquals(true, f.saveFriend());
	  //Assert.assertEquals(true, f.saveFriend());
    //throw new RuntimeException("Test not implemented");
  }
}
