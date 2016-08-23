package de.dkt.eservices.ecorefresolution.modules;

import edu.stanford.nlp.hcoref.CorefCoreAnnotations;
import edu.stanford.nlp.hcoref.data.CorefChain;
import edu.stanford.nlp.hcoref.data.Mention;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import eu.freme.common.conversion.rdf.RDFConstants.RDFSerialization;

import java.util.List;
import java.util.Properties;

import com.hp.hpl.jena.rdf.model.Model;

import de.dkt.common.niftools.NIFReader;

public class Corefinizer {
	 public static void main(String[] args) throws Exception {
		 
		 String nifString = "@prefix dktnif: <http://dkt.dfki.de/ontologies/nif#> .\n" +
			      "@prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" +
			      "@prefix xsd:   <http://www.w3.org/2001/XMLSchema#> .\n" +
			      "@prefix itsrdf: <http://www.w3.org/2005/11/its/rdf#> .\n" +
			      "@prefix nif:   <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#> .\n" +
			      "@prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#> .\n" +
			      "\n" +
			      "<http://dkt.dfki.de/documents/#char=0,298>\n" +
			      "        a               nif:RFC5147String , nif:String , nif:Context ;\n" +
			      "        nif:beginIndex  \"0\"^^xsd:nonNegativeInteger ;\n" +
			      "        nif:endIndex    \"298\"^^xsd:nonNegativeInteger ;\n" +
			      "        nif:isString    \"Pierre Vinken, 61 years old, will join the board as a nonexecutive director Nov. 29.\\r\\nMr. Vinken is chairman of Elsevier N.V., the Dutch publishing group.\\r\\nRudolph Agnew, 55 years old and former chairman of Consolidated Gold Fields PLC, was named a director of this British industrial conglomerate.\"^^xsd:string .\n" +
			      "\n" +
			      "<http://dkt.dfki.de/documents/#char=0,13>\n" +
			      "        a                     nif:RFC5147String , nif:String ;\n" +
			      "        nif:anchorOf          \"Pierre Vinken\"^^xsd:string ;\n" +
			      "        nif:beginIndex        \"0\"^^xsd:nonNegativeInteger ;\n" +
			      "        nif:endIndex          \"13\"^^xsd:nonNegativeInteger ;\n" +
			      "        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,298> ;\n" +
			      "        itsrdf:taClassRef     <http://dbpedia.org/ontology/Person> .\n" +
			      "\n" +
			      "<http://dkt.dfki.de/documents/#char=156,169>\n" +
			      "        a                     nif:RFC5147String , nif:String ;\n" +
			      "        nif:anchorOf          \"Rudolph Agnew\"^^xsd:string ;\n" +
			      "        nif:beginIndex        \"156\"^^xsd:nonNegativeInteger ;\n" +
			      "        nif:endIndex          \"169\"^^xsd:nonNegativeInteger ;\n" +
			      "        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,298> ;\n" +
			      "        itsrdf:taClassRef     <http://dbpedia.org/ontology/Person> .\n" +
			      "";
			    
			    
			  
			  Model nifModel;
			  try {
			   nifModel = NIFReader.extractModelFromFormatString(nifString, RDFSerialization.TURTLE);
			   resolveCoreferencesNIF(nifModel);
			  } catch (Exception e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  }

			  
			
			  
			  
		 /*Annotation document = new Annotation("When Harald was seven months old he cut his first tooth. Then his father "
				 +"said:"
				 +""
				 +"\"All the young of my herds, lambs and calves and colts, that have been "
				 +"born since this baby was born I this day give to him. I also give to him "
				 +"his thrall, Olaf. These are my tooth-gifts to my son.\""
				 +"The boy grew fast, for as soon as he could walk about he was out of "
				 +"doors most of the time. He ran in the woods and climbed the hills and "
				 +"waded in the creek. He was much with his tooth thrall, for the king had "
				 +"said to Olaf:\"Be ever at his call.\"Now this Olaf was full of stories, and Harald liked to hear them. "
				 +"\"Come out to Aegir's Rock, Olaf, and tell me stories,\" he said almost "
				 +"every day. ");
		 //Annotation document = new Annotation("Everything was in confusion in the Oblonskys' house. The wife had discovered that the husband was carrying on an intrigue with a French girl, who had been a governess in their family, and she had announced to her husband that she could not go on living in the same house with him. This position of affairs had now lasted three days, and not only the husband and wife themselves, but all the members of their family and household, were painfully conscious of it. Every person in the house felt that there was so sense in their living together, and that the stray people brought together by chance in any inn had more in common with one another than they, the members of the family and household of the Oblonskys. The wife did not leave her own room, the husband had not been at home for three days. The children ran wild all over the house; the English governess quarreled with the housekeeper, and wrote to a friend asking her to look out for a new situation for her; the man-cook had walked off the day before just at dinner time; the kitchen-maid, and the coachman had given warning.");
		 //Annotation document = new Annotation("Barack Obama was born in Hawaii.  He is the president.  Obama was elected in 2008.");   
		 Properties props = new Properties();
		    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,mention,dcoref");
		    props.setProperty("coref.doClustering", "false");
		    props.setProperty("coref.md.type", "hybrid");
		    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		    pipeline.annotate(document);
		    System.out.println("---");
		    System.out.println("coref chains");
		    for (CorefChain cc : document.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()) {
		      System.out.println("\t"+cc);
		    }
		    for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
		      System.out.println("---");
		      System.out.println("mentions");
		      for (Mention m : sentence.get(CorefCoreAnnotations.CorefMentionsAnnotation.class)) {
		        System.out.println("\t"+m);
		       }
		    }
		  */}

	private static void resolveCoreferencesNIF(Model nifModel) {
		
		 Annotation document = new Annotation(NIFReader.extractIsString(nifModel));   
		 Properties props = new Properties();
		    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,mention,dcoref");
		    //props.setProperty("coref.doClustering", "false");
		    //props.setProperty("coref.md.type", "hybrid");
		    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		    pipeline.annotate(document);
		    System.out.println("---");
		    System.out.println("coref chains");
		    for (CorefChain cc : document.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()) {
		      System.out.println("\t"+cc);
		    }
		    for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
		      System.out.println("---");
		      System.out.println("mentions");
		      for (Mention m : sentence.get(CorefCoreAnnotations.CorefMentionsAnnotation.class)) {
		        System.out.println("\t"+m);
		       }
		    }
		    
		    //find sentence index of a reference, find word index of a reference. The length of the sentences to find out where a word is
		    //on document level. CorefDestAnnotation
		
		  List<String[]> ents = NIFReader.extractEntityIndices(nifModel);
		   /*
		    * 0:ITSRDF.taClassRef
		    * 1:anchorOf
		    * 2:taIdentRef
		    * 3:beginIndex
		    * 4:endIndex
		    */
		
	}

}
