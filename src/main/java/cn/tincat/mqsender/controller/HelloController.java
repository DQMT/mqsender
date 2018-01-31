package cn.tincat.mqsender.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/hello")
public class HelloController {

    @Value("${mqsender.queue.name}")
    private String queueName;

    @Value("${mqsender.index.message}")
    private String helloMessage;

    @Value("${spring.rabbitmq.host}")
    private String rabbitmqHost;

    @Value("${spring.rabbitmq.port}")
    private String rabbitmqPort;

    @Value("${spring.rabbitmq.virtual-host}")
    private String rabbitmqVirtualHost;

    @RequestMapping("")
    public String hello(Model model) {
        String msg = helloMessage + "   default mq = " + rabbitmqHost + ":" + rabbitmqPort + rabbitmqVirtualHost + "    default queue=" + queueName;
        model.addAttribute("msg", msg);
        return "hello";
    }
}
