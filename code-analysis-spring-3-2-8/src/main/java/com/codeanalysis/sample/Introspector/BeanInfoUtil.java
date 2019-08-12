package com.codeanalysis.sample.Introspector;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

// https://www.cnblogs.com/uu5666/p/8601983.html
public class BeanInfoUtil {

    public static void setProperty(UserInfo userInfo,String userName)throws Exception{
        PropertyDescriptor propDesc=new PropertyDescriptor(userName,UserInfo.class);
        Method methodSetUserName=propDesc.getWriteMethod();
        methodSetUserName.invoke(userInfo, "wong");
        System.out.println("set userName:"+userInfo.getUserName());
    }

    public static void getProperty(UserInfo userInfo,String userName)throws Exception{
        PropertyDescriptor proDescriptor =new PropertyDescriptor(userName,UserInfo.class);
        Method methodGetUserName=proDescriptor.getReadMethod();
        Object objUserName=methodGetUserName.invoke(userInfo);
        System.out.println("get userName:"+objUserName.toString());
    }

    public static void main(String[] args) throws Exception {
        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(123);
        userInfo.setAge(22);
        userInfo.setUserName("helloName");
        userInfo.setEmailAddress("hello@123.com");
        getProperty(userInfo,"age");
    }
}
