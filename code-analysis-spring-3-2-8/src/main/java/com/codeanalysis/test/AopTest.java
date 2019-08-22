package com.codeanalysis.test;

import com.codeanalysis.aop.MathCalculator;
import com.codeanalysis.config.MainConfigOfAOP;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
		
		//1、不要自己创建对象
//		MathCalculator mathCalculator = new MathCalculator();
//		mathCalculator.div(1, 1);
		MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
		
		mathCalculator.div(1, 0);
		
		applicationContext.close();
	}

}
