package examples;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class ExampleBeanTest {
    public static void main(String[] args) {
        BeanFactory bf=new XmlBeanFactory(new ClassPathResource("spring/constructor/exampleBean.xml"));
        ExampleBean bean= (ExampleBean) bf.getBean("exampleBean");
        System.out.println(bean.toString());
    }
}
