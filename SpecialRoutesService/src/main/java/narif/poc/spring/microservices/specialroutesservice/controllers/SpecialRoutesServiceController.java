package narif.poc.spring.microservices.specialroutesservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import narif.poc.spring.microservices.specialroutesservice.model.AbTesting;
import narif.poc.spring.microservices.specialroutesservice.service.AbTestingRouteService;
import narif.poc.spring.microservices.specialroutesservice.utils.UserContextHolder;

@RestController
@RequestMapping("v1/route")
public class SpecialRoutesServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(SpecialRoutesServiceController.class);
	
	@Autowired
	private AbTestingRouteService routingService;
	
	@RequestMapping(value="{serviceName}", method=RequestMethod.GET)
	public AbTesting getAbTestingForServiceName(@PathVariable("serviceName") String serviceName) {
		logger.debug("Calculating the special route for correlationId {}.",UserContextHolder.getContext().getCorrelationId());
		return routingService.getRoute(serviceName);
	}

}
