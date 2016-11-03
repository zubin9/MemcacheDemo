package com.rest.learning.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.ReturnDataUpdateContent;
import com.google.code.ssm.api.UpdateSingleCache;
import com.rest.learning.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= populateDummyUsers();
	}

	public List<User> findAllUsers() {
		return users;
	}
	
	@ReadThroughSingleCache(namespace = "userCache", expiration = 15)
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for(User user : users){
			if(user.getName().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
	//@Cacheable(value = "userCache", key = "new Long(counter.incrementAndGet()).toString()")
	@Cacheable(value = "userCache", key = "T(com.rest.learning.service.UserServiceImpl).counter")
	public void saveUser(User user) {
		user.setId(generateUserId());
		users.add(user);
	}

	private long generateUserId() {
		//new Long(counter.incrementAndGet()).toString();
		return counter.incrementAndGet();
	}
	
	@UpdateSingleCache(namespace = "userCache", expiration = 15)
	@ReturnDataUpdateContent
	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getName())!=null;
	}
	
	public void deleteAllUsers(){
		users.clear();
	}

	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"ABC",30, 90000));
		users.add(new User(counter.incrementAndGet(),"DEF",35, 80000));
		users.add(new User(counter.incrementAndGet(),"GHI",40, 70000));
		users.add(new User(counter.incrementAndGet(),"PQR",45, 40000));
		return users;
	}

}
