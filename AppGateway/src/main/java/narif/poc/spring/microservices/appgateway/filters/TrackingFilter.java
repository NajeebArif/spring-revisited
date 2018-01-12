package narif.poc.spring.microservices.appgateway.filters;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import narif.poc.spring.microservices.appgateway.utils.FilterUtils;

@Component
public class TrackingFilter extends ZuulFilter{
	
	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER = true;
	private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);
	
	@Autowired
	private FilterUtils filterUtils;

	@Override
	public Object run() {
		if(isCorrelationIdPresent()) {
			logger.debug("Correlation id found in Tracking filter. {}",filterUtils.getCorrelationId());
		}else {
			logger.debug("No correlation id found in Tracking filter and thus creating a new one.");
			filterUtils.setCorrelationId(generateCorrelationId());
			logger.debug("CorrelationId generated in tracking filter. {}",filterUtils.getCorrelationId());
		}
		
		RequestContext ctx = RequestContext.getCurrentContext();
		logger.debug("Processing incomming request for {}.",ctx.getRequest().getRequestURI());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return SHOULD_FILTER;
	}

	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}

	@Override
	public String filterType() {
		return FilterUtils.PRE_FILTER_TYPE;
	}
	
	private boolean isCorrelationIdPresent() {
		if(filterUtils.getCorrelationId()!=null)
			return true;
		else
			return false;
	}
	
	private String generateCorrelationId() {
		return UUID.randomUUID().toString();
	}

}
