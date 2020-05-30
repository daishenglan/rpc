package com.rpcdemo.impl;

import com.rpcdemo.service.DemoService;

public class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        return "你好：" + name;
    }
}
