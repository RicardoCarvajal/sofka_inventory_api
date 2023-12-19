package com.sofka.service.app.infraestructure.drivenAdapter.bus;

public interface ISenderQueue {

	public void senderQueueSuccess(Object object);

	public void senderQueueError(Object object);

}
