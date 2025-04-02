package com.mx.templo.ManejoDeExcepciones.controllers;

import com.mx.templo.ManejoDeExcepciones.EmpleadoService;
import com.mx.templo.ManejoDeExcepciones.dto.MensajeDetalle;
import com.mx.templo.ManejoDeExcepciones.entity.Empleado;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados2")
@Slf4j
public class Empleado2Controller {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/{empleadoId}")
    public ResponseEntity<MensajeDetalle> getEmpleado(@PathVariable("empleadoId") String empleadoId) {
        MensajeDetalle mensajeDetalle = new MensajeDetalle();
        HttpStatus status = HttpStatus.NOT_FOUND;
        Optional<Empleado> empleado = empleadoService.findById(empleadoId);
        if (empleado.isPresent()) {
            Empleado empleadoEncontrado = empleado.get();
            mensajeDetalle.setMensaje("Empleado encontrado fue: " + empleadoEncontrado );
            mensajeDetalle.setFechaHora(LocalDateTime.now());
            mensajeDetalle.setComentario("ALGO");
            status = HttpStatus.OK;
        }else{
            mensajeDetalle.setMensaje("El empleado con el id: " + empleadoId + " no existe");
            mensajeDetalle.setFechaHora(LocalDateTime.now());
        }
        return new ResponseEntity<>(mensajeDetalle,status);
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

    @PutMapping("/{empleadoId}")
    public ResponseEntity<MensajeDetalle> updateEmpleado(@RequestBody Empleado empleadoUpdate, @PathVariable("empleadoId") String empleadoId) {
        Optional<Empleado> empleadoEncontradoOptional = empleadoService.findById(empleadoId);
        MensajeDetalle mensajeDetalle = new MensajeDetalle();

        if (empleadoEncontradoOptional.isPresent()) {
            Empleado empleadoBD = empleadoEncontradoOptional.get();
            log.info(this.getClass().getSimpleName() + " - El Empleado encontrado es: " + empleadoBD);
            Empleado actualizado = empleadoService.actualizarEmpleado(empleadoBD, empleadoUpdate);

            mensajeDetalle.setMensaje("Empleado actualizado fue: " + actualizado);
            mensajeDetalle.setFechaHora(LocalDateTime.now());
            mensajeDetalle.setComentario("ALGO");
            log.info(this.getClass().getSimpleName() + "Actualización del empleado completa exitosa!");
        }else{
            log.warn(this.getClass().getSimpleName() + " - El Empleado no fue encontrado");
        }
        return new ResponseEntity<>(mensajeDetalle,HttpStatus.OK);
    }

    @DeleteMapping("/{empleadoId}")
    public ResponseEntity<MensajeDetalle> borradoFisicoEmpleado(@PathVariable("empleadoId") String empleadoId) {
        MensajeDetalle mensajeDetalle = new MensajeDetalle();
        mensajeDetalle.setFechaHora(LocalDateTime.now());
        mensajeDetalle.setComentario("ALGO");
        mensajeDetalle.setMensaje("Empleado borrado");

        empleadoService.delete(empleadoId);

        log.info(this.getClass().getSimpleName() + " - Empleado eliminado: " + empleadoId);
        return new ResponseEntity<>(mensajeDetalle,HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/borrado_logico/{empleadoId}")
    public ResponseEntity<MensajeDetalle> borradoLogicoEmpleado(@PathVariable("empleadoId") String empleadoId) {
        MensajeDetalle mensajeDetalle = new MensajeDetalle();
        mensajeDetalle.setFechaHora(LocalDateTime.now());
        mensajeDetalle.setComentario("ALGO");
        mensajeDetalle.setMensaje("Empleado borrado logico");

        empleadoService.deleteLogico(empleadoId);

        log.info(this.getClass().getSimpleName() + " - Empleado eliminado: " + empleadoId);
        return new ResponseEntity<>(mensajeDetalle,HttpStatus.NO_CONTENT);
    }

}
