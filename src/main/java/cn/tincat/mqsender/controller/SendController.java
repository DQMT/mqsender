package cn.tincat.mqsender.controller;

import cn.tincat.mqsender.mq.MQSender;
import cn.tincat.mqsender.util.URLUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("/sendmq")
public class SendController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MQSender mqSender;

    @Value("${mqsender.queue.name}")
    private String queueName;

    @Value("${mqsender.send.times}")
    private String sendTimes;

    @Value("${mqsender.http.cookies}")
    private String cookies;

    @Value("${mqsender.http.remote.address}")
    private String remoteAddress;

    private String session;

    private void put(JSONObject jsonObject, String key, String value) {
        if (value != null && "".equals(value)) {
            jsonObject.put(key, value);
        }
    }

    public void addJson(JSONObject jsonObject) {
        put(jsonObject, "cookies", cookies);
        put(jsonObject, "remoteAddress", remoteAddress);
        put(jsonObject,"session",session);
    }

    @RequestMapping("")
    public void send(HttpServletRequest request, HttpServletResponse response){
        String method = request.getParameter("method");
        String url = request.getParameter("url");
        String body = request.getParameter("body");
        String deliveryInfo = request.getParameter("deliveryInfo");
        String host = request.getParameter("host");
        String port = request.getParameter("port");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String queue = request.getParameter("queue");
        if ("".equals(queue)) {
            queue = queueName;
        }
        ConnectionFactory connectionFactory = null;
        if (!"".equals(host) && !"".equals(port)  && !"".equals(username)  && !"".equals(password) ) {
             connectionFactory = mqSender.getConnectionFactory(host, Integer.valueOf(port), username, password);
        }

        JSONObject jsonObject = JSON.parseObject("{}");
        jsonObject.put("url", url);
        jsonObject.put("method", method);
        jsonObject.put("body", JSONObject.parseObject(body));
        Map map = URLUtil.getRequestMap(url);
        jsonObject.put("query", JSONObject.parseObject(JSON.toJSONString(map)));
        addJson(jsonObject);
        Integer times = Integer.valueOf(sendTimes);
        String msg = jsonObject.toJSONString();
        for (int i = 0; i < times; i++) {
            if (connectionFactory == null) {
                mqSender.send(queue, msg);
            }else {
                mqSender.send(connectionFactory,queue, msg);
            }

            logger.warn(">>>>>> send MQ massage to " + queueName + " <<<<<<");
            logger.warn(">>>>>> massage : " + msg + " <<<<<<");
        }
    }

}
