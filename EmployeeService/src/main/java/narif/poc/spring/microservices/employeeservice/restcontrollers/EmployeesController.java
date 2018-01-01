package narif.poc.spring.microservices.employeeservice.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import narif.poc.spring.microservices.employeeservice.businessservice.EmployeeService;
import narif.poc.spring.microservices.employeeservice.entities.Employee;

@RestController
@RequestMapping(value="/employees")
public class EmployeesController {

	@Autowired
	private EmployeeService empService;

	public EmployeesController() {
		super();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Employee> getAllEmployees(){
		return empService.findAllEmployees();
	}
	
	@RequestMapping(value= "{empId}",method=RequestMethod.GET)
	public Employee getEmployeeForEmployeeId(@PathVariable("empId") Employee emp) {
		return emp;
	}
	
	@RequestMapping(value="configData")
	public List<String> getAllConfigData(){
		return empService.checkTheConfigData();
	}

}
