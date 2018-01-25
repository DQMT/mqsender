package cn.tincat.mqsender.mq;

import org.springframework.amqp.core.MessageProperties;

public class DefaultMessage {
    private static MessageProperties messageProperties;
    public static MessageProperties getDefaultMessageProperties(){
        if (messageProperties == null) {
            messageProperties = new MessageProperties();
            messageProperties.setContentType(MessageProperties.CONTENT_TYPE_BYTES);
            messageProperties.setDeliveryTag(100);
            messageProperties.setRedelivered(false);
        }
        return messageProperties;
    }
}
