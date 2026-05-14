package com.template.demo.service;

import java.util.List;

import org.openapitools.model.CustomerBase;

/**
 * Interfaz de servicio para la gestión de clientes. Principio de Abstracción
 * (Abstraction), esta interfaz define los métodos necesarios para la gestión de
 * clientes, sin exponer los detalles de implementación. *****
 */
public interface ICustomerService {

    CustomerBase create(CustomerBase request);

    CustomerBase update(
            String id,
            CustomerBase request);

    List<CustomerBase> findAll();
    
    CustomerBase findByDocument(String document);

}
