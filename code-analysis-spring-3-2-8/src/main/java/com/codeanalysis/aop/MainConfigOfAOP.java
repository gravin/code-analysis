package com.codeanalysis.aop;


import com.codeanalysis.aop.LogAspects;
import com.codeanalysis.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

	@Bean
	public MathCalculator calculator(){
		return new MathCalculator();
	}

	@Bean
	public LogAspects logAspects(){
		return new LogAspects();
	}
}

