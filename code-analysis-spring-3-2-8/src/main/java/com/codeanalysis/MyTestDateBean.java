package com.codeanalysis;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTestDateBean {

    Date testDate;

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public void test(){
        System.out.println("test at "+new SimpleDateFormat("yyyy-MM-dd").format(testDate));
    }
}