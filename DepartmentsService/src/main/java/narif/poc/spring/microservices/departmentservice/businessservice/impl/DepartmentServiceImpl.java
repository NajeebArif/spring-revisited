package narif.poc.spring.microservices.departmentservice.businessservice.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import narif.poc.spring.microservices.departmentservice.businessservice.DepartmentService;
import narif.poc.spring.microservices.departmentservice.dao.DepartmentsJpaDao;
import narif.poc.spring.microservices.departmentservice.entities.Department;
import narif.poc.spring.microservices.departmentservice.events.publishers.DepartmentsChangePublisher;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	
	@Autowired
	private DepartmentsJpaDao deptRepo;
	
	@Autowired
	private DepartmentsChangePublisher changePublisher;
	
	@Override
	public List<Department> findAllDepartments() {
		changePublisher.publishDepartmentsChange("FIND_ALL", 0L);
		return deptRepo.findAll();
	}

	@Override
	public Department findDepartmentForDepartmentId(Long departmentId) {
		changePublisher.publishDepartmentsChange("FIND_ONE", departmentId);
		return deptRepo.getOne(departmentId);
	}

	@Override
	public void deleteDepartment(Department dept) {
		logger.info("Deleting Department {}",dept.getDepartmentId());
		deptRepo.delete(dept);
		changePublisher.publishDepartmentsChange("DELETE", dept.getDepartmentId());
	}

	@Override
	public Department createDepartment(Department dept) {
		logger.info("Creating Department: with Department Name {}, Location Id {} and manager Id {}",dept.getDepartmentName(),
				dept.getLocationId(),dept.getManagerId());
		deptRepo.saveAndFlush(dept);
		changePublisher.publishDepartmentsChange("CREATE", dept.getDepartmentId());
		return dept;
	}

	@Override
	public Department updateDepartment(Department oldDepartment, Department newDepartment) {
		oldDepartment.setDepartmentName(newDepartment.getDepartmentName()==null?oldDepartment.getDepartmentName():newDepartment.getDepartmentName());
		oldDepartment.setLocationId(newDepartment.getLocationId()==null?oldDepartment.getLocationId():newDepartment.getLocationId());
		oldDepartment.setManagerId(newDepartment.getManagerId()==null?oldDepartment.getManagerId():newDepartment.getManagerId());
		deptRepo.saveAndFlush(oldDepartment);
		changePublisher.publishDepartmentsChange("UPDATE", oldDepartment.getDepartmentId());
		return oldDepartment;
	}

}
