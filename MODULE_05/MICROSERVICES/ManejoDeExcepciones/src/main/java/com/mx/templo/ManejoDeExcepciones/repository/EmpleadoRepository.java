package com.mx.templo.ManejoDeExcepciones.repository;


import com.mx.templo.ManejoDeExcepciones.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author josesaidolanogarcia
 */
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    Empleado findByNombre(String nombre);
    Empleado findByApellido(String apellido);
    Empleado findByNombreAndApellido(String nombre, String apellido);
    boolean existsByNombre(String nombre);
    boolean existsByApellido(String apellido);
    boolean existsByNombreAndApellido(String nombre, String apellido);
    boolean existsById(int id);
    void deleteById(int id);
    void deleteByNombre(String nombre);
    void deleteByApellido(String apellido);
    void deleteByNombreAndApellido(String nombre, String apellido);

}
