package com.rpcdemo.consumer;



import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ConsumerClient {

    public String send(String hostname ,Integer port, Invoker invoker) {

        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            //建立远程连接
            URL url = new URL("http", hostname, port, "/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            //开始传递信息
            outputStream = connection.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(invoker);
            objectOutputStream.flush();

            //接收信息

            return IOUtils.toString(connection.getInputStream());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert objectOutputStream != null;
                objectOutputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;

    }

}
