package com.codeanalysis.test;


import com.codeanalysis.java.util.HashMap;

import java.util.Map;

public class TestHashMap {
    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();
        map.put("a","1");
        System.out.println(map.get("a"));
    }
}
