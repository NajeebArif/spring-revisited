package narif.poc.spring.microservices.employeeservice.cache;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestRedisConnection {

	@Test
	public final void test() {
		Jedis server = new Jedis("localhost");
		System.out.println("Connected to Redis");
		System.out.println("Pining redis: "+server.ping());
		String msg = server.ping();
		assertTrue(msg.equals("PONG"));
	}

}
