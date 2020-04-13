package com.codeanalysis.test;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPSolution {
    List<String> res = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 1, new ArrayList<String>());
        return res;
    }
    public void dfs(String s, int start, int end, List<String> r) {
        if (start < 0 || start > s.length() || end < 0 || end > s.length() + 1 || r.size() > 4) {
            return;
        }
        if (r.size() == 4 && start == s.length()) {
            String rs = "";
            for (int i = 0; i < r.size(); i++) {
                if (i < r.size() - 1) {
                    rs += r.get(i) + ".";
                } else {
                    rs += r.get(i);
                }
            }
            res.add(rs);
            return;
        }
        for (int i = 0; i < 3; i++) {
            int ends = end + i;
            if (start < 0 || start > s.length() || ends < 0 || ends > s.length()) {
                continue;
            }
            String juge = s.substring(start, ends);
            int jugeint = Integer.valueOf(juge);
            if (juge.charAt(0) == '0' && juge.length() > 1) {
                continue;
            }
            if (jugeint <= 255) {
                r.add(juge);
                dfs(s, ends, ends + 1, r);
                r.remove(r.size() - 1);
            }
        }
    }
    public static void main(String[] args) {
        RestoreIPSolution r = new RestoreIPSolution();
        System.out.println(r.restoreIpAddresses("101023"));
    }
}
