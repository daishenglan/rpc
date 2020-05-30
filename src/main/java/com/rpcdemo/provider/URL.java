package com.rpcdemo.provider;

import lombok.Data;

import java.io.Serializable;

@Data
public class URL implements Serializable {

    private String hostname;  //ip
    private Integer port;   //端口

    public URL(String hostname , Integer port){
        this.hostname = hostname;
        this.port = port;
    }

}
