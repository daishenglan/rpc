package com.rpcdemo.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalRegister {

    //本地注册
    private static Map<String,Class> map = new ConcurrentHashMap<String, Class>();

    //存入本地
    public static void register(String interfaceName,Class implClass){
        map.put(interfaceName,implClass);
    }

    public static Class getInterfaceName(String interfaceName){
        return map.get(interfaceName);

    }


}
