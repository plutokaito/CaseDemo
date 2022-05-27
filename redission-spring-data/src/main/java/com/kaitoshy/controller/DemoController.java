package com.kaitoshy.controller;


import com.kaitoshy.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private TaskService service;

    @RequestMapping("")
    @ResponseBody
    public String taskTest() {
       int a = service.result();
       System.out.println("调用：" + a);
       return "11";
    }
}
