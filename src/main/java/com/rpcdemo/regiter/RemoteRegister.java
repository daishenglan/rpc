package com.rpcdemo.regiter;

import com.rpcdemo.provider.URL;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RemoteRegister {
    private static Map<String, List<URL>> map = new ConcurrentHashMap<String, List<URL>>();

    public static void register(String interfaceName,URL url){
        if(null != map && map.size() > 0){
            List<URL> list = map.get(interfaceName);
            list.add(url);
        }else {
            List<URL> newlist = new ArrayList<URL>();
            newlist.add(url);
            map.put(interfaceName,newlist);
        }
        //模拟注册服务中心，可以使用数据库，但是嫌麻烦，直接写入文件
        saveFile();

    }

    public static List<URL> getProviderList(String interfaceName){
        Map<String, List<URL>> map = new ConcurrentHashMap<String, List<URL>>();

        try {
            FileInputStream fis = new FileInputStream("d:/text.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (Map) ois.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map.get(interfaceName);
    }

    public static void saveFile(){
        File file = new File("d:/text.txt");
        try {
            FileOutputStream fis = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            oos.writeObject(map);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
