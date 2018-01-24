package cn.tincat.mqsender.mq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ConnectionFactory getConnectionFactory(String host, int port, String username, String password) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    public void send(ConnectionFactory connectionFactory,String mq, String msg) {
        RabbitTemplate newRabbitTemplate = new RabbitTemplate(connectionFactory);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        newRabbitTemplate.convertAndSend(null, mq, msg, correlationData);
    }

    public void send(String mq, String msg) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend(null, mq, msg, correlationData);
    }
}
