package narif.poc.spring.microservices.departservicenew.utils.hystrix;

import java.util.concurrent.Callable;

import narif.poc.spring.microservices.departservicenew.utils.UserContext;
import narif.poc.spring.microservices.departservicenew.utils.UserContextHolder;

public class DelegatingUserContextCallable<V> implements Callable<V> {

	private final Callable<V> delegate;
	private UserContext originalUserContext;

	public DelegatingUserContextCallable(Callable<V> delegate, UserContext originalUserContext) {
		super();
		this.delegate = delegate;
		this.originalUserContext = originalUserContext;
	}

	@Override
	public V call() throws Exception {
		UserContextHolder.setContext(originalUserContext);
		try {
			return delegate.call();
		} finally {
			this.originalUserContext = null;
		}
	}

	public static <V> Callable<V> create(Callable<V> delegate, UserContext userContext) {
		return new DelegatingUserContextCallable<V>(delegate, userContext);
	}

}
