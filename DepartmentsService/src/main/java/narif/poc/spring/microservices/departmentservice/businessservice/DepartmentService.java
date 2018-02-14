package narif.poc.spring.microservices.departmentservice.businessservice;

import java.util.List;

import narif.poc.spring.microservices.departmentservice.entities.Department;

public interface DepartmentService {
	
	public List<Department> findAllDepartments();
	
	public Department findDepartmentForDepartmentId(Long departmentId);
	
	public void deleteDepartment(Department dept);
	
	public Department createDepartment(Department dept);
	
	public Department updateDepartment(Department oldDepartment,Department newDepartment);
}
