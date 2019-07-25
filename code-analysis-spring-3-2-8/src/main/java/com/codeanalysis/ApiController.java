package com.codeanalysis;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/***
 * <p>
 * API 控制器注解<br>
 * </br>
 * 如果不指定value，默认服务名：{type}_{name}
 * </p>
 * @author 顾用峰
 * @date 2016年12月11日 下午4:58:47
 * @version v1.0
 */
@Documented
@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiController {
	/*** 该值作为Bean的id，字段默认不需要填写。自动生成为：{type}_{name} */
	String value() default "";
	String name();
	String type();
	String version() default "1.0";
}
