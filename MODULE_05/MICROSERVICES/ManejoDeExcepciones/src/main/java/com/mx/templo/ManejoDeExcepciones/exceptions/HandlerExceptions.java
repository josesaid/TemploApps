package com.mx.templo.ManejoDeExcepciones.exceptions;

import com.mx.templo.ManejoDeExcepciones.EmpleadoConIdNegativoException;
import com.mx.templo.ManejoDeExcepciones.dto.EmpleadoConIdNegativoDTO;
import com.mx.templo.ManejoDeExcepciones.dto.EmpleadoNoEncontradoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author josesaidolanogarcia
 */
@ControllerAdvice
@Slf4j
public class HandlerExceptions {

    @ExceptionHandler(value = {EmpleadoNoEncontradoException.class})
    public ResponseEntity<Object> empleadoNoEncontradoHandler(EmpleadoNoEncontradoException ex){
        log.error("1.- Error: {}", ex.getMessage());
        EmpleadoNoEncontradoDTO empleadoNoEncontradoDTO = new EmpleadoNoEncontradoDTO();
        empleadoNoEncontradoDTO.setCausa(ex.getMessage());
        empleadoNoEncontradoDTO.setLineaQueFalla(123);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(empleadoNoEncontradoDTO);
    }

    @ExceptionHandler(value = {EmpleadoConIdNegativoException.class})
    public ResponseEntity<Object> empleadoConIdNegativoHandler(EmpleadoConIdNegativoException ex){
        log.error("2.- Error: {}", ex.getMessage());
        EmpleadoConIdNegativoDTO empleadoConIdNegativoDTO = new EmpleadoConIdNegativoDTO();
        empleadoConIdNegativoDTO.setComentario(ex.getMessage());
        empleadoConIdNegativoDTO.setIdNegativoInexistente(1);
        return ResponseEntity.badRequest().body(empleadoConIdNegativoDTO);
    }

}
