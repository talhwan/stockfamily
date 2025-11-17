package com.thc.sprbasic2025.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    /*@RequestMapping("/test")
    public String test(){
        return "test";
    }
    @ResponseBody
    @RequestMapping("/test1")
    public String test1(){
        return "test1";
    }

    @RequestMapping("/index") //
    @ResponseBody //페이지를 리턴해줘야 하는데, JSON 형태로 리턴해주기 위한 코드
    public String index(){
        return "11222333";
    }
    @RequestMapping("/abc") //
    @ResponseBody //페이지를 리턴해줘야 하는데, JSON 형태로 리턴해주기 위한 코드
    public Map<String, Object> abc(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("11", "22222222222222222222222222222222222222");
        return map;
    }

    //일반 컨트롤러 이고, 페이지를 리턴해줄꺼야!!
    @RequestMapping("/page1") //
    public String page(){
        return "page1";
    }
*/
}
