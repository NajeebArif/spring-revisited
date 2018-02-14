package narif.poc.spring.microservices.departmentservice.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import narif.poc.spring.microservices.departmentservice.businessservice.DepartmentService;
import narif.poc.spring.microservices.departmentservice.entities.Department;

@RestController
@RequestMapping(value="/departments")
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
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(code=HttpStatus.CREATED)
	public void createDepartment(@RequestBody Department dept) {
		deptService.createDepartment(dept);
	}
	
	@RequestMapping(value="{deptId}",method=RequestMethod.DELETE)
	public void removeDepartment(@PathVariable("deptId") Department dept) {
		deptService.deleteDepartment(dept);
	}
	
	@RequestMapping(value="{deptId}",method=RequestMethod.PUT)
	public Department updateDepartment(@PathVariable("deptId") Department oldDept,@RequestBody Department newDept) {
		return deptService.updateDepartment(oldDept, newDept);
	}
	
}
