package com.rest.learning.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.google.code.ssm.api.format.SerializationType;
import com.google.code.ssm.providers.CacheException;
import com.rest.learning.model.HelloWorld;
import com.rest.learning.model.User;

public class HelloWorldMain {
	public static void main(String[] args) throws IOException, TimeoutException, CacheException {
		//ApplicationContext context = new ClassPathXmlApplicationContext("mvc-dispatcher-servlet.xml");
		
		Resource resource = new FileSystemResource("G:\\Spring\\Spring_Prac_Zubin\\MemcacheDemo2\\WebContent\\WEB-INF\\mvc-dispatcher-servlet.xml");
		BeanFactory container = new XmlBeanFactory(resource);
		Object obj = container.getBean("helloWorld");
		HelloWorld hw = (HelloWorld) obj;
		
		//HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		hw.getMessage();
		
		obj = container.getBean("memcacheManager");
		com.google.code.ssm.spring.SSMCacheManager ssmcm = (com.google.code.ssm.spring.SSMCacheManager) obj;
		com.google.code.ssm.spring.SSMCache ssmCache = (com.google.code.ssm.spring.SSMCache) ssmcm.getCache("userCache");
		
		User user1 = new User(1, "abc", 28, 60000);
		ssmCache.put("1", user1);
		
		System.out.println("Cache Name : " + ssmCache.getName());
		System.out.println("Native Cache Name : " + ssmCache.getNativeCache().toString());
		
		//ssmCache.putIfAbsent(2, user1);
		System.out.println("Clear flag : " + ssmCache.isAllowClear());
		
		System.out.println("Expiration time: " + ssmCache.getExpiration());
		User u1 = (User) (ssmCache.getCache().get("1", SerializationType.JAVA));
		
		//User u1 = (User) ssmCache.get("1", User.class);		
		System.out.println("User from cache: " + u1);
		
		//obj = container.getBean("userCache");
		//com.google.code.ssm.spring.SSMCache cache = (com.google.code.ssm.spring.SSMCache) obj;
		//com.google.code.ssm.CacheImpl cache = (com.google.code.ssm.CacheImpl) obj;
		
		/*
		System.out.println("Cache Name : " + cache.getName());
		System.out.println("Native Cache Name : " + cache.getNativeCache().toString());
		User user1 = new User(1, "abc", 28, 60000);
		cache.put("1", user1);
		cache.get("1", User.class);
		*/
		
		//com.google.code.ssm.providers.CacheClientFactory chf = cf.getCacheClientFactory();
		//chf.create(AddrUtil.getAddresses("127.0.0.1:11211 127.0.0.1:11212"), cf.getConfiguration());
	}
}