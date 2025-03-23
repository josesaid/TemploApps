package com.cursomicroservicios.ioc.dto;

import lombok.Data;

@Data
public class UsuarioBorrado {
    private Long id;
    private String nombre;
    private String status;
}
