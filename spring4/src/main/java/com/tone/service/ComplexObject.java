package com.tone.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/31
 */
public class ComplexObject {

    private Map<String, String> adminEmails = new HashMap<>();

    public Map<String, String> getAdminEmails() {
        return adminEmails;
    }

    public void setAdminEmails(Map<String, String> adminEmails) {
        this.adminEmails = adminEmails;
    }
}
