package narif.poc.spring.microservices.departmentservice.events.publishers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import narif.poc.spring.microservices.departmentservice.events.model.DepartmentsChangeModel;

@Component
public class DepartmentsChangePublisher {
	private Source source;
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentsChangePublisher.class);

	@Autowired
	public DepartmentsChangePublisher(Source source) {
		super();
		this.source = source;
	}
	
	public void publishDepartmentsChange(String action, Long deptartmentId) {
		logger.debug("Sending kafka messgae {} for Department Id: {}",action,deptartmentId);
		
		DepartmentsChangeModel changeModel = new DepartmentsChangeModel(action, deptartmentId);
		
		source.output().send(MessageBuilder.withPayload(changeModel).build());
	}
}
