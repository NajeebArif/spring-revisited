package narif.poc.spring.microservices.employeeservice.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import narif.poc.spring.microservices.employeeservice.dao.DepartmentRedisRepo;
import narif.poc.spring.microservices.employeeservice.entities.Department;

@Repository
public class DepartmentRedisRepoImpl implements DepartmentRedisRepo {
	
	private static final String HASH_NAME = "departments";
	
	private RedisTemplate<String,Department> redisTemplate;
	private HashOperations<String, Long, Department> hashOperations;
	

	public DepartmentRedisRepoImpl() {
		super();
	}
	
	@Autowired
	public DepartmentRedisRepoImpl(RedisTemplate redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void saveDepartment(Department dept) {
		hashOperations.put(HASH_NAME, dept.getDepartmentId(),dept);
	}

	@Override
	public void updateDepartment(Department dept) {
		hashOperations.put(HASH_NAME,dept.getDepartmentId(),dept);
	}

	@Override
	public void deleteDepartment(Department dept) {
		hashOperations.delete(HASH_NAME, dept.getDepartmentId());
	}

	@Override
	public Department findDepartment(Long deptId) {
		return (Department) hashOperations.get(HASH_NAME, deptId);
	}

}
