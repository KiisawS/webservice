package com.ks.webservice.impl;

import com.ks.webservice.HelloService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@WebService(
        serviceName = "helloService"
        ,
        targetNamespace = "http://webservice.ks.com"
        ,
        endpointInterface = "com.ks.webservice.HelloService"
)
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
