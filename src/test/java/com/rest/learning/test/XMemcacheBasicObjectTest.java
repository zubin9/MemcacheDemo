package com.rest.learning.test;

import com.rest.learning.model.User;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class XMemcacheBasicObjectTest {

	public static void main(String args[]) throws Exception {
		MemcachedClient client = new XMemcachedClient(AddrUtil.getAddresses("127.0.0.1:11211 127.0.0.1:11212"));
		User user1 = new User(1, "abc", 28, 60000);
		client.set("1", 5, user1);
		client.add("2", 15, user1);
		client.setName("userCache");
		System.out.println("Cache Instance name is : " + client.getName());
		System.out.println("Server Description is : " + client.getServersDescription());
		
		User userFromCache = (User)client.get("1");
		System.out.println("Value: " + userFromCache);
		/*
		Thread.sleep(10*1000);
		User userAfterWait = (User)client.get("1");
		System.out.println("Value after expiration of 1: " + userAfterWait);
	
		User userAfterWait2 = (User)client.get("2");
		System.out.println("Value after expiration of 2: " + userAfterWait2);
		*/
		
		MemcachedClient client2 = new XMemcachedClient(AddrUtil.getAddresses("127.0.0.1:11211 127.0.0.1:11212"));
		client.add("2", 15, user1);
		System.out.println("Cache Instance name is : " + client2.getName());
		System.out.println("Server Description is : " + client2.getServersDescription());
		
		User userAfterWait2 = (User)client2.get("2");
		System.out.println("client2: " + userAfterWait2);
	}
	
}
