package com.sofka.service.app.conf;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Sender;
import reactor.rabbitmq.SenderOptions;

@Configuration
public class RabbitConf {

	Logger logger = LoggerFactory.getLogger(RabbitConf.class);

	public static final String QUEUEP = "sofkaApi-traicing-product";
	public static final String QUEUES = "sofkaApi-traicing-sale";

	public static final String EXCHANGE_NAME_1 = "sofkaApi-product-exchange";
	public static final String ROUTING_KEY_NAME_1 = "sofkaApi.product.routing.key";

	public static final String EXCHANGE_NAME_2 = "sofkaApi-sale-exchange";
	public static final String ROUTING_KEY_NAME_2 = "sofkaApi.dale.routing.key";

	@Value("${app.rabbit.url}")
	public String uri_name;

	@Bean
	public AmqpAdmin amqpAdmin() {
		logger.info("Conexion RABBITMQ SOFKAAPI: " + uri_name);

		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(URI.create(uri_name));
		var amqpAdmin = new RabbitAdmin(connectionFactory);

		var exchangep = new TopicExchange(EXCHANGE_NAME_1);
		var queuep = new Queue(QUEUEP, true, false, false);

		var exchanges = new TopicExchange(EXCHANGE_NAME_2);
		var queues = new Queue(QUEUES, true, false, false);

		amqpAdmin.declareExchange(exchangep);
		amqpAdmin.declareQueue(queuep);

		amqpAdmin.declareExchange(exchanges);
		amqpAdmin.declareQueue(queues);

		amqpAdmin.declareBinding(BindingBuilder.bind(queuep).to(exchangep).with(ROUTING_KEY_NAME_1));
		amqpAdmin.declareBinding(BindingBuilder.bind(queues).to(exchanges).with(ROUTING_KEY_NAME_2));

		return amqpAdmin;
	}

	@Bean
	public Mono<Connection> connMono() throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		logger.info("Conexion RABBITMQ SOFKAAPI: " + uri_name);
		connectionFactory.setUri(uri_name);
		connectionFactory.useNio();
		return Mono.fromCallable(() -> connectionFactory.newConnection());
	}

	@Bean
	public SenderOptions senderOptions(Mono<Connection> connMono) {
		return new SenderOptions().connectionMono(connMono).resourceManagementScheduler(Schedulers.boundedElastic());
	}

	@Bean
	public Sender sender(SenderOptions senderOptions) {
		return RabbitFlux.createSender(senderOptions);
	}

}
