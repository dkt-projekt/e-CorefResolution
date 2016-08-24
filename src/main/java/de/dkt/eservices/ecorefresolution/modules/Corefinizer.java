package de.dkt.eservices.ecorefresolution.modules;

import edu.stanford.nlp.hcoref.CorefCoreAnnotations;
import edu.stanford.nlp.hcoref.data.CorefChain;
import edu.stanford.nlp.hcoref.data.CorefChain.CorefMention;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.IntPair;
import eu.freme.common.conversion.rdf.RDFConstants.RDFSerialization;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.hp.hpl.jena.rdf.model.Model;

import de.dkt.common.niftools.NIFReader;
import de.dkt.common.niftools.NIFWriter;


public class Corefinizer {
	 public static void main(String[] args) throws Exception {

		String nifString = "@prefix dktnif: <http://dkt.dfki.de/ontologies/nif#> .\n"
				+ "@prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n"
				+ "@prefix xsd:   <http://www.w3.org/2001/XMLSchema#> .\n"
				+ "@prefix itsrdf: <http://www.w3.org/2005/11/its/rdf#> .\n"
				+ "@prefix nif:   <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#> .\n"
				+ "@prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#> .\n" + "\n"
				+ "<http://dkt.dfki.de/documents/#char=0,298>\n"
				+ "        a               nif:RFC5147String , nif:String , nif:Context ;\n"
				+ "        nif:beginIndex  \"0\"^^xsd:nonNegativeInteger ;\n"
				+ "        nif:endIndex    \"298\"^^xsd:nonNegativeInteger ;\n"
				+ "        nif:isString    \"Pierre Vinken, 61 years old, will join the board as a nonexecutive director Nov. 29.\\r\\nMr. Vinken is chairman of Elsevier N.V., the Dutch publishing group.\\r\\nRudolph Agnew, 55 years old and former chairman of Consolidated Gold Fields PLC, was named a director of this British industrial conglomerate.\"^^xsd:string .\n"
				+ "\n" + "<http://dkt.dfki.de/documents/#char=0,13>\n"
				+ "        a                     nif:RFC5147String , nif:String ;\n"
				+ "        nif:anchorOf          \"Pierre Vinken\"^^xsd:string ;\n"
				+ "        nif:beginIndex        \"0\"^^xsd:nonNegativeInteger ;\n"
				+ "        nif:endIndex          \"13\"^^xsd:nonNegativeInteger ;\n"
				+ "        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,298> ;\n"
				+ "        itsrdf:taClassRef     <http://dbpedia.org/ontology/Person> .\n" + "\n"
				+ "\n" + "<http://dkt.dfki.de/documents/#char=112,125>\n"
				+ "        a                     nif:RFC5147String , nif:String ;\n"
				+ "        nif:anchorOf          \"Elsevier N.V.\"^^xsd:string ;\n"
				+ "        nif:beginIndex        \"112\"^^xsd:nonNegativeInteger ;\n"
				+ "        nif:endIndex          \"125\"^^xsd:nonNegativeInteger ;\n"
				+ "        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,298> ;\n"
				+ "        itsrdf:taClassRef     <http://dbpedia.org/ontology/Organization> .\n" + "\n"
				+ "<http://dkt.dfki.de/documents/#char=156,169>\n"
				+ "        a                     nif:RFC5147String , nif:String ;\n"
				+ "        nif:anchorOf          \"Rudolph Agnew\"^^xsd:string ;\n"
				+ "        nif:beginIndex        \"156\"^^xsd:nonNegativeInteger ;\n"
				+ "        nif:endIndex          \"169\"^^xsd:nonNegativeInteger ;\n"
				+ "        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,298> ;\n"
				+ "        itsrdf:taClassRef     <http://dbpedia.org/ontology/Person> .\n" + "";
			    
		try {
			Model nifModel = NIFReader.extractModelFromFormatString(nifString, RDFSerialization.TURTLE);
			System.out.println("NIFMODEL before:\n" + NIFReader.model2String(nifModel, RDFSerialization.TURTLE));
			nifModel = resolveCoreferencesNIF(nifModel);
			System.out.println("NIFMODEL after:\n" + NIFReader.model2String(nifModel, RDFSerialization.TURTLE));
			
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
	


	public static Model resolveCoreferencesNIF(Model nifModel) {
		
		 Annotation document = new Annotation(NIFReader.extractIsString(nifModel));   
		 Properties props = new Properties();
		    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,mention,dcoref");
		    //props.setProperty("coref.doClustering", "false");
		    //props.setProperty("coref.md.type", "hybrid");
		    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		    pipeline.annotate(document);

		    List<String[]> ents = NIFReader.extractEntityIndices(nifModel);
		    if (ents == null){
		    	return nifModel; // in this case we do not want to do anything, just return the current model
		    }
			   /*
			    * 0:ITSRDF.taClassRef
			    * 1:anchorOf
			    * 2:taIdentRef
			    * 3:beginIndex
			    * 4:endIndex
			    */
		    // converting this to hashmap structure with indices as key, so we don't have to loop through it everytime for every coreference mention, but can check if it is in the hash right away
		    HashMap<String, String> entIndexMap = new HashMap<String, String>();
		    for (String[] sa : ents){
		    	entIndexMap.put(sa[3] + "_" + sa[4], sa[1]);
		    }
		    
		    for (CorefChain cc : document.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()){
		    	for (IntPair key : cc.getMentionMap().keySet()){
		    		Set<CorefMention> set = cc.getMentionMap().get(key);
		    		if (set.size() > 1){ // we only want chains with more than one element, otherwise there is nothing to refer to
		    			String existingIndex = null;
		    			for (CorefMention cm : set){ // first pass is to check if there is a recognized entity in this coref chain
		    				List<CoreLabel> tokens = document.get(CoreAnnotations.SentencesAnnotation.class).get(cm.sentNum-1).get(CoreAnnotations.TokensAnnotation.class);
		    				int startIndex = tokens.get(cm.startIndex-1).beginPosition();
		    				int endIndex = startIndex + cm.mentionSpan.length();
		    				if (entIndexMap.containsKey(startIndex + "_" + endIndex)){
		    					existingIndex = startIndex + "_" + endIndex;
		    				}
		    			}
		    			if (existingIndex != null){ // second pass; if there was a matching entity, link them
		    				for (CorefMention cm : set){
		    					List<CoreLabel> tokens = document.get(CoreAnnotations.SentencesAnnotation.class).get(cm.sentNum-1).get(CoreAnnotations.TokensAnnotation.class);
			    				int startIndex = tokens.get(cm.startIndex-1).beginPosition();
			    				int endIndex = startIndex + cm.mentionSpan.length();
			    				if (entIndexMap.containsKey(startIndex + "_" + endIndex)){
			    					// skip, this one is in the nif already
			    				}
			    				else{
			    					String existingEntityURI = NIFReader.extractDocumentURI(nifModel) + "#char=" + existingIndex.split("_")[0] + "," + existingIndex.split("_")[1]; // TODO: fix this. This will probably outdated again once we move to NIF 2.1 and is quite a hacky way of retrieving an entity URI. Should be a neat way of getting that, so use that!
			    					NIFWriter.addCoreferenceAnnotation(nifModel, startIndex, endIndex, cm.mentionSpan, existingEntityURI);
			    				}
		    				}
		    			}
		    		}
		    		
		    	}
		    	
		    }
		    
		return nifModel;
	}

}
