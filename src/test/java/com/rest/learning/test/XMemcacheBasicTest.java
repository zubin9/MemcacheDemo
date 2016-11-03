package com.rest.learning.test;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class XMemcacheBasicTest {

	public static void main(String args[]) throws Exception {
		MemcachedClient client = new XMemcachedClient(AddrUtil.getAddresses("127.0.0.1:11211 127.0.0.1:11212"));
		client.set("key1", 5, "value1");
		String str = (String)client.get("key1");
		System.out.println("Value: " + str);
		
		Thread.sleep(10*1000);
		String strAfterWait = (String)client.get("key1");
		System.out.println("Value after expiration: " + strAfterWait);
	}
	
}
