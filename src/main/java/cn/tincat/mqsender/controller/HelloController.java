package cn.tincat.mqsender.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/hello")
public class HelloController {

    @Value("${mqsender.index.message}")
    private String helloMessage;

    @RequestMapping("")
    public String hello(Model model) {
        model.addAttribute("msg", helloMessage);
        return "hello";
    }
}
