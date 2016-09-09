package de.dkt.eservices.ecorefresolution;


import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;

import com.hp.hpl.jena.rdf.model.Model;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;

import de.dkt.common.niftools.DKTNIF;
import de.dkt.common.niftools.NIFReader;
import de.dkt.common.niftools.NIFWriter;
import de.dkt.eservices.ecorefresolution.TestConstants;
import de.dkt.eservices.ecorefresolution.modules.Corefinizer;
import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations;
import edu.stanford.nlp.dcoref.Mention;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import eu.freme.bservices.testhelper.TestHelper;
import eu.freme.bservices.testhelper.ValidationHelper;
import eu.freme.bservices.testhelper.api.IntegrationTestSetup;
import eu.freme.common.conversion.rdf.RDFConstants.RDFSerialization;

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
	static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
	
	
//	@Test
//	public void loopTroughCorpus() throws UnirestException, IOException,Exception {
//		
//		PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
//		System.setOut(out);
//		String docFolder = "C:\\Users\\Sabine\\Desktop\\NIFs";
//		File df = new File(docFolder);
//		for (File f : df.listFiles()){
//		
//		System.out.println("Trying to read file:" + f.getAbsolutePath());
//		String fileContent = readFile(f.getAbsolutePath(), StandardCharsets.UTF_8);
//		Model nifModel = NIFReader.extractModelFromFormatString(fileContent, RDFSerialization.TURTLE);
//		Corefinizer.resolveCoreferencesNIF(nifModel);
//		
//		
//		}
		
		/*@Test
		public void testChunkingOfText() throws UnirestException, IOException,Exception {
			
			
			String doc = "C:\\Users\\Sabine\\Desktop\\NIFs\\24811-0.nif";
			File f = new File(doc);
			String fileContent = readFile(f.getAbsolutePath(), StandardCharsets.UTF_8);
			Model nifModel = NIFReader.extractModelFromFormatString(fileContent, RDFSerialization.TURTLE);
			
			
			
			System.out.println("Trying to read file:" + f.getAbsolutePath());
		
			
			String nifString = NIFReader.extractIsString(nifModel);
			List<String> strings = new ArrayList<String>();
			 int index = 0;
			 int j = 0;
			 while (index < nifString.length()) {
				 String a = nifString.substring(index, Math.min(index + 20000,nifString.length()));
			     strings.add(a);
			     index += 20000;
			     j += 1;
			     System.out.println("counting loop: "+j);
			     System.out.println("first word is: "+ a.substring(0, a.indexOf(' ')));
			 }
			 
			 //PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
			 //System.setOut(out);
			 
			 Properties props = new Properties();
			 props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,mention,dcoref");
			    
			 props.setProperty("dcoref.maxdist", "3");
			 props.setProperty("parse.maxlen","70");
			 
			 int k = 0;
			 for (String s : strings){
			 Annotation document = new Annotation(s);   
			
			    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
			    pipeline.annotate(document);
			    k += 1;
			    System.out.println("counting loop: "+k);
			    
			 }*/
			 
			 /*@Test
				public void forgetChunking() throws UnirestException, IOException,Exception {
					
					
					String doc = "C:\\Users\\Sabine\\Desktop\\NIFs\\24811-0.nif";
					File f = new File(doc);
					String fileContent = readFile(f.getAbsolutePath(), StandardCharsets.UTF_8);
					Model nifModel = NIFReader.extractModelFromFormatString(fileContent, RDFSerialization.TURTLE);
					
					
					
					System.out.println("Trying to read file:" + f.getAbsolutePath());
				
					
					String nifString = NIFReader.extractIsString(nifModel);
				
					 
					 Properties props = new Properties();
					 props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,mention,dcoref");
					    
					 props.setProperty("dcoref.maxdist", "3");
					 props.setProperty("parse.maxlen","70");
					 
					 
					
					 Annotation document = new Annotation(nifString);   
					
					    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
					    pipeline.annotate(document);
					*/
					    
					 
			
			
			
		
	
		
		//}
	
	
}
