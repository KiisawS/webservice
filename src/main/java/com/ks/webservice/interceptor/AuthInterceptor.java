package com.ks.webservice.interceptor;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.soap.SOAPException;
import java.util.List;

public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    public AuthInterceptor() {
        // 指定该拦截器在哪个阶段被激发
        super(Phase.INVOKE);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {

        List<Header> headers = message.getHeaders();

        if (null == headers || headers.isEmpty()) {
            throw new Fault(new SOAPException("无认证信息"));
        }

        headers.stream().forEach(header -> {
            SoapHeader soapHeader = (SoapHeader) header;
            Element element = (Element) soapHeader.getObject();
            NodeList nameNodes = element.getElementsByTagName("name");
            NodeList pwdNodes = element.getElementsByTagName("pwd");

            if (nameNodes != null && pwdNodes != null) {
                if (nameNodes.item(0).getTextContent().equals("test")
                        && pwdNodes.item(0).getTextContent().equals("test")) {
                    System.out.println("认证通过");
                    return;
                }
            }
            throw new Fault(new SOAPException("认证失败"));
        });
    }
}
