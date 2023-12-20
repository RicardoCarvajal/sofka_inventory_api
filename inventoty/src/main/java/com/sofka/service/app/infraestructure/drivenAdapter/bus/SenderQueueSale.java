package com.sofka.service.app.infraestructure.drivenAdapter.bus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.sofka.service.app.conf.RabbitConf;
import com.sofka.service.app.infraestructure.drivenAdapter.entity.Venta;
import com.sofka.service.app.infraestructure.entryPoint.dto.MovementSaleDto;

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

		if (object instanceof Venta) {

			Venta venta = ((Venta) object);
			String id = venta.getId();

			MovementSaleDto movement = MovementSaleDto.building().idObject(id).typeProcess(SALE_PROCESS)
					.description(SALE_PROCESS_DESCRIPTION).object(object).typeSale(venta.getTipo()).build();

			log.info("Enviando data a " + " cola: " + RabbitConf.QUEUE + " data: " + gson.toJson(movement));
			sender.send(Mono.just(new OutboundMessage(RabbitConf.EXCHANGE_NAME_1, RabbitConf.ROUTING_KEY_NAME_1,
					gson.toJson(movement).getBytes()))).subscribe();

		}

	}

	@Override
	public void senderQueueError(Object object) {

		MovementSaleDto movement = MovementSaleDto.building().typeProcess(SALE_PROCESS)
				.description(SALE_PROCESS_DESCRIPTION).isError(true).object(object).build();

		log.info("Enviando data a " + " cola: " + RabbitConf.QUEUE + " data: " + gson.toJson(movement));
		sender.send(Mono.just(new OutboundMessage(RabbitConf.EXCHANGE_NAME_1, RabbitConf.ROUTING_KEY_NAME_1,
				gson.toJson(movement).getBytes()))).subscribe();

	}

}
