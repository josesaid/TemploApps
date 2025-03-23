package com.cursomicroservicios.ioc.notification;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmailService implements INotificacionService {

	@Override
	public void enviarNoticacion(String destinatario, String mensaje) {
		System.out.println("Enviando email a " + destinatario + " con mensaje: " + mensaje);
		
		//logica para el envio del email
		
	}

}
