package org.bahmni.module.teleconsultation.api;

import org.openmrs.api.OpenmrsService;


public interface TeleconsultationService extends OpenmrsService {
	
	public String generateTeleconsultationLink(String uuid);
}
