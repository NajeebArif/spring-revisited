package narif.poc.spring.microservices.employeeservice.restcontrollers;

import java.util.List;

import javax.servlet.UnavailableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import narif.poc.spring.microservices.employeeservice.businessservice.EmployeeService;
import narif.poc.spring.microservices.employeeservice.businessservice.impl.EmployeeServiceImpl;
import narif.poc.spring.microservices.employeeservice.entities.Department;
import narif.poc.spring.microservices.employeeservice.entities.Employee;
import narif.poc.spring.microservices.employeeservice.utils.UserContextHolder;

@RestController
@RequestMapping(value="/employees")
public class EmployeesController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeesController.class);

	@Autowired
	private EmployeeService empService;

	public EmployeesController() {
		super();
	}
	
	@RequestMapping
	public List<Employee> getAllEmployees(){
		logger.debug("EmployeesController correlation id: "+UserContextHolder.getContext().getCorrelationId());
		return empService.findAllEmployees();
	}
	
	@RequestMapping(value= "{empId}",method=RequestMethod.GET)
	public Employee getEmployeeForEmployeeId(@PathVariable("empId") Employee emp) {
		logger.debug("EmployeesController correlation id: "+UserContextHolder.getContext().getCorrelationId());
		return emp;
	}
	
	@RequestMapping(value="{empId}/dept")
	public Department getEmployeesDepartment(@PathVariable("empId") Employee emp) {
		logger.debug("EmployeesController correlation id: "+UserContextHolder.getContext().getCorrelationId());
		return empService.getDepartmentForEmployee(emp);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(code=HttpStatus.CREATED)
	public void createEmployee(Employee emp) {
		logger.debug("EmployeesController correlation id: "+UserContextHolder.getContext().getCorrelationId());
		empService.createEmployee(emp);
	}
	
	@RequestMapping(value="{empId}",method=RequestMethod.PUT)
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public Employee updateEmployee(@PathVariable("empId") Employee oldEmp, Employee newEmp) {
		logger.debug("EmployeesController correlation id: "+UserContextHolder.getContext().getCorrelationId());
		return empService.updateEmployeeData(oldEmp, newEmp);
	}
	
	@RequestMapping(value="forDpt/{deptId}")
	public List<Employee> getAllEmployeesForDepartment(@PathVariable("deptId") Long deptId){
		logger.debug("EmployeesController correlation id: "+UserContextHolder.getContext().getCorrelationId());
		return empService.getAllEmployeesForDepartmentId(deptId);
	}
}
