package de.dkt.eservices.ecorefresolution;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import eu.freme.common.conversion.rdf.JenaRDFConversionService;
import eu.freme.common.conversion.rdf.RDFConversionService;

/**
 * @author Julian Moreno Schneider julian.moreno_schneider@dfki.de
 *
 * The whole documentation about openNLP examples can be found in https://opennlp.apache.org/documentation/1.6.0/manual/opennlp.html
 *
 */

@Component
public class ECorefResolutionService {
    
	Logger logger = Logger.getLogger(ECorefResolutionService.class);

	RDFConversionService rdfConversionService = new JenaRDFConversionService();

	
	public ECorefResolutionService() {
	
	}
	
	
	public static void main(String[] args) throws Exception {
		
	}
	
    
}
