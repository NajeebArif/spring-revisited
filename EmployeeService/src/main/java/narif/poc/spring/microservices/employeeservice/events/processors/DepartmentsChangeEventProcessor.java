package narif.poc.spring.microservices.employeeservice.events.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import narif.poc.spring.microservices.employeeservice.events.model.DepartmentsChangeModel;

@Component
public class DepartmentsChangeEventProcessor {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentsChangeEventProcessor.class);

	public void processTheChangeEvent(DepartmentsChangeModel model) {
		switch (model.getAction()) {
		case "DELETE":
			logger.info("Department with deptId {} is deleted", model.getDeptartmentId());
			break;
		case "CREATE":
			logger.info("Department with deptid {} is created", model.getDeptartmentId());
			break;
		case "UPDATED":
			logger.info("Department with deptId {} is updated", model.getDeptartmentId());
			break;
		default:
			logger.info("Action Performed {} for Department id {}", model.getAction(), model.getDeptartmentId());
			break;
		}
	}
}
