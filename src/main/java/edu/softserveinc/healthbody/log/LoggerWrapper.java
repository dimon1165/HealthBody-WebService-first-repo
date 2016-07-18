package edu.softserveinc.healthbody.log;

import org.slf4j.LoggerFactory;

public class LoggerWrapper {
	
    public static void debug(Class<?> someclass, String msg){
        LoggerFactory.getLogger(someclass).debug(msg);
    }
    public static void info(Class<?> someclass, String msg){
        LoggerFactory.getLogger(someclass).info(msg);
    }
    public static void error(Class<?> someclass, String msg){
        LoggerFactory.getLogger(someclass).error(msg);
    }
}