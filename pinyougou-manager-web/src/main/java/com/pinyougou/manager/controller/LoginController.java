package com.pinyougou.manager.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: LoginController
 * @description: 登录控制器
 * @author: XZQ
 * @create: 2020/2/8 10:10
 **/
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/showName")
    public Map name() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map map = new HashMap();
        map.put("loginName", name);
        return map;
    }
}
