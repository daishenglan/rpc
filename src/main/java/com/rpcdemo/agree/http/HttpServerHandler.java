package com.rpcdemo.agree.http;

import com.rpcdemo.consumer.Invoker;
import com.rpcdemo.provider.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler{

    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        //接收请求，返回结果
        try {
            InputStream inputStream = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Invoker invoker = (Invoker) ois.readObject();

            //处理请求
            Class cls = LocalRegister.getInterfaceName(invoker.getInterfaceName());
            Method method = cls.getMethod(invoker.getMethod(),invoker.getParamList());
            String s = (String) method.invoke(cls.newInstance(),invoker.getParaValues());

            //返回结果
//            OutputStream outputStream = resp.getOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
//            oos.writeObject(s);
//            oos.flush();
//            oos.close();
//            outputStream.close();
//            inputStream.close();
            IOUtils.write(s,resp.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
