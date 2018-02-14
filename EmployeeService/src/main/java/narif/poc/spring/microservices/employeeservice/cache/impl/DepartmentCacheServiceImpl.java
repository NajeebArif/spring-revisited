/**
 * 
 */
package narif.poc.spring.microservices.employeeservice.cache.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import narif.poc.spring.microservices.employeeservice.cache.DepartmentCacheService;
import narif.poc.spring.microservices.employeeservice.dao.DepartmentRedisRepo;
import narif.poc.spring.microservices.employeeservice.entities.Department;
import narif.poc.spring.microservices.employeeservice.microclients.DepartmentServiceRestTemplateClient;

/**
 * @author narif
 *
 */
@Service
public class DepartmentCacheServiceImpl implements DepartmentCacheService {
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentCacheServiceImpl.class);
	
	@Autowired
	private DepartmentRedisRepo redisRepo;

	/* (non-Javadoc)
	 * @see narif.poc.spring.microservices.employeeservice.cache.DepartmentCacheService#saveDepartment(narif.poc.spring.microservices.employeeservice.entities.Department)
	 */
	@Override
	public void saveDepartment(Department dept) {
		try {
			redisRepo.saveDepartment(dept);
		}catch(Exception ex) {
			logger.error("Unable to cache Department {} in Redis.",dept.getDepartmentId());
		}
	}

	/* (non-Javadoc)
	 * @see narif.poc.spring.microservices.employeeservice.cache.DepartmentCacheService#updateDepartment(narif.poc.spring.microservices.employeeservice.entities.Department)
	 */
	@Override
	public void updateDepartment(Department dept) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see narif.poc.spring.microservices.employeeservice.cache.DepartmentCacheService#deleteDepartment(narif.poc.spring.microservices.employeeservice.entities.Department)
	 */
	@Override
	public void deleteDepartment(Department dept) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see narif.poc.spring.microservices.employeeservice.cache.DepartmentCacheService#findDepartment(java.lang.Long)
	 */
	@Override
	public Department findDepartment(Long deptId) {
		try {
			return redisRepo.findDepartment(deptId);
		}catch(Exception ex) {
			logger.error("Error encountered while trying to retrieve department {} check the redis cache.\n Exception {}",deptId,ex);
			return null;
		}
	}

}
