package com.company;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetExample {

    public static void main(String[] args) throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                "GetExample.xml");
        CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
        try {
            camelContext.start();
            ProducerTemplate orderProducerTemplate = camelContext.createProducerTemplate();

            System.out.println("GET request start!");
            orderProducerTemplate.sendBody("direct:DistributeOrderXML", "");
        } finally {
            camelContext.stop();
        }
    }
}