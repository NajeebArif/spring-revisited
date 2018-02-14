package narif.poc.spring.microservices.employeeservice.cache;

import narif.poc.spring.microservices.employeeservice.entities.Department;

public interface DepartmentCacheService {
	
	void saveDepartment(Department dept);
	void updateDepartment(Department dept);
	void deleteDepartment(Department dept);
	Department findDepartment(Long deptId);
}
