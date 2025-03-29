package com.mx.templo.ManejoDeExcepciones.controllers;

import com.mx.templo.ManejoDeExcepciones.EmpleadoConIdNegativoException;
import com.mx.templo.ManejoDeExcepciones.EmpleadoService;
import com.mx.templo.ManejoDeExcepciones.dto.EmpleadoConIdNegativoDTO;
import com.mx.templo.ManejoDeExcepciones.dto.EmpleadoNoEncontradoDTO;
import com.mx.templo.ManejoDeExcepciones.dto.MensajeDetalle;
import com.mx.templo.ManejoDeExcepciones.entity.Empleado;
import com.mx.templo.ManejoDeExcepciones.exceptions.EmpleadoNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;


    @GetMapping("/{empleadoId}")
    public MensajeDetalle getEmpleado(@PathVariable("empleadoId") String empleadoId) {
        try{
            //vamos a la BD y encontramos al  empleado con el id:  + empleadoID;
            MensajeDetalle mensajeDetalle = new MensajeDetalle();
            Empleado empleado = empleadoService.findById(empleadoId);
            mensajeDetalle.setMensaje("Empleado encontrado fue: " + empleado.toString());
            mensajeDetalle.setFechaHora(LocalDateTime.now());
            mensajeDetalle.setComentario("ALGO");
            return mensajeDetalle;
        }catch (EmpleadoNoEncontradoException e){
            EmpleadoNoEncontradoDTO empleadoNoEncontradoDTO = new EmpleadoNoEncontradoDTO();
            empleadoNoEncontradoDTO.setMensaje(e.getMessage());
            empleadoNoEncontradoDTO.setFechaHora(LocalDateTime.now());
            empleadoNoEncontradoDTO.setComentario("ALGO");
            empleadoNoEncontradoDTO.setCausa("Me quisieron meter gol con un numero inesperado");
            empleadoNoEncontradoDTO.setLineaQueFalla(5);
            return empleadoNoEncontradoDTO;
        }catch (EmpleadoConIdNegativoException e){
            EmpleadoConIdNegativoDTO empleadoConIdNegativoDTO = new EmpleadoConIdNegativoDTO();
            empleadoConIdNegativoDTO.setMensaje(e.getMessage());
            empleadoConIdNegativoDTO.setFechaHora(LocalDateTime.now());
            empleadoConIdNegativoDTO.setComentario("ALGO");
            empleadoConIdNegativoDTO.setIdNegativoInexistente(Integer.parseInt(empleadoId));
            return empleadoConIdNegativoDTO;
        }
    }

}
