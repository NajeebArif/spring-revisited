package narif.poc.spring.microservices.departservicenew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class DepartmentServiceV02Application {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceV02Application.class, args);
	}
}
