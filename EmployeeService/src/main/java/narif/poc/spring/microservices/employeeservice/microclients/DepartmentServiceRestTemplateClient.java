package narif.poc.spring.microservices.employeeservice.microclients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import narif.poc.spring.microservices.employeeservice.entities.Department;

@Component
public class DepartmentServiceRestTemplateClient {
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	public Department getDepartmentForDepartmentId(Long departmentId) {
		ResponseEntity<Department> restExchange = restTemplate.exchange("http://appgateway/api/departmentservice/departments/{deptId}", HttpMethod.GET,
				null, Department.class,departmentId);
		return restExchange.getBody();
	}

}
