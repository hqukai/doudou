package com.hqukai.learning.java;


import com.hqukai.learning.java.service.TestService;
import org.joda.time.LocalTime;

/**
 * Created by hankai on 2015/12/28.
 */
public class Main {
    public static void main(String args[]) {
        System.out.println("Gradle hello");
        LocalTime currentTime = new LocalTime();
        System.out.println("now time is:" + currentTime);

        TestService testService = new TestService();
        testService.test();
    }
}
