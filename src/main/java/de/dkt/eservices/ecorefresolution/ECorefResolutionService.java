package de.dkt.eservices.ecorefresolution;

import java.io.IOException;
import org.apache.jena.riot.RiotException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.hp.hpl.jena.rdf.model.Model;

import de.dkt.common.niftools.DKTNIF;
import de.dkt.common.niftools.NIFReader;
import de.dkt.common.niftools.NIFWriter;
import de.dkt.common.tools.ParameterChecker;
import de.dkt.eservices.ecorefresolution.modules.Corefinizer;
import eu.freme.common.conversion.rdf.JenaRDFConversionService;
import eu.freme.common.conversion.rdf.RDFConstants;
import eu.freme.common.conversion.rdf.RDFConversionService;
import eu.freme.common.exception.BadRequestException;
import eu.freme.common.exception.ExternalServiceFailedException;

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
	
	
	public Model resolveCoreferences(String textToProcess, String languageParam, RDFConstants.RDFSerialization inFormat)
					throws ExternalServiceFailedException, BadRequestException, IOException, Exception {
		ParameterChecker.checkNotNullOrEmpty(languageParam, "language", logger);
		
		try {
			Model nifModel = null;
			if (inFormat.equals(RDFConstants.RDFSerialization.PLAINTEXT)) {
				nifModel = NIFWriter.initializeOutputModel();
				NIFWriter.addInitialString(nifModel, textToProcess, DKTNIF.getDefaultPrefix());
			} else {
				try {
					nifModel = NIFReader.extractModelFromFormatString(textToProcess, inFormat);
				} catch (RiotException e) {
					throw new BadRequestException("Check the input format [" + inFormat + "]!!");
				}
			}
		
			if (languageParam.equals("en")) {
				//all is well
				// add clause for de here when implemented
			} else {
				logger.error("Unsupported language:" + languageParam);
				throw new BadRequestException("Unsupported language:" + languageParam);
			}
			
			nifModel = Corefinizer.resolveCoreferencesNIF(nifModel);
			return nifModel;
			
		} catch (BadRequestException e) {
			logger.error(e.getMessage());
			throw e;
		} catch (ExternalServiceFailedException e2) {
			logger.error(e2.getMessage());
			throw e2;
		}

	}
	
	
	public static void main(String[] args) throws Exception {
		
	}
	
    
}
