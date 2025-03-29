package com.mx.templo.ManejoDeExcepciones.dto;

import lombok.Data;

/**
 * @author josesaidolanogarcia
 */
@Data
public class EmpleadoNoEncontradoDTO extends MensajeDetalle {

    private String causa;
    private int lineaQueFalla;
}
