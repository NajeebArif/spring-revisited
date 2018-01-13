package narif.poc.spring.microservices.specialroutesservice;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import narif.poc.spring.microservices.specialroutesservice.utils.UserContextFilter;

@SpringBootApplication
@EnableJpaRepositories
@EnableCircuitBreaker
@EnableEurekaClient
public class SpecialRoutesServiceApplication {
	
	@Bean
	public Filter userContextFilter() {
		return new UserContextFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpecialRoutesServiceApplication.class, args);
	}
}
