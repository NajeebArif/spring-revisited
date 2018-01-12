package narif.poc.spring.microservices.appgateway.utils;

import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;

@Component
public class FilterUtils {
	
	public static final String CORRELATION_ID = "correlation-id";
	public static final String AUTH_TOKEN = "Authorization";
	public static final String USER_ID = "user_id";
    public static final String ORG_ID         = "tmx-org-id";
    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";
    public static final String ROUTE_FILTER_TYPE = "route";
	
	public String getCorrelationId() {
		RequestContext ctx = RequestContext.getCurrentContext();
		if(ctx.getRequest().getHeader(CORRELATION_ID)!=null) {
			return ctx.getRequest().getHeader(CORRELATION_ID);
		}else {
			return ctx.getZuulRequestHeaders().get(CORRELATION_ID);
		}
	}
	
	public void setCorrelationId(String correlationId) {
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);
	}
}
