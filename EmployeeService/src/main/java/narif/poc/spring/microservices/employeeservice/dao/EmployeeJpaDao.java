package narif.poc.spring.microservices.employeeservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import narif.poc.spring.microservices.employeeservice.entities.Employee;

@Repository
public interface EmployeeJpaDao extends JpaRepository<Employee, Long> {
	
}
