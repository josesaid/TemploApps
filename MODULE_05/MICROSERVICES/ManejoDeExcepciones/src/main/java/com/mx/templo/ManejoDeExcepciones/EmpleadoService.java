package com.mx.templo.ManejoDeExcepciones;

import com.mx.templo.ManejoDeExcepciones.entity.Empleado;
import com.mx.templo.ManejoDeExcepciones.exceptions.EmpleadoNoEncontradoException;
import com.mx.templo.ManejoDeExcepciones.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author josesaidolanogarcia
 */
@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Empleado findById(String empleadoId){
        int empleadoInt = Integer.parseInt(empleadoId);
        /*if (empleado <= 0) {
            throw new EmpleadoConIdNegativoException("El id del empleado debe ser mayor a 0");
        }else if(empleado > 0 && empleado <= 10) {
            throw new EmpleadoNoEncontradoException("El empleado con el id: " + empleadoId + " no existe");
        }else{
            return "Juan Perez";
        }*/
        Empleado empleado = empleadoRepository.findById(empleadoInt)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("El empleado con el id: " + empleadoId + " no existe"));
        return empleado;
    }

    public Empleado save(Empleado empleado) {
        Empleado empleadoGuardado = empleadoRepository.save(empleado);
        return empleadoGuardado;
    }
}
