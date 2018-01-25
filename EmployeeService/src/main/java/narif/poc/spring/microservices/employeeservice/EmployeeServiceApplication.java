package narif.poc.spring.microservices.employeeservice;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import narif.poc.spring.microservices.employeeservice.utils.UserContextInterceptor;

@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableResourceServer
public class EmployeeServiceApplication {
	
	@Bean
	public OAuth2RestTemplate oauth2RestTmplate(OAuth2ClientContext oauth2ClientContext,
			OAuth2ProtectedResourceDetails details) {
		return new OAuth2RestTemplate(details,oauth2ClientContext);
	}
	
//	@LoadBalanced
//	@Bean
//	public RestTemplate getRestTemplate() {
//		RestTemplate template = new RestTemplate();
//		List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
//		if(interceptors==null) {
//			interceptors = Collections.singletonList(new UserContextInterceptor());
//		}else {
//			interceptors.add(new UserContextInterceptor());
//		}
//		template.setInterceptors(interceptors);
//		return template;
//	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
}
