package com.cursomicroservicios.ioc.notification;

public interface INotificacionService {

	void enviarNoticacion(String destinatario, String mensaje);
}
