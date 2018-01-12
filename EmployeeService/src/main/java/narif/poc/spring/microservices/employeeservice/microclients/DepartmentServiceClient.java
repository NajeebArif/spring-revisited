package narif.poc.spring.microservices.employeeservice.microclients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import narif.poc.spring.microservices.employeeservice.entities.Department;

@FeignClient("DEPARTMENTSERVICE")
public interface DepartmentServiceClient {
	@RequestMapping(method=RequestMethod.GET,value="/departments/{deptId}", consumes="application/json")
	public Department getDepartment(@PathVariable("deptId") Long departmentId);
}
