package com.mx.templo.ManejoDeExcepciones;

import lombok.Data;

/**
 * @author josesaidolanogarcia
 */
@Data
public class EmpleadoConIdNegativoException extends RuntimeException  {

    private String mensaje;

    public EmpleadoConIdNegativoException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

}
