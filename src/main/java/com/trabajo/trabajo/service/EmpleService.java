package com.trabajo.trabajo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabajo.trabajo.entities.EmpleadosEntity;
import com.trabajo.trabajo.repositories.EmpleadosRepository;

@Service
public class EmpleService {
    @Autowired
    private EmpleadosRepository empleadosRepository;
    public void saveEmpleado(EmpleadosEntity empleado){
        empleadosRepository.save(empleado);
    }
    
    
}
