package com.kaitoshy.controller;


import com.kaitoshy.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private TaskService service;

    @RequestMapping("")
    @ResponseBody
    public Map<Integer, Object> taskTest() {
       int a = service.result();
       System.out.println("调用：" + a);
       Map<Integer, Object> objectObjectMap = new HashMap<Integer, Object>() {{
           put(0, 111);
           put(333, 222);
       }};
       return objectObjectMap;
    }
}
