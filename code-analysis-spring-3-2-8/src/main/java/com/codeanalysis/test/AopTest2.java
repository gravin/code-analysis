package com.codeanalysis.test;

import com.codeanalysis.MyTestBean;
import com.codeanalysis.aop.MathCalculator;
import com.codeanalysis.config.MainConfigOfAOP;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest2 {

	public static void main(String[] args) {
		ApplicationContext bf = new ClassPathXmlApplicationContext("spring/aspectTest.xml");
		MyTestBean myTestBean = (MyTestBean) bf.getBean("test");
		myTestBean.test();
	}

}
