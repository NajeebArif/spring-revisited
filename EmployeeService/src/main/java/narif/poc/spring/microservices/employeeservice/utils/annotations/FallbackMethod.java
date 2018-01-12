/**
 * 
 */
package narif.poc.spring.microservices.employeeservice.utils.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(SOURCE)
@Target(METHOD)
/**
 * @author narif
 * Annotation is just for documentation purpose
 * It just states that the target method is fallback for a particular Hystrix wrapped method
 */
public @interface FallbackMethod {
	
	String forHystrixWrappedMethod();

}
