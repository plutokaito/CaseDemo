package com.kaitoshy;

import com.kaitoshy.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;

/*
@SpringBootApplication
public class SpringDataDemo implements CommandLineRunner {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private TaskService service;


    public static void main(String[] args) {
        SpringApplication.run(SpringDataDemo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "bbb");
        String xx = stringRedisTemplate.opsForValue().get("aaa");
        System.out.println("aaa : " + xx);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "shuai");
        map.put("age", "32");
        stringRedisTemplate.opsForHash().putAll("user_1", map);
        Map<Object, Object> getMap = stringRedisTemplate.opsForHash().entries("user_1");
        getMap.forEach((key, value) -> System.out.println("key:" + key + "value:" + value));


        for (int i = 0; i < 10; i++) {
            System.out.println("调啊调:" + service.result());
        }
    }
}
*/

