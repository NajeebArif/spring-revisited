package narif.poc.spring.microservices.specialroutesservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import narif.poc.spring.microservices.specialroutesservice.exception.NoRouteFoundException;
import narif.poc.spring.microservices.specialroutesservice.model.AbTesting;
import narif.poc.spring.microservices.specialroutesservice.repository.AbTestingJpaRepo;

@Service
@DefaultProperties(commandProperties= {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")})
public class AbTestingRouteService {
	
	@Autowired
	private AbTestingJpaRepo repo;
	
	public AbTesting getRoute(String serviceName) {
		AbTesting newRoute = repo.findOne(serviceName);
		if(newRoute==null)
			throw new NoRouteFoundException();
		return newRoute;
	}
	
	public void saveRoute(AbTesting route) {
		repo.saveAndFlush(route);
	}
	
	public void updateRoute(AbTesting route) {
		repo.saveAndFlush(route);
	}
	
	public void deleteRoute(AbTesting route) {
		repo.delete(route);
	}

}
