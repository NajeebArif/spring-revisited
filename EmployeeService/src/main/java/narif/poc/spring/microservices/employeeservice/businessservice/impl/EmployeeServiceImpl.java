package narif.poc.spring.microservices.employeeservice.businessservice.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import narif.poc.spring.microservices.employeeservice.businessservice.EmployeeService;
import narif.poc.spring.microservices.employeeservice.config.EmployeeServiceConfig;
import narif.poc.spring.microservices.employeeservice.dao.EmployeeJpaDao;
import narif.poc.spring.microservices.employeeservice.entities.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeJpaDao empRepo;
	
	@Autowired
	private EmployeeServiceConfig empConfigData;
	
	@Override
	public List<Employee> findAllEmployees() {
		return empRepo.findAll();
	}

	@Override
	public Employee findEmployeeForEmpId(Long employeeId) {
		return empRepo.getOne(employeeId);
	}

	@Override
	public List<String> checkTheConfigData() {
		String header = "The values fetched from the config server for the values are:";
		String envName = "Current Environment(custom property): "+empConfigData.getEnv();
		String devOnlyValue = "Some dev specific property(should not come in other env): "+empConfigData.getDevProperty();
		String defaultOnlyValue = "Some default specific property(should not come in other env): "+empConfigData.getUselessProperty(); 
		System.out.println(header);
		System.out.println(envName);
		System.out.println(devOnlyValue);
		System.out.println(defaultOnlyValue);
		return Arrays.asList(header,envName,devOnlyValue,defaultOnlyValue);
	}

}
