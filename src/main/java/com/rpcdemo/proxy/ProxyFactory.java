package com.rpcdemo.proxy;

import com.rpcdemo.consumer.ConsumerClient;
import com.rpcdemo.consumer.Invoker;
import com.rpcdemo.provider.URL;
import com.rpcdemo.regiter.RemoteRegister;
import com.rpcdemo.service.DemoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Random;

public class ProxyFactory {

    public static <T>  T proxy(final Class cls){
        //这里有个注意点，就是第二个参数，是需要得到接口的所有方法，如果使用cls.getInterfaces()是会报类型转换出错
        //cls.getInterfaces()是取实现该接口
         return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invoker invoker = new Invoker();
                invoker.setInterfaceName(cls.getName());
                invoker.setMethod(method.getName());
                invoker.setParamList(method.getParameterTypes());
                invoker.setParaValues(args);
                ConsumerClient consumerClient = new ConsumerClient();


                String s = consumerClient.send(loadBalance(cls.getName()).getHostname(),
                        loadBalance(cls.getName()).getPort(),invoker);
                return s;
            }
        });
    }

    public static URL loadBalance(String interfaceName){
        //随机策略，这里是从注册中心获取的信息（文件里面）
        int num = new Random().nextInt(RemoteRegister.getProviderList(interfaceName).size());
        return RemoteRegister.getProviderList(interfaceName).get(num);
    }

//    public static void main(String[] args) {
////        System.out.println(DemoService.class.getInterfaces());
////
////        System.out.println(DemoService.class);
////    }

}
