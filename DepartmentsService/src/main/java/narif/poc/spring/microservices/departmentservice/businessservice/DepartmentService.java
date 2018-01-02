package narif.poc.spring.microservices.departmentservice.businessservice;

import java.util.List;

import narif.poc.spring.microservices.departmentservice.entities.Department;

public interface DepartmentService {
	
	public List<Department> findAllDepartments();
	
	public Department findDepartmentForDepartmentId(Long departmentId);
}
