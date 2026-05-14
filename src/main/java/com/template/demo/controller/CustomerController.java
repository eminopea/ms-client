package com.template.demo.controller;

import java.util.List;

import org.openapitools.api.ApiApi;
import org.openapitools.model.CustomerBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.template.demo.service.ICustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// Principio de Unica Responsabilidad (SRP), esta clase tiene una única responsabilidad, 
// que es manejar las solicitudes relacionadas con los clientes. 
// No se encarga de la lógica de negocio ni de la persistencia de datos, 
// sino que delega esas responsabilidades al servicio correspondiente.
// OPENAPI: esta clase implementa la interfaz generada por OpenAPI, 
// lo que garantiza que se adhiera al contrato definido en la especificación de la API.
@RestController
public class CustomerController implements ApiApi {

    /*
     * DIP:
     * Dependemos de una abstracción
     * y no de una implementación concreta.
     */
    private final ICustomerService service;

    public CustomerController(ICustomerService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<CustomerBase>
            apiV1CustomersPost(
                    @Valid CustomerBase customerBase) {

        CustomerBase response
                = service.create(customerBase);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    public ResponseEntity<List<CustomerBase>>
            apiV1CustomersGet() {

        return ResponseEntity.ok(
                service.findAll());
    }

    @Override
    public ResponseEntity<CustomerBase>
            apiV1CustomersIdPut(
                    String id,
                    @Valid CustomerBase customerBase) {

        CustomerBase response
                = service.update(id, customerBase);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CustomerBase> apiV1CustomersDniDniGet(String dni) {

        CustomerBase cliente = service.findByDocument(dni);

        return ResponseEntity.ok(cliente);
    }

}
