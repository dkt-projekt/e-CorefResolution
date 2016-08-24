package de.dkt.eservices.ecorefresolution;


import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;

import de.dkt.eservices.ecorefresolution.TestConstants;
import eu.freme.bservices.testhelper.TestHelper;
import eu.freme.bservices.testhelper.ValidationHelper;
import eu.freme.bservices.testhelper.api.IntegrationTestSetup;

/**
 * @author 
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ECorefResolutionTest {

	TestHelper testHelper;
	ValidationHelper validationHelper;
	String indexPath = "";
	
	@Before
	public void setup() {
		ApplicationContext context = IntegrationTestSetup
				.getContext(TestConstants.pathToPackage);
		testHelper = context.getBean(TestHelper.class);
		validationHelper = context.getBean(ValidationHelper.class);
		
		
	}
	
	private HttpRequestWithBody resolveCorefRequest() {
		String url = testHelper.getAPIBaseUrl() + "/e-nlp/CoreferenceResolution";
		return Unirest.post(url);
	}
	
	
	
	
	@Test
	public void basicResolveCorefsTest() throws UnirestException, IOException,Exception {
		
		HttpResponse<String> response = resolveCorefRequest()
				.queryString("informat", "turtle")
				.queryString("outformat", "turtle")
				.queryString("language", "en")
				.queryString("input", TestConstants.inputNIF)
				.asString();
		assertTrue(response.getStatus() == 200);
		assertTrue(response.getBody().length() > 0);
		Assert.assertEquals(TestConstants.expectedOutput1, response.getBody());
		
	}
	
	@Test
	public void basicResolveCorefsTestWhereNothingIsAdded() throws UnirestException, IOException,Exception {
		
		HttpResponse<String> response = resolveCorefRequest()
				.queryString("informat", "turtle")
				.queryString("outformat", "turtle")
				.queryString("language", "en")
				.queryString("input", TestConstants.inputNIF2)
				.asString();
		assertTrue(response.getStatus() == 200);
		assertTrue(response.getBody().length() > 0);
		Assert.assertEquals(TestConstants.expectedResponse2, response.getBody());
		
	}
	
	
	@Test
	public void ResolveCorefsPlainTextIn() throws UnirestException, IOException,Exception { // in this scenario nothing happens, because there are no entities in the nif to resolve stuff to
		
		HttpResponse<String> response = resolveCorefRequest()
				.queryString("informat", "text")
				.queryString("outformat", "turtle")
				.queryString("language", "en")
				.queryString("input", "this is just a sample sentence")
				.asString();
		assertTrue(response.getStatus() == 200);
		assertTrue(response.getBody().length() > 0);
		Assert.assertEquals(TestConstants.expectedResponse3, response.getBody());
		
	}
	
	
}
