package narif.poc.spring.microservices.departmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableJpaRepositories
@EnableResourceServer
public class DepartmentsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentsServiceApplication.class, args);
	}
}
