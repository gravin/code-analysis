package com.gupaoedu.springboot;

import com.gupaoedu.springboot.annotation.EnableAutoImport;
import com.gupaoedu.springboot.test.FirstClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableAutoImport
public class ImportSelectorMain {

	public static void main(String[] args) {

		ConfigurableApplicationContext ca = SpringApplication.run(ImportSelectorMain.class, args);
		FirstClass bean = ca.getBean(FirstClass.class);
		bean.sayHello();
	}
}
