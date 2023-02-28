package com.trabajo.trabajo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

@NoArgsConstructor
@Entity
@Table(name = "empleados")

public class EmpleadosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "el campo nombre es obligatorio")
    @Size(min = 2, max = 50)
    @Column(name = "nombre", nullable = false)
    private String nombres;

    @Size(min = 1, max = 50)
    @Column(name = "apellido")
    private String apellidos;

    @NotEmpty(message = "el campo correo es obligatorio")
    @Email(message = "debe ser tipo Email")
    @Size(min = 4, max = 100)
    @Column(name = "correo", unique = true, nullable = false)
    private String correo;

    @NotNull(message = "el campo edad es obligatorio")
    @Min(value = 18, message = "la edad minima debe ser 18")
    @Column(name = "edad")
    private double edad;

    @NotNull(message = "el campo salario es obligatorio")
    @Column(name = "salario", nullable = false)
    private double salario;

    @NotNull(message = "el campo salario es obligatorio y ")
    @NotEmpty(message = "el campo cargo es obligatorio")
    @Size(min = 1, max = 50)
    @Column(name = "cargo", nullable = false)
    private String cargo;

}
