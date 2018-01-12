package narif.poc.spring.microservices.employeeservice.microclients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import narif.poc.spring.microservices.employeeservice.entities.Department;

@Component
public class DepartmentServiceRestTemplateClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Department getDepartmentForDepartmentId(Long departmentId) {
		ResponseEntity<Department> restExchange = restTemplate.exchange("http://departmentService/departments/{deptId}", HttpMethod.GET,
				null, Department.class,departmentId);
		return restExchange.getBody();
	}

}
