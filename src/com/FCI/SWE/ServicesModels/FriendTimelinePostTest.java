package com.FCI.SWE.ServicesModels;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.FCI.SWE.ServicesModels.FriendTimelinePost;

public class FriendTimelinePostTest {
	FriendTimelinePost p=new FriendTimelinePost("ramadan", "mohamed", 
			"hello", "private");
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }


  @Test
  public void savePost() {
	  Assert.assertEquals(true, p.savePost());
    throw new RuntimeException("Test not implemented");
  }
}
