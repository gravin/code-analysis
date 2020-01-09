package com.codeanalysis.test;

import com.codeanalysis.MyTestBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest4LTW {

	public static void main(String[] args) {
		ApplicationContext bf = new ClassPathXmlApplicationContext("spring/aspectTestLTW.xml");
		MyTestBean myTestBean = (MyTestBean) bf.getBean("test");
		myTestBean.test();
	}

}
