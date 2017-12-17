package org.itstep.security.domain.repositories;

import org.itstep.security.domain.entities.AutoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoUserRepository extends JpaRepository<AutoUser, Long> {

}
