package de.dkt.eservices.ecorefresolution;


public class TestConstants {
	
	public static final String pathToPackage = "rdftest/ecorefresolution-test-package.xml";
	
	static String inputNIF = 
			"@prefix dktnif: <http://dkt.dfki.de/ontologies/nif#> .\n" +
					"@prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" +
					"@prefix xsd:   <http://www.w3.org/2001/XMLSchema#> .\n" +
					"@prefix itsrdf: <http://www.w3.org/2005/11/its/rdf#> .\n" +
					"@prefix nif:   <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#> .\n" +
					"@prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#> .\n" +
					"\n" +
					"<http://dkt.dfki.de/documents/#char=0,298>\n" +
					"        a               nif:Context , nif:String , nif:RFC5147String ;\n" +
					"        nif:beginIndex  \"0\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:endIndex    \"298\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:isString    \"Pierre Vinken, 61 years old, will join the board as a nonexecutive director Nov. 29.\\r\\nMr. Vinken is chairman of Elsevier N.V., the Dutch publishing group.\\r\\nRudolph Agnew, 55 years old and former chairman of Consolidated Gold Fields PLC, was named a director of this British industrial conglomerate.\"^^xsd:string .\n" +
					"\n" +
					"<http://dkt.dfki.de/documents/#char=112,125>\n" +
					"        a                     nif:String , nif:RFC5147String ;\n" +
					"        nif:anchorOf          \"Elsevier N.V.\"^^xsd:string ;\n" +
					"        nif:beginIndex        \"112\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:endIndex          \"125\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,298> ;\n" +
					"        itsrdf:taClassRef     <http://dbpedia.org/ontology/Organization> .\n" +
					"\n" +
					"<http://dkt.dfki.de/documents/#char=0,13>\n" +
					"        a                     nif:String , nif:RFC5147String ;\n" +
					"        nif:anchorOf          \"Pierre Vinken\"^^xsd:string ;\n" +
					"        nif:beginIndex        \"0\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:endIndex          \"13\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,298> ;\n" +
					"        itsrdf:taClassRef     <http://dbpedia.org/ontology/Person> .\n" +
					"\n" +
					"<http://dkt.dfki.de/documents/#char=156,169>\n" +
					"        a                     nif:String , nif:RFC5147String ;\n" +
					"        nif:anchorOf          \"Rudolph Agnew\"^^xsd:string ;\n" +
					"        nif:beginIndex        \"156\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:endIndex          \"169\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,298> ;\n" +
					"        itsrdf:taClassRef     <http://dbpedia.org/ontology/Person> .\n" +
					"";

	static String expectedOutput1 = 
			"@prefix dktnif: <http://dkt.dfki.de/ontologies/nif#> .\n" +
				"@prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" +
				"@prefix owl:   <http://www.w3.org/2002/07/owl#> .\n" +
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
				"<http://dkt.dfki.de/documents/#char=156,169>\n" +
				"        a                     nif:RFC5147String , nif:String ;\n" +
				"        nif:anchorOf          \"Rudolph Agnew\"^^xsd:string ;\n" +
				"        nif:beginIndex        \"156\"^^xsd:nonNegativeInteger ;\n" +
				"        nif:endIndex          \"169\"^^xsd:nonNegativeInteger ;\n" +
				"        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,298> ;\n" +
				"        itsrdf:taClassRef     <http://dbpedia.org/ontology/Person> .\n" +
				"\n" +
				"<http://dkt.dfki.de/documents/#char=0,13>\n" +
				"        a                     nif:RFC5147String , nif:String ;\n" +
				"        nif:anchorOf          \"Pierre Vinken\"^^xsd:string ;\n" +
				"        nif:beginIndex        \"0\"^^xsd:nonNegativeInteger ;\n" +
				"        nif:endIndex          \"13\"^^xsd:nonNegativeInteger ;\n" +
				"        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,298> ;\n" +
				"        itsrdf:taClassRef     <http://dbpedia.org/ontology/Person> .\n" +
				"\n" +
				"<http://dkt.dfki.de/documents/#char=112,125>\n" +
				"        a                     nif:RFC5147String , nif:String ;\n" +
				"        nif:anchorOf          \"Elsevier N.V.\"^^xsd:string ;\n" +
				"        nif:beginIndex        \"112\"^^xsd:nonNegativeInteger ;\n" +
				"        nif:endIndex          \"125\"^^xsd:nonNegativeInteger ;\n" +
				"        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,298> ;\n" +
				"        itsrdf:taClassRef     <http://dbpedia.org/ontology/Organization> .\n" +
				"\n" +
				"<http://dkt.dfki.de/documents/#char=127,153>\n" +
				"        a                     nif:RFC5147String , nif:String ;\n" +
				"        nif:anchorOf          \"the Dutch publishing group\"^^xsd:string ;\n" +
				"        nif:beginIndex        \"127\"^^xsd:nonNegativeInteger ;\n" +
				"        nif:endIndex          \"153\"^^xsd:nonNegativeInteger ;\n" +
				"        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,298> ;\n" +
				"        owl:sameAs            \"http://dkt.dfki.de/documents/#char=112,125\"^^xsd:string .\n" +
				"";
	
	static String inputNIF2 = 
			"@prefix dktnif: <http://dkt.dfki.de/ontologies/nif#> .\n" +
					"@prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" +
					"@prefix xsd:   <http://www.w3.org/2001/XMLSchema#> .\n" +
					"@prefix itsrdf: <http://www.w3.org/2005/11/its/rdf#> .\n" +
					"@prefix nif:   <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#> .\n" +
					"@prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#> .\n" +
					"\n" +
					"<http://dkt.dfki.de/documents/#char=0,298>\n" +
					"        a               nif:Context , nif:String , nif:RFC5147String ;\n" +
					"        nif:beginIndex  \"0\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:endIndex    \"298\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:isString    \"Pierre Vinken, 61 years old, will join the board as a nonexecutive director Nov. 29.\\r\\nMr. Vinken is chairman of Elsevier N.V., the Dutch publishing group.\\r\\nRudolph Agnew, 55 years old and former chairman of Consolidated Gold Fields PLC, was named a director of this British industrial conglomerate.\"^^xsd:string .\n" +
					"\n" +
					"<http://dkt.dfki.de/documents/#char=0,13>\n" +
					"        a                     nif:String , nif:RFC5147String ;\n" +
					"        nif:anchorOf          \"Pierre Vinken\"^^xsd:string ;\n" +
					"        nif:beginIndex        \"0\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:endIndex          \"13\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,298> ;\n" +
					"        itsrdf:taClassRef     <http://dbpedia.org/ontology/Person> .\n" +
					"\n" +
					"<http://dkt.dfki.de/documents/#char=156,169>\n" +
					"        a                     nif:String , nif:RFC5147String ;\n" +
					"        nif:anchorOf          \"Rudolph Agnew\"^^xsd:string ;\n" +
					"        nif:beginIndex        \"156\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:endIndex          \"169\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:referenceContext  <http://dkt.dfki.de/documents/#char=0,298> ;\n" +
					"        itsrdf:taClassRef     <http://dbpedia.org/ontology/Person> .\n" +
					"";
	
	static String expectedResponse2 = 
			"@prefix dktnif: <http://dkt.dfki.de/ontologies/nif#> .\n" +
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
	
	static String expectedResponse3 =
			"@prefix dktnif: <http://dkt.dfki.de/ontologies/nif#> .\n" +
					"@prefix nif-ann: <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-annotation#> .\n" +
					"@prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" +
					"@prefix xsd:   <http://www.w3.org/2001/XMLSchema#> .\n" +
					"@prefix itsrdf: <http://www.w3.org/2005/11/its/rdf#> .\n" +
					"@prefix nif:   <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#> .\n" +
					"@prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#> .\n" +
					"\n" +
					"<http://dkt.dfki.de/documents/#char=0,30>\n" +
					"        a               nif:RFC5147String , nif:String , nif:Context ;\n" +
					"        nif:beginIndex  \"0\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:endIndex    \"30\"^^xsd:nonNegativeInteger ;\n" +
					"        nif:isString    \"this is just a sample sentence\"^^xsd:string .\n" +
					"";
}
