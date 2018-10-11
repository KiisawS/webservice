package com.ks.webservice;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;

import java.util.List;

public class UserServiceTest {

    @Test
    public void getDefUser() {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/services/UserService?wsdl");
        Object[] objects = null;
        try {
            objects = client.invoke("getDefUser");
            System.out.println(objects[0]);
            //这里User需要和方法放在同一目录下，要不然cast异常
            User user = (User) objects[0];
            System.out.println(user.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getList() {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/services/UserService?wsdl");
        Object[] objects = null;
        try {
            objects = client.invoke("getList",10);
            System.out.println(objects[0]);
            System.out.println(objects.length);
            List<User> users = (List<User>) objects[0];
            users.stream().forEach(user -> System.out.println(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
