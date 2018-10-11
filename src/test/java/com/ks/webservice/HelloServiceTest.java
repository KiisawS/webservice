package com.ks.webservice;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;

public class HelloServiceTest {

    @Test
    public void sayHello() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/services/HelloService?wsdl");
        // 需要密码的情况下，添加用户名和密码
        client.getOutInterceptors().add(new ClientAuthInterceptor("test","test"));

        Object[] objects = null;
        try {
            objects = client.invoke("sayHello", "sss");
            System.out.println(objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ClientAuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

        private String name;

        private String pwd;

        public ClientAuthInterceptor(String name, String pwd) {
            // 指定该拦截器在哪个阶段被激发
            super(Phase.WRITE);
            this.name = name;
            this.pwd = pwd;
        }

        @Override
        public void handleMessage(SoapMessage message) throws Fault {
            Document doc = DOMUtils.createDocument();
            Element auth = doc.createElement("auth");

            Element name = doc.createElement("name");
            name.setTextContent(this.name);

            Element pwd = doc.createElement("pwd");
            pwd.setTextContent(this.pwd);

            auth.appendChild(name);
            auth.appendChild(pwd);

            QName qName = new QName("RequestSOAPHeader");
            message.getHeaders().add(new SoapHeader(qName, auth));
        }
    }
}
