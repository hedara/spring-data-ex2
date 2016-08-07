package com.samples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by edara on 8/4/16.
 */
public class Demo {

    static final Logger logger = LoggerFactory.getLogger(Demo.class);
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        for(String beanname:context.getBeanDefinitionNames()){
            //logger.info(beanname);
        }
        CircleDao circleDao = (CircleDao) context.getBean("circleDao");
        CircleDao2 circleDao2 = (CircleDao2) context.getBean("circleDao2");
        CircleDao3 circleDao3 = (CircleDao3) context.getBean("circleDao3");
        Circle circle = circleDao.getCircle(2);
        Circle circle2 = circleDao.getCircle2(2);
        Circle circle3 = circleDao.getCircle3(1);
        logger.info(circle.getName());
        logger.info(circle2.getName());
        logger.info(circle3.getName());

        // update
        boolean result = ((CircleDao)context.getBean("circleDao")).updateCircleName(1,"circle 1");

        boolean insert_result = ((CircleDao)context.getBean("circleDao")).insertCircle(4,"Circle 4");
        for(Circle c : ((CircleDao)context.getBean("circleDao")).getAllCircles()) {
            logger.info(""+c);
        }
        // using named parameter jdbc template.
        logger.info(""+circleDao2.getCircle(3));

        // using namedparameter jdbc dao support class
        logger.info(""+circleDao3.getCircle(4));

        // using hibernate dao. not working
        //HibernateCircleDao hibernateCircleDao = (HibernateCircleDao) context.getBean("hibernateCircleDao");
        //logger.info("Using hibernate dao");
        //logger.info(""+hibernateCircleDao.getCircleCount());

    }
}
