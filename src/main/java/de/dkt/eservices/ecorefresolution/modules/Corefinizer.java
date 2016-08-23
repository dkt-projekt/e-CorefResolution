package de.dkt.eservices.ecorefresolution.modules;

import edu.stanford.nlp.hcoref.CorefCoreAnnotations;
import edu.stanford.nlp.hcoref.data.CorefChain;
import edu.stanford.nlp.hcoref.data.Mention;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.Properties;

public class Corefinizer {
	 public static void main(String[] args) throws Exception {

		 Annotation document = new Annotation("When Harald was seven months old he cut his first tooth. Then his father "
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
		  }

}
