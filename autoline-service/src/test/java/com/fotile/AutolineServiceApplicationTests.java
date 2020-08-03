package com.fotile;

import com.datasweep.compatibility.client.DatasweepException;
import com.datasweep.compatibility.client.ServerInfo;
import com.datasweep.compatibility.client.Unit;
import com.datasweep.compatibility.client.User;
import com.datasweep.compatibility.manager.ServerImpl;
import com.datasweep.plantops.common.exceptions.DatasweepProxyException;
import com.datasweep.plantops.proxies.ProxyFactory;
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
	@Test
	void test(){
		Unit unit = serverImplProxy.getUnitBySerialNumber("123");
		System.err.println(unit);
	}
}