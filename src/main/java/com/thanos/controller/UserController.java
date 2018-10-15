package com.thanos.controller;

import com.thanos.service.O2OCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Thanos Yu
 * @date 10/9/2018 11:49 AM
 */
@Controller
@RequestMapping("/test")//Contoller下所有接口统一入口
public class UserController {

    @Autowired
    private O2OCategoryService categoryService;

    //映射一个action
    @RequestMapping("/user")
    @ResponseBody
    public String getCategories() {
        return "ok";
    }
}
