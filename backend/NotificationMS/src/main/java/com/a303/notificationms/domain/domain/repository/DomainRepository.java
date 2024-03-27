package com.a303.notificationms.domain.domain.repository;

import com.a303.notificationms.domain.domain.Domain;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<Domain, Integer> {
	Domain findByName(String domainName);
}
