package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
		List list1=new ArrayList();
		list1.add("aa");
		list1.add("bb");
		list1.add("cc")
		;
		List list2=new ArrayList();
		list1.add("dd");
		list1.add("ee");
		list1.add("cc");

		System.out.println(Collections.disjoint(list1,list2));
	}

}
