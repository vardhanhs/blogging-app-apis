package com.blogging_apis.blog;

import com.blogging_apis.blog.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BloggingAppApisApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserRepo userRepo;

	@Test
	public void repoTest(){
		String className = this.userRepo.getClass().getName();
		String packageName = this.userRepo.getClass().getPackage().getName();
		System.out.println(className);
		System.out.println(packageName);
	}


}
