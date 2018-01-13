package narif.poc.spring.microservices.departservicenew.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import narif.poc.spring.microservices.departservicenew.entities.Department;

@Repository
public interface DepartmentsJpaDao extends JpaRepository<Department, Long> {

}
