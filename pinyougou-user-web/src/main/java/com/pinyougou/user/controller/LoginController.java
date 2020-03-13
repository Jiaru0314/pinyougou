package com.pinyougou.user.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: LoginUserController
 * @description:
 * @author: XZQ
 * @create: 2020/2/24 9:27
 **/
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/name")
    public Map showName() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        HashMap<String, String> map = new HashMap<>();
        map.put("loginName", name);
        return map;
    }
}
