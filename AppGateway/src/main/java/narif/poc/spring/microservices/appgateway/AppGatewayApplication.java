package narif.poc.spring.microservices.appgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class AppGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppGatewayApplication.class, args);
	}
}
