package com.ks;

import com.ks.webservice.HelloService;
import com.ks.webservice.UserService;
import com.ks.webservice.interceptor.AuthInterceptor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.ws.Endpoint;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * HelloService服务
     * {@link AuthInterceptor} 需要签名认证
     *
     * @param bus
     * @param helloService
     * @return
     */
    @Bean
    public Endpoint endpointHello(Bus bus, HelloService helloService) {
        EndpointImpl endpoint = new EndpointImpl(bus, helloService);
        endpoint.publish("/HelloService");//接口发布在 /HelloService 目录下
        endpoint.getInInterceptors().add(new AuthInterceptor());
        return endpoint;
    }

    /**
     * UserService服务
     * @param bus
     * @param userService
     * @return
     */
    @Bean
    public Endpoint endpointUser(Bus bus, UserService userService) {
        EndpointImpl endpoint = new EndpointImpl(bus, userService);
        endpoint.publish("/UserService");//接口发布在 /UserService 目录下
//        endpoint.getInInterceptors().add(new AuthInterceptor());
        return endpoint;
    }

}
