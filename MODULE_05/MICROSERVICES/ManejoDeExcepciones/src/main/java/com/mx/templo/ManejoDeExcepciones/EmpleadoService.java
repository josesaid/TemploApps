package com.mx.templo.ManejoDeExcepciones;


import com.mx.templo.ManejoDeExcepciones.entity.Empleado;
import com.mx.templo.ManejoDeExcepciones.exceptions.EmpleadoNoEncontradoException;
import com.mx.templo.ManejoDeExcepciones.repository.EmpleadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author josesaidolanogarcia
 */
@Service
@Slf4j
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Optional<Empleado> findById(String empleadoId){
        int empleadoInt = Integer.parseInt(empleadoId);
        /*if (empleado <= 0) {
            throw new EmpleadoConIdNegativoException("El id del empleado debe ser mayor a 0");
        }else if(empleado > 0 && empleado <= 10) {
            throw new EmpleadoNoEncontradoException("El empleado con el id: " + empleadoId + " no existe");
        }else{
            return "Juan Perez";
        }*/
        Optional<Empleado> empleadoOptional = Optional.ofNullable(empleadoRepository.findById(empleadoInt)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("El empleado con el id: " + empleadoId + " no existe")));
        return empleadoOptional;
    }

    public Empleado save(Empleado empleado) {
        Empleado empleadoGuardado = empleadoRepository.save(empleado);
        return empleadoGuardado;
    }

    public Empleado actualizarEmpleado(Empleado empleadoBD, Empleado empleadoUpdate) {
        empleadoBD.setNombre(empleadoUpdate.getNombre());
        empleadoBD.setApellido(empleadoUpdate.getApellido());
        empleadoBD.setEmail(empleadoUpdate.getEmail());
        empleadoBD.setActivo(empleadoUpdate.isActivo());
        Empleado empleadoActualizado = empleadoRepository.save(empleadoBD);
        log.info(this.getClass().getSimpleName() + " - Empleado actualizado en BD fue: " + empleadoBD);
        return empleadoActualizado;
    }

    public void delete(String empleadoId) {
        int empleadoInt = Integer.parseInt(empleadoId);
        //usar el findByiD para atrapar un posible empleado no encontrado.
        //Si es encontrado, borralo, pero sino, lanza la exception y pinta un log error.
        empleadoRepository.deleteById(empleadoInt);
        log.info(this.getClass().getSimpleName() + " - Empleado eliminado en BD fue: " + empleadoId);
    }

    public void deleteLogico(String empleadoId) {
        int empleadoInt = Integer.parseInt(empleadoId);
        Optional<Empleado> empleadoEncontrado = empleadoRepository.findById(empleadoInt);
        if (empleadoEncontrado.isPresent()){
            Empleado empleadoABorrar = empleadoEncontrado.get();
            empleadoABorrar.setActivo(false);
            empleadoRepository.save(empleadoABorrar);
            log.info(this.getClass().getSimpleName() + " - Empleado eliminado logico en BD fue: " + empleadoId);
        }else{
            throw new EmpleadoNoEncontradoException("El empleado con el id: " + empleadoId + " no existe");
        }
    }

}
