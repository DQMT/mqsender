package cn.tincat.mqsender;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MqsenderApplication {

	@Value("${mqsender.queue.x.max.length}")
	private String xMaxLength;

	@Value("${mqsender.queue.x.max.length.bytes}")
	private String xMaxLengthBytes;

	@Value("${mqsender.queue.prefetchCount}")
	private String prefetchCount;

	@Value("${mqsender.queue.name}")
	private String queueName;

	@Value("${mqsender.queue.durable}")
	private boolean durable;

	@Value("${mqsender.queue.exclusive}")
	private boolean exclusive;

	@Value("${mqsender.queue.}")
	private boolean autoDelete;

	@Bean
	public Queue testQueue(){
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-max-length", Integer.valueOf(xMaxLength).intValue());
		arguments.put("x-max-length-bytes", Integer.valueOf(xMaxLengthBytes).intValue());
		arguments.put("prefetchCount", Integer.valueOf(prefetchCount).intValue());
		return new Queue(queueName, durable, exclusive, autoDelete, arguments);
	}

	public static void main(String[] args) {
		SpringApplication.run(MqsenderApplication.class, args);
	}

}
