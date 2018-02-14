package narif.poc.spring.microservices.employeeservice;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import narif.poc.spring.microservices.employeeservice.config.EmployeeServiceConfig;
import narif.poc.spring.microservices.employeeservice.events.model.DepartmentsChangeModel;
import narif.poc.spring.microservices.employeeservice.events.processors.DepartmentsChangeEventProcessor;
import narif.poc.spring.microservices.employeeservice.utils.UserContextInterceptor;

@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableBinding(Sink.class)
public class EmployeeServiceApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceApplication.class);
	
	@Autowired 
	private DepartmentsChangeEventProcessor eventProcessor;
	
	@Autowired
	private EmployeeServiceConfig serviceConfig;
	
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate template = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
		if(interceptors==null) {
			interceptors = Collections.singletonList(new UserContextInterceptor());
		}else {
			interceptors.add(new UserContextInterceptor());
		}
		template.setInterceptors(interceptors);
		return template;
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
	
	@StreamListener(Sink.INPUT)
	public void logTheDepartmentChange(DepartmentsChangeModel dptChangeModel) {
		logger.info("Department change detected: ACTION- {}, DEPT_ID- {}",dptChangeModel.getAction(),dptChangeModel.getDeptartmentId());
		eventProcessor.processTheChangeEvent(dptChangeModel);
	}
	
	@Bean
	public JedisConnectionFactory jeddisConnectionFactory() {
		JedisConnectionFactory jedisConnFact = new JedisConnectionFactory();
		jedisConnFact.setHostName(serviceConfig.getRedisServer());
		jedisConnFact.setPort(Integer.parseInt(serviceConfig.getRedisPort()));
		return jedisConnFact;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(){
		RedisTemplate<String,Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jeddisConnectionFactory());
		return template;
	}
}
