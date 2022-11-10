package org.bahmni.module.teleconsultation.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.bahmni.module.teleconsultation.api.impl.TeleconsultationServiceImpl;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Context.class })
public class TeleconsultationServiceTest {
	
	@Mock
	private AdministrationService administrationService;
	
	@InjectMocks
	TeleconsultationServiceImpl teleconsultationService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		PowerMockito.mockStatic(Context.class);
		when(administrationService.getGlobalProperty("bahmni.appointment.teleConsultation.serverUrlPattern")).thenReturn(
		    "https://test.server/{0}");
		when(Context.getAdministrationService()).thenReturn(administrationService);
	}
	
	@Test
	public void shouldGenerateTCLinkForAppointment() {
		UUID uuid = UUID.randomUUID();
		String link = teleconsultationService.generateTeleconsultationLink(uuid.toString());
		assertEquals("https://test.server/" + uuid, link);
	}
}
