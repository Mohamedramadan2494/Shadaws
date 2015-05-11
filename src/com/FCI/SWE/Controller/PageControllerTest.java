package com.FCI.SWE.Controller;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.FCI.SWE.ServicesModels.PageEntity;
import com.FCI.SWE.ServicesModels.PageTimelinePost;

public class PageControllerTest {
	ArrayList a=new ArrayList();
	PageEntity p=new PageEntity("","","",0,0,"",a);
	PageController g=new PageController();
	PageTimelinePost t=new PageTimelinePost("", "", "", "");
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }


  @Test
  public void createPage() {
	  Assert.assertEquals("Page created Successfully", g.createPage("ee", "ee", "rr"));
	 // Assert.assertEquals("Page created Successfully",g.createPage("ee", "ee", "rr"));
    //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void likepage() {
	  Assert.assertEquals("page liked Successfully",g.likepage("CR77"));
    //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void postPage() {
	  Assert.assertEquals("Ok post published",g.postPage("CR77", "hello", "public"));
        //throw new RuntimeException("Test not implemented");
  }
}
