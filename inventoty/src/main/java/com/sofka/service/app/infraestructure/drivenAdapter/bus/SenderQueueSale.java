package com.sofka.service.app.infraestructure.drivenAdapter.bus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.sofka.service.app.conf.RabbitConf;

import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Sender;

@Component("SenderQueueSale")
public class SenderQueueSale implements ISenderQueue {

	Logger log = LoggerFactory.getLogger(SenderQueueSale.class);

	@Autowired
	private Sender sender;

	@Autowired
	private Gson gson;

	private static final String SALE_PROCESS = "VENTA";

	private static final String SALE_PROCESS_DESCRIPTION = "MOVIMIENTO DE VENTAS";

	@Override
	public void senderQueueSuccess(Object object) {

		log.info("Enviando data a " + " cola: " + RabbitConf.QUEUE + " data: " + gson.toJson(object));
		sender.send(Mono.just(new OutboundMessage(RabbitConf.EXCHANGE_NAME_1, RabbitConf.ROUTING_KEY_NAME_1,
				gson.toJson(object).getBytes()))).subscribe();

	}

	@Override
	public void senderQueueError(Object object) {

		log.info("Enviando data a " + " cola: " + RabbitConf.QUEUE + " data: " + gson.toJson(object));
		sender.send(Mono.just(new OutboundMessage(RabbitConf.EXCHANGE_NAME_1, RabbitConf.ROUTING_KEY_NAME_1,
				gson.toJson(object).getBytes()))).subscribe();

	}

}
