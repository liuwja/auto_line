package com.fotile;

import com.datasweep.compatibility.manager.ServerImpl;
import com.fotile.proxy.ServerImplProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AutolineServiceApplicationTests {

	@Autowired
	ServerImpl server;

	@Autowired
	ServerImplProxy serverImplProxy;

	@Test
	void contextLoads() {
	}
}