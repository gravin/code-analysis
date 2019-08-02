package com.codeanalysis.test;

import com.codeanalysis.Car;
import com.codeanalysis.TestA;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class factoryBeanTest {
    public static void main(String[] args) {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring/factoryBeanTest.xml"));
        Car car = (Car) bf.getBean("car");
        System.out.println(car.getBrand() + "," + car.getMaxSpeed() + "," + car.getPrice());
    }
}
