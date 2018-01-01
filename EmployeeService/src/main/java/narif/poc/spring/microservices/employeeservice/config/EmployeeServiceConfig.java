package narif.poc.spring.microservices.employeeservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceConfig {
	
	@Value("${employeeservice.env}")
	private String employeeserviceEnv;
	
	@Value("${some.dev.property:dev}")
	private String devProperty;
	
	@Value("${some.useless.property:default}")
	private String uselessProperty;

	public String getEnv() {
		return employeeserviceEnv;
	}

	public String getDevProperty() {
		return devProperty;
	}

	public String getUselessProperty() {
		return uselessProperty;
	}
	
	
}
