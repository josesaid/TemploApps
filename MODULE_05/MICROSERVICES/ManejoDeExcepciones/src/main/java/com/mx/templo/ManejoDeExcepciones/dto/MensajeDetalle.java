package com.mx.templo.ManejoDeExcepciones.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author josesaidolanogarcia
 */
@Data
public class MensajeDetalle {

    private String mensaje;
    private LocalDateTime fechaHora;
    private String comentario;

}
