package com.mx.templo.ManejoDeExcepciones.exceptions;

import lombok.Data;

/**
 * @author josesaidolanogarcia
 */
@Data

public class EmpleadoNoEncontradoException extends RuntimeException{

    private String mensaje;

    public EmpleadoNoEncontradoException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

}
