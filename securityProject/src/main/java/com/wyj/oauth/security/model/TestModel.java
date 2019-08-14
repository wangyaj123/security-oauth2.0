package com.wyj.oauth.security.model;

/**
 * @author wangyajing
 * @date 209-08-14
 */
public class TestModel {
    private String subject;
    private int num;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "subject='" + subject + '\'' +
                ", num=" + num +
                '}';
    }
}
