package com.template.demo.factory;

import org.openapitools.model.CustomerBase;

import com.template.demo.document.CustomerDocument;

// Esta clase abstracta proporciona una implementación base para las fábricas de clientes,
// que incluye un método común para mapear los campos comunes entre CustomerBase y CustomerDocument.
// Cumple con el principio de SRP y el pilar POO de la herencia y el polimorfismo, 
// ya que permite que las fábricas específicas de cada tipo de cliente hereden esta funcionalidad común 
// y se enfoquen en la creación y actualización específica de su tipo de cliente.
public abstract class AbstractCustomerFactory
        implements CustomerFactory {

    protected void mapCommonFields(
            CustomerBase dto,
            CustomerDocument customer) {

        customer.setFullName(
                dto.getFullName());

        customer.setAge(
                dto.getAge());

        customer.setEmail(
                dto.getEmail());

        customer.setDocumentType(
                dto.getDocumentType());

        customer.setIdentificationNumber(
                dto.getIdentificationNumber());

        customer.setBonusMiles(
                dto.getBonusMiles());

        customer.setCustomerType(
                supports());
    }
}
