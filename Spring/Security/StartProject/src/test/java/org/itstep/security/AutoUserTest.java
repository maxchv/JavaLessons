package org.itstep.security;

import java.time.LocalDate;
import java.util.ArrayList;

import org.itstep.security.domain.entities.AutoUser;
import org.itstep.security.domain.repositories.AutoUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.itstep.security.domain.entities.Appointment;
import org.itstep.security.domain.entities.Automobile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application-context.xml")
public class AutoUserTest {

	@Autowired
	private AutoUserRepository autoUserRepository;

	@Test
	public void insertUser() {
		Automobile automobile = new Automobile();
		automobile.setMake("Ford");
		automobile.setModel("F150");
		automobile.setYear(new Short("2015"));

		AutoUser autoUser = new AutoUser();
		autoUser.setFirstName("Kevin");
		autoUser.setLastName("Bowersox");
		autoUser.setUsername("kmb385");
		autoUser.setPassword("test");
		
		Appointment appointment = new Appointment();
		appointment.setAppointmentDt(LocalDate.now());
		appointment.setUser(autoUser);
		appointment.setAutomobile(automobile);
		appointment.setServices(new ArrayList<String>(){{
			add("Tire Change");
			add("Oil Change");
		}});

		autoUser.getAppointments().add(appointment);

		autoUserRepository.save(autoUser);
	}


}
