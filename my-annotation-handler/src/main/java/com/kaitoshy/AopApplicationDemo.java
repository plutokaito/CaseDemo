package com.kaitoshy;

import com.kaitoshy.core.ComponentScan;
import com.kaitoshy.service.EchoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan(packages = {"com.kaitoshy.service", "com.kaitoshy.service2"})
public class AopApplicationDemo implements CommandLineRunner {

    @Autowired
    private EchoServer echoServer;

    public static void main(String[] args) {
        SpringApplication.run(AopApplicationDemo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(echoServer.assembleString());
    }
}
