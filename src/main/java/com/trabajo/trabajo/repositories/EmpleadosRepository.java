package com.trabajo.trabajo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabajo.trabajo.entities.EmpleadosEntity;

public interface EmpleadosRepository extends JpaRepository<EmpleadosEntity,Integer> {
    
}
