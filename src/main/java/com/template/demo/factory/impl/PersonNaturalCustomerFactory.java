package com.template.demo.factory.impl;
 
import org.openapitools.model.CustomerBase;
import org.openapitools.model.CustomerType;
import org.openapitools.model.PersonNaturalCustomer; 
import org.springframework.stereotype.Component;

import com.template.demo.document.CustomerDocument;
import com.template.demo.document.PersonNaturalCustomerDocument; 
import com.template.demo.factory.AbstractCustomerFactory;

// La anotación @Component indica que esta clase es un componente de Spring, lo que permite que sea detectada y gestionada por el contenedor de Spring.
// Esta clase cumple con el patrón de diseño Factory, 
// específicamente para crear y actualizar documentos de clientes de tipo Persona Jurídica.
// Cumple con los principios SOLID, 
//  * Responsabilidad Única (SRP): crear y actualizar documentos de clientes de tipo Persona Jurídica,
//  * Principio de Sustitución de Liskov (LSP): la clase puede ser sustituida por su superclase AbstractCustomerFactory sin afectar el funcionamiento del programa,
//  * Principio de Abierto/Cerrado (OCP): la clase está abierta para extensión (puede crear y actualizar nuevos tipos de clientes) pero cerrada para modificación (no requiere cambios en el código existente para agregar nuevos tipos de clientes).

@Component
public class PersonNaturalCustomerFactory
        extends AbstractCustomerFactory {

    @Override
    public CustomerDocument create(
            CustomerBase request) {

        PersonNaturalCustomer dto =
                (PersonNaturalCustomer) request;

        PersonNaturalCustomerDocument customer =
                new PersonNaturalCustomerDocument();

        mapCommonFields(dto, customer);

        customer.setBirthDate(
                dto.getBirthDate());

        return customer;
    }

    @Override
    public void update(
            CustomerDocument customer,
            CustomerBase request) {

        PersonNaturalCustomer dto =
                (PersonNaturalCustomer) request;

        PersonNaturalCustomerDocument entity =
                (PersonNaturalCustomerDocument) customer;

        mapCommonFields(dto, entity);

        entity.setBirthDate(
                dto.getBirthDate());
    }

    @Override
    public CustomerType supports() {
        return CustomerType.PN;
    }
}
