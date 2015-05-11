package com.FCI.SWE.ServicesModels;

import junit.framework.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class UserEntityTest {
    UserEntity u=new UserEntity("Magid","ma123","ma");
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	@BeforeClass
	public void setUp() {
		helper.setUp();
	}

	@AfterClass
	public void tearDown() {
		helper.tearDown();
	}
  @Test
  public void addnotification() {
    
	  //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getUserStringString() {
	  UserEntity e=new UserEntity("salama","salama123","sa");
	  Assert.assertEquals(e, u.getUser("salama", "sa"));
	  
    //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getUserString() {
	  Assert.assertEquals(true,u.getUser("salama"));
    //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void saveUser() {
     Assert.assertEquals(true, u.saveUser());
	  //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void searchuser() {
	  //throw new RuntimeException("Test not implemented");
  }
}
