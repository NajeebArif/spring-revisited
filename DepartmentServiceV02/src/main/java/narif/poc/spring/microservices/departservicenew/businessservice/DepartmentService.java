package narif.poc.spring.microservices.departservicenew.businessservice;

import java.util.List;

import narif.poc.spring.microservices.departservicenew.entities.Department;

public interface DepartmentService {
	
	public List<Department> findAllDepartments();
	
	public Department findDepartmentForDepartmentId(Long departmentId);
}
