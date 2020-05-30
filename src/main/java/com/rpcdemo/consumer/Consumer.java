package com.rpcdemo.consumer;

import com.rpcdemo.http.HttpService;
import com.rpcdemo.proxy.ProxyFactory;
import com.rpcdemo.regiter.RemoteRegister;
import com.rpcdemo.provider.URL;
import com.rpcdemo.service.DemoService;

import java.util.List;
import java.util.Random;

public class Consumer {


    public static void main(String[] args) {

        //根据接口名，获取注册中心信息
//        List<URL> list = RemoteRegister.getProviderList(DemoService.class.getName());
//
//        //Lbalance   负载均衡   选一个服务器进行发送信息
//        URL url = loadBalance(list);

        DemoService demoService = ProxyFactory.proxy(DemoService.class);

        System.out.println(demoService.sayHello("还是不要脸"));


    }

//    public static void main(String[] args) {
//        int num = new Random().nextInt(2);
//        System.out.printf(num+"");
//    }

}
