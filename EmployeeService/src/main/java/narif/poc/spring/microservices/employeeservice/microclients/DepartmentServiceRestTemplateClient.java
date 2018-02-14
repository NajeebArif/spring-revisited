package narif.poc.spring.microservices.employeeservice.microclients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import narif.poc.spring.microservices.employeeservice.cache.DepartmentCacheService;
import narif.poc.spring.microservices.employeeservice.dao.DepartmentRedisRepo;
import narif.poc.spring.microservices.employeeservice.entities.Department;

@Component
public class DepartmentServiceRestTemplateClient {
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceRestTemplateClient.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DepartmentRedisRepo redisRepo;
	
//	@Autowired
//	private DepartmentCacheService deptCache;
	
	public Department getDepartmentForDepartmentId(final Long departmentId) {
		
		final Department dept = checkRedisCache(departmentId);
		
		if(dept!=null) {
			logger.info("Department {} found in the cache and retrieved from there.",dept.getDepartmentId());
			return dept;
		}
		
		logger.info("Unable to locate Department {} in redis cache.",departmentId);
		ResponseEntity<Department> restExchange = restTemplate
				.exchange("http://appgateway/api/departmentservice/departments/{deptId}", 
						HttpMethod.GET,
						null, 
						Department.class,
						departmentId);
		
		Department deptNew = restExchange.getBody();
		logger.info("Found Department");
		if(deptNew!=null) {
			logger.info("Fetched Department of {} dept id from deptService",deptNew.getDepartmentId());
			cacheDepartmentObject(deptNew);
		}else
			logger.error("No Department found for department id {} even in the dept service.",departmentId);
			
		return deptNew;
	}
	
	private Department checkRedisCache(Long departmentId) {
		try {
			return redisRepo.findDepartment(departmentId);
		}catch(Exception ex) {
			logger.error("Error encountered while trying to retrieve department {} check the redis cache.\n Exception {}",departmentId,ex);
			return null;
		}
	}
	
	private void cacheDepartmentObject(Department dept) {
		try {
			redisRepo.saveDepartment(dept);
		}catch(Exception ex) {
			logger.error("Unable to cache Department {} in Redis.",dept.getDepartmentId());
		}
	}

}
