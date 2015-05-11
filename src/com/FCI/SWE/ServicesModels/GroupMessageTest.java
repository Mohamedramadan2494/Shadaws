package com.FCI.SWE.ServicesModels;

import junit.framework.Assert;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class GroupMessageTest {
	GroupMessage g=new GroupMessage();
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }


  @Test
  public void saveGroupMessage() {
	  Assert.assertEquals(true, g.saveGroupMessage());
    //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void showGroupMessage() {
    
	  //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void updateGroupMessage() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void updateGroupUsers() {
    throw new RuntimeException("Test not implemented");
  }
}
