package com.su.springdoc.controller;

import com.su.springdoc.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author xusu
 * @since 2021/4/26
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> greeting(String key,String val) {
        return Collections.singletonMap(key, val);
    }
    @PostMapping("/post")
    public Map<String, Object> post(String key,String val) {
        return Collections.singletonMap(key, val);
    }
    @PostMapping("/post/user")
    public User postUser(User user) {
        return user;
    }
    @PostMapping("/post/json")
    public User json(@RequestBody User user) {
        return user;
    }

}

