package narif.poc.spring.microservices.departmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableBinding(Source.class)
public class DepartmentsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentsServiceApplication.class, args);
	}
}
