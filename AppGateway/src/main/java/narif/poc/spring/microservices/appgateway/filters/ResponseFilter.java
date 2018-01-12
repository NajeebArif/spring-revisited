package narif.poc.spring.microservices.appgateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import narif.poc.spring.microservices.appgateway.utils.FilterUtils;

@Component
public class ResponseFilter extends ZuulFilter {
	
	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER = false;
	private static final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);

	@Autowired
	private FilterUtils filterUtils;
	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		logger.debug("Adding correlation id to the response header. {}",filterUtils.getCorrelationId());
		ctx.getResponse().addHeader(FilterUtils.CORRELATION_ID, filterUtils.getCorrelationId());
		logger.debug("Completing the outgoing request for {}.", ctx.getRequest().getRequestURI());
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
		return FilterUtils.POST_FILTER_TYPE;
	}

}
