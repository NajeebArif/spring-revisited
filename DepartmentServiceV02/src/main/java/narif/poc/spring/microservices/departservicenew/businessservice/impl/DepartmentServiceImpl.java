package narif.poc.spring.microservices.departservicenew.businessservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import narif.poc.spring.microservices.departservicenew.businessservice.DepartmentService;
import narif.poc.spring.microservices.departservicenew.dao.DepartmentsJpaDao;
import narif.poc.spring.microservices.departservicenew.entities.Department;

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
