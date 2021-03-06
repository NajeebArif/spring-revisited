package narif.poc.spring.microservices.departservicenew.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import narif.poc.spring.microservices.departservicenew.businessservice.DepartmentService;
import narif.poc.spring.microservices.departservicenew.entities.Department;

@RestController
@RequestMapping(value="v2/departments")
public class DepartmentsController {
	
	@Autowired
	private DepartmentService deptService;
	
	public DepartmentsController() {
		super();
	}
	
	@RequestMapping
	public List<Department> getAllDepartments(){
		return deptService.findAllDepartments();
	}
	
	@RequestMapping(value="{deptId}")
	public Department getDepartmentForDepartmentId(@PathVariable("deptId") Department dept) {
		return dept;
	}
	
}
