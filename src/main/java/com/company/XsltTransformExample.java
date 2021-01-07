package com.company;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.InputStream;

public class XsltTransformExample {

    public static void main(String[] args) throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                "XsltTransformExample.xml");
        CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
        try {
            camelContext.start();
            ProducerTemplate orderProducerTemplate = camelContext.createProducerTemplate();
            InputStream catalogInputStream = new FileInputStream(ClassLoader.getSystemClassLoader()
                    .getResource("catalog.xml").getFile());

            orderProducerTemplate.sendBody("direct:xsltTransform", catalogInputStream);
        } finally {
            camelContext.stop();
        }
    }
}