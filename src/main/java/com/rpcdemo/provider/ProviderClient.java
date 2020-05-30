package com.rpcdemo.provider;

import com.rpcdemo.agree.http.HttpService;
import com.rpcdemo.impl.DemoServiceImpl;
import com.rpcdemo.regiter.RemoteRegister;
import com.rpcdemo.service.DemoService;

public class ProviderClient {






    public static void main(String[] args) {

        //本地注册，服务名，URL
        LocalRegister.register(DemoService.class.getName(), DemoServiceImpl.class);

        //远程注册
        RemoteRegister.register(DemoService.class.getName(),new URL("localhost",8080));

        //启动服务tomcat，http
        new HttpService().start("localhost",8080);
    }

//    public static void main(String[] args) {
//        System.out.printf(DemoService.class.getName());
//    }

}
