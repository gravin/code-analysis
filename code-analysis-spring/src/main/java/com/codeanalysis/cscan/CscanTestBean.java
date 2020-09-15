package com.codeanalysis.cscan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CscanTestBean {

    @Autowired
    private CscanTestChildBean cscanTestChildBean;

    public String getTestStr() {
        return cscanTestChildBean.getTestStr();
    }

}