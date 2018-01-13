package narif.poc.spring.microservices.specialroutesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import narif.poc.spring.microservices.specialroutesservice.model.AbTesting;

@Repository
public interface AbTestingJpaRepo extends JpaRepository<AbTesting, String> {

}
