package narif.poc.spring.microservices.employeeservice.businessservice.impl;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import narif.poc.spring.microservices.employeeservice.businessservice.EmployeeService;
import narif.poc.spring.microservices.employeeservice.dao.EmployeeJpaDao;
import narif.poc.spring.microservices.employeeservice.entities.Department;
import narif.poc.spring.microservices.employeeservice.entities.Employee;
import narif.poc.spring.microservices.employeeservice.microclients.DepartmentServiceClient;
import narif.poc.spring.microservices.employeeservice.microclients.DepartmentServiceRestTemplateClient;
import narif.poc.spring.microservices.employeeservice.utils.UserContextHolder;
import narif.poc.spring.microservices.employeeservice.utils.annotations.FallbackMethod;

@Service
@DefaultProperties(commandProperties= {
		@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
		@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="75"),
		@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="7000"),
		@HystrixProperty(name="metricsRollingStats.timeInMilliseconds",value="15000"),
		@HystrixProperty(name="metricsRollingStats.numOfBuckets",value="3")
})
public class EmployeeServiceImpl implements EmployeeService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeJpaDao empRepo;
	
	@Autowired
	private DepartmentServiceClient dptClient;
	
	@Autowired
	private DepartmentServiceRestTemplateClient deptRestTempClient;
	
	@HystrixCommand(commandProperties= {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="12000")},
			threadPoolKey="employeeServiceSlowCallsTP",
			threadPoolProperties= {@HystrixProperty(name="coreSize",value="5")})
	@Override
	public List<Employee> findAllEmployees() {
		logger.debug("EmpoyeeServiceImpl correlation id: "+UserContextHolder.getContext().getCorrelationId());
		randomlyLongRunning();
		return empRepo.findAll();
	}

	@Override
	public Employee findEmployeeForEmpId(Long employeeId) {
		logger.debug("EmpoyeeServiceImpl correlation id: "+UserContextHolder.getContext().getCorrelationId());
		return empRepo.getOne(employeeId);
	}
	
	@Override
	@HystrixCommand(commandProperties= {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="14000")},
	fallbackMethod = "getDepartmentForEmployeeFallBack",
	threadPoolKey="departmentServiceTP",
	threadPoolProperties= {@HystrixProperty(name="coreSize",value="30")
						  ,@HystrixProperty(name="maxQueueSize",value="10")})
	public Department getDepartmentForEmployee(Employee emp) {
		logger.debug("EmpoyeeServiceImpl correlation id: "+UserContextHolder.getContext().getCorrelationId());
		randomlyLongRunning();
		return deptRestTempClient.getDepartmentForDepartmentId(emp.getDepartmentId());
	}
	
	@FallbackMethod(forHystrixWrappedMethod="getDepartmentForEmployee")
	public Department getDepartmentForEmployeeFallBack(Employee emp) {
		logger.debug("EmpoyeeServiceImpl correlation id: "+UserContextHolder.getContext().getCorrelationId());
		logger.info("Fallback executed for getDepartmentForEmployee method");
		return dptClient.getDepartment(emp.getDepartmentId());
	}

	@Override
	public void createEmployee(Employee emp) {
		logger.debug("EmpoyeeServiceImpl correlation id: "+UserContextHolder.getContext().getCorrelationId());
		empRepo.saveAndFlush(emp);
	}

	@Override
	@HystrixCommand
	public Employee updateEmployeeData(Employee oldEmployee, Employee newEmployee) {
		logger.debug("EmpoyeeServiceImpl correlation id: "+UserContextHolder.getContext().getCorrelationId());
		Class<?> empClazz = newEmployee.getClass();
		Field[] fieldsOfOld = oldEmployee.getClass().getDeclaredFields();
		for(Field oldField: fieldsOfOld) {
			String oldFldName = oldField.getName();
			if(oldFldName.equalsIgnoreCase("empId"))continue;
			oldField.setAccessible(true);
			try {
				Field f = empClazz.getField(oldFldName);
				f.setAccessible(true);
				oldField.set(f.get(newEmployee), oldEmployee);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		empRepo.saveAndFlush(oldEmployee);
		return oldEmployee;
	}

	@Override
	@HystrixCommand(fallbackMethod="getAllEmployeesForDepartmentIdFallback")
	public List<Employee> getAllEmployeesForDepartmentId(Long departmentId) {
		logger.debug("EmpoyeeServiceImpl correlation id: "+UserContextHolder.getContext().getCorrelationId());
		return empRepo.findByDepartmentId(departmentId);
	}
	
	@FallbackMethod(forHystrixWrappedMethod="getAllEmployeesForDepartmentId")
	public List<Employee> getAllEmployeesForDepartmentIdFallback(Long departmentId){
		Employee emp = new Employee();
		return Collections.singletonList(emp);
	}
	
	private void randomlyLongRunning() {
		Random ran = new Random();
		int randNum = ran.nextInt((4-1)+1)+1;
		if(randNum==4)sleep();
	}

	private void sleep() {
		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
