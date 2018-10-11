package com.ks.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * http://localhost:8080/services/HelloService?wsdl
 */
@WebService(targetNamespace = "http://webservice.ks.com")
public interface HelloService {

    @WebMethod
    String sayHello(@WebParam(name = "name") String name);
}
