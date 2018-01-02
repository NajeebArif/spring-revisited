package narif.poc.spring.microservices.departmentservice.businessservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import narif.poc.spring.microservices.departmentservice.businessservice.DepartmentService;
import narif.poc.spring.microservices.departmentservice.dao.DepartmentsJpaDao;
import narif.poc.spring.microservices.departmentservice.entities.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentsJpaDao deptRepo;
	
	@Override
	public List<Department> findAllDepartments() {
		return deptRepo.findAll();
	}

	@Override
	public Department findDepartmentForDepartmentId(Long departmentId) {
		return deptRepo.getOne(departmentId);
	}

}
