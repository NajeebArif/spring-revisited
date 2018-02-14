/**
 * 
 */
package narif.poc.spring.microservices.employeeservice.dao;

import narif.poc.spring.microservices.employeeservice.entities.Department;

/**
 * @author narif
 *
 */
public interface DepartmentRedisRepo {
	void saveDepartment(Department dept);
	void updateDepartment(Department dept);
	void deleteDepartment(Department dept);
	Department findDepartment(Long deptId);
}
