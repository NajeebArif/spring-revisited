package narif.poc.spring.microservices.departmentservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import narif.poc.spring.microservices.departmentservice.entities.Department;

@Repository
public interface DepartmentsJpaDao extends JpaRepository<Department, Long> {

}
