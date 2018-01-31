package cn.tincat.mqsender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/apiTest")
public class ApiTestController {

    @RequestMapping("")
    public String hello(Model model) {
        model.addAttribute("msg", "Hello World . This is Api Tester! ");
        return "api_test";
    }

}
