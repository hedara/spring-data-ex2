package com.samples;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by edara on 8/4/16.
 */
public class Demo {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        for(String beanname:context.getBeanDefinitionNames()){
            //System.out.println(beanname);
        }
        Circle circle = ((CircleDao)context.getBean("circleDao")).getCircle(2);
        System.out.println(circle.getName());
    }
}
