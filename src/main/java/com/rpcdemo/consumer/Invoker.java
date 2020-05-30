package com.rpcdemo.consumer;

import lombok.Data;

import java.io.Serializable;

@Data
public class Invoker implements Serializable {

    private String interfaceName;

    private String method;

    //参数类型列表
    private Class[] paramList;

    private Object[] paraValues;

}
