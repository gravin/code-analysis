package com.codeanalysis.test;

import com.codeanalysis.MyTestBean;
import com.codeanalysis.MyTestBean2;
import com.codeanalysis.MyTestBean2Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopJDKProxyTest3 {

	public static void main(String[] args) {
		ApplicationContext bf = new ClassPathXmlApplicationContext("spring/aspectTest2.xml");
		MyTestBean2Service myTestBean2Service = (MyTestBean2Service) bf.getBean("test");
		myTestBean2Service.test();
	}

}
