package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import static org.junit.Assert.assertEquals;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class PageEntityTest {
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
    ArrayList a=new ArrayList();
	PageEntity p = new PageEntity("ee","ee","ee",0,0,"",a);

	@Test
	public void check() {
		Assert.assertEquals(false, p.check("salama", "CR77"));
		// throw new RuntimeException("Test not implemented");
	}

	@Test
	public void likepage() {
		Assert.assertEquals(true, p.likepage("CR77"));
		// throw new RuntimeException("Test not implemented");
	}

	@Test
	public void savePage() {

		Assert.assertEquals(true, p.savePage(p));
	}
}
