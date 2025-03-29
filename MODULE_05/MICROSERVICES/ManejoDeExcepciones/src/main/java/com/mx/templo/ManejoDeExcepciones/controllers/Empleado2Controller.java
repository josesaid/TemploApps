package com.mx.templo.ManejoDeExcepciones.controllers;

import com.mx.templo.ManejoDeExcepciones.EmpleadoService;
import com.mx.templo.ManejoDeExcepciones.dto.MensajeDetalle;
import com.mx.templo.ManejoDeExcepciones.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/empleados2")
public class Empleado2Controller {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/{empleadoId}")
    public ResponseEntity<MensajeDetalle> getEmpleado(@PathVariable("empleadoId") String empleadoId) {
        Empleado empleado = empleadoService.findById(empleadoId);
        MensajeDetalle mensajeDetalle = new MensajeDetalle();
        mensajeDetalle.setMensaje("Empleado encontrado fue: " + empleado.toString());
        mensajeDetalle.setFechaHora(LocalDateTime.now());
        mensajeDetalle.setComentario("ALGO");
        return new ResponseEntity<>(mensajeDetalle,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MensajeDetalle> createEmpleado(@RequestBody Empleado empleado) {
        Empleado empleadoCreado = empleadoService.save(empleado);
        MensajeDetalle mensajeDetalle = new MensajeDetalle();
        mensajeDetalle.setMensaje("Empleado creado fue: " + empleadoCreado.toString());
        mensajeDetalle.setFechaHora(LocalDateTime.now());
        mensajeDetalle.setComentario("ALGO");
        return new ResponseEntity<>(mensajeDetalle,HttpStatus.CREATED);
    }

}
