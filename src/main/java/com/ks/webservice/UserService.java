package com.ks.webservice;

import com.ks.webservice.resp.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.text.ParseException;
import java.util.List;

/**
 * http://localhost:8080/services/UserService?wsdl
 */
@WebService(targetNamespace = "http://webservice.ks.com")
public interface UserService {

    @WebMethod
    User getDefUser() throws ParseException;

    @WebMethod
    List<User> getList(@WebParam(name="size") Integer size);
}
