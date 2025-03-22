package com.cursomicroservicios.ioc.notification;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class SMSService implements INotificacionService {

	@Override
	public void enviarNoticacion(String destinatario, String mensaje) {
		System.out.println("Enviando SMS a " + destinatario + " con mensaje: " + mensaje);
		
		//logica para el envio del SMS
		
	}

}
