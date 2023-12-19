package com.sofka.service.app.infraestructure.drivenAdapter.bus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.sofka.service.app.conf.RabbitConf;
import com.sofka.service.app.infraestructure.drivenAdapter.entity.Producto;
import com.sofka.service.app.infraestructure.entryPoint.dto.MovementProductDto;

import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Sender;

@Component("SenderQueueProduct")
public class SenderQueueProduct implements ISenderQueue {

	Logger log = LoggerFactory.getLogger(SenderQueueProduct.class);

	@Autowired
	private Sender sender;

	@Autowired
	private Gson gson;

	private static final String PRODUCT_PROCESS = "PRODUCT";

	private static final String PRODUCT_PROCESS_DESCRIPTION = "MOVIMIENTO DE ENTRADA Y SALIDA DE PRODUCTOS";

	@Override
	public void senderQueueSuccess(Object object) {

		if (object instanceof Producto) {

			Producto producto = ((Producto) object);
			String id = producto.getId();
			Integer quantity = 0;

			if (producto.getExistencia() != null && producto.getExistencia() != 0) {
				quantity = producto.getExistencia();
			}

			MovementProductDto movement = MovementProductDto.building().idObject(id).typeProcess(PRODUCT_PROCESS)
					.description(PRODUCT_PROCESS_DESCRIPTION).object(object).quantity(quantity).build();

			log.info("Enviando data a " + " cola: " + RabbitConf.QUEUE + " data: " + gson.toJson(movement));
			sender.send(Mono.just(new OutboundMessage(RabbitConf.EXCHANGE_NAME_1, RabbitConf.ROUTING_KEY_NAME_1,
					gson.toJson(movement).getBytes()))).subscribe();

		}

	}

	@Override
	public void senderQueueError(Object object) {
		MovementProductDto movement = MovementProductDto.building().typeProcess(PRODUCT_PROCESS)
				.description(PRODUCT_PROCESS_DESCRIPTION).isError(true).object(object).build();

		log.info("Enviando data a " + " cola: " + RabbitConf.QUEUE + " data: " + gson.toJson(movement));
		sender.send(Mono.just(new OutboundMessage(RabbitConf.EXCHANGE_NAME_1, RabbitConf.ROUTING_KEY_NAME_1,
				gson.toJson(movement).getBytes()))).subscribe();

	}

}
