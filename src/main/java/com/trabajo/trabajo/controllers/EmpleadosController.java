package com.trabajo.trabajo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trabajo.trabajo.entities.EmpleadosEntity;
import com.trabajo.trabajo.helpers.ApiResponse;
import com.trabajo.trabajo.helpers.SetResponse;
import com.trabajo.trabajo.helpers.ValidacionCampos;
import com.trabajo.trabajo.repositories.EmpleadosRepository;
import com.trabajo.trabajo.service.EmpleService;

@RestController
@CrossOrigin(origins = "*")
public class EmpleadosController {

    @Autowired
    private EmpleService empleadoService;

    @Autowired
    EmpleadosRepository empleadosRepository;

    @Autowired
    private SetResponse serviceResponse;

    @Autowired
    private ValidacionCampos validacionCampos;

    @GetMapping("/empleados")

    public ResponseEntity<?> employeesList() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<EmpleadosEntity> empleados = empleadosRepository.findAll();
            response = serviceResponse.setApiResponse(new ApiResponse<>(true, "lista de empleados", empleados));
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException e) {
            response = serviceResponse.setApiResponse(new ApiResponse<>(false, "error al obtener lista de empleados"
                    + e.getMessage() + "" + e.getMostSpecificCause().getMessage(), ""));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<?> findEmployeesById(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            EmpleadosEntity empleado = empleadosRepository.findById(id).orElse(null);
            if (empleado == null) {
                response = serviceResponse.setApiResponse(new ApiResponse<>(false, "empleado no encontrado", ""));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                response = serviceResponse.setApiResponse(new ApiResponse<>(true, "empleado encontrado", empleado));
                return new ResponseEntity<>(response, HttpStatus.OK);

            }
        } catch (DataAccessException e) {
            response = serviceResponse.setApiResponse(new ApiResponse<>(false, "error al obtener al empleado"
                    + e.getMessage() + "" + e.getMostSpecificCause().getMessage(), ""));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/empleados")
    public ResponseEntity<?> saveEmployees(@Valid @RequestBody EmpleadosEntity empleado, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors())
            return validacionCampos.validFields(result);

        try {
            empleadoService.saveEmpleado(empleado);
            response = serviceResponse
                    .setApiResponse(new ApiResponse<>(true, "el empleado se a guardado exitosamente", empleado));
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (DataAccessException e) {
            response = serviceResponse.setApiResponse(new ApiResponse<>(false, "error al guardar al empleado"
                    + e.getMessage() + "" + e.getMostSpecificCause().getMessage(), ""));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<?> updateEmployees(@Valid @RequestBody EmpleadosEntity empleado, BindingResult result,
            @PathVariable Integer id) {

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors())
            return validacionCampos.validFields(result);

        try {
            EmpleadosEntity empleadoEncontrado = empleadosRepository.findById(id).orElse(null);
            if (empleadoEncontrado == null) {
                response = serviceResponse.setApiResponse(new ApiResponse<>(false, "empleado no encontrado", ""));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            if (empleado.getApellidos() != null) {
                empleadoEncontrado.setApellidos(empleado.getApellidos());
            }

            empleadoEncontrado.setNombres(empleado.getNombres());
            empleadoEncontrado.setCorreo(empleado.getCorreo());
            empleadoEncontrado.setEdad(empleado.getEdad());
            empleadoEncontrado.setSalario(empleado.getSalario());
            empleadoEncontrado.setCargo(empleado.getCargo());
            empleadosRepository.save(empleadoEncontrado);
            response = serviceResponse
                    .setApiResponse(
                            new ApiResponse<>(true, "el empleado se a actualizado exitosamente", empleadoEncontrado));
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException e) {
            response = serviceResponse.setApiResponse(new ApiResponse<>(false, "error al actualizar al empleado"
                    + e.getMessage() + "" + e.getMostSpecificCause().getMessage(), ""));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<?> deleteEmployees(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            EmpleadosEntity empleadoEncontrado = empleadosRepository.findById(id).orElse(null);
            if (empleadoEncontrado == null) {
                response = serviceResponse.setApiResponse(new ApiResponse<>(false, "empleado no encontrado", ""));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            empleadosRepository.delete(empleadoEncontrado);
            response = serviceResponse
                    .setApiResponse(new ApiResponse<>(true, "el empleado se a eliminado exitosamente", ""));
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException e) {
            response = serviceResponse.setApiResponse(new ApiResponse<>(false, "error al eliminar al empleado"
                    + e.getMessage() + "" + e.getMostSpecificCause().getMessage(), ""));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
