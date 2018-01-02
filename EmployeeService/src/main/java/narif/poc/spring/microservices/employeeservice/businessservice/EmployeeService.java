package narif.poc.spring.microservices.employeeservice.businessservice;

import java.util.List;

import narif.poc.spring.microservices.employeeservice.entities.Department;
import narif.poc.spring.microservices.employeeservice.entities.Employee;

public interface EmployeeService {
	
	public List<Employee> findAllEmployees();
	
	public Employee findEmployeeForEmpId(Long employeeId);
	
	public List<String> checkTheConfigData();
	
	public Department getDepartmentForEmployee(Employee emp);
}
