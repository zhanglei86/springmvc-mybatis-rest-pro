package com.zl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuzh on 2015/3/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-jdbc.xml" })
public class BasicTest {
	
	@Test
	public void testBase(){
		System.out.println("hello!");
		System.out.println("shenzhen!");
		System.out.println("CN!");
	}
}
