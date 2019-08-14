package com.wyj.oauth.security.controller;

import com.wyj.oauth.security.model.TestModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyajing
 */
@RestController
public class TestController {
    @RequestMapping(value="/order/demo")
    public TestModel getDemo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        TestModel t = new TestModel();
        t.setNum(2);
        t.setSubject("语文");
        return t;
    }

    @GetMapping("/test")
    public String getTest() {
        TestModel t = new TestModel();
        t.setNum(3);
        t.setSubject("英语");
        return t.toString();
    }
}
