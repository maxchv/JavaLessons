package org.itstep.security.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.itstep.security.domain.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
