package com.template.demo.factory.resolver;

import java.util.List;

import org.springframework.stereotype.Component;

import com.template.demo.document.CustomerDocument;
import com.template.demo.exception.BusinessException;
import com.template.demo.mapper.CustomerMapper;

import lombok.RequiredArgsConstructor;

// Este resolver se encarga de resolver el mapeador adecuado para un cliente específico. 
// Utiliza una lista de mapeadores disponibles y selecciona el que es compatible con el cliente dado. 
// Si no se encuentra un mapeador compatible, lanza una excepción de negocio. 
// Este resolver permite una mayor flexibilidad y adherencia al principio de responsabilidad única  SRP, 
// ya que cada mapeador tiene la responsabilidad de mapear un tipo específico de cliente.

@Component
@RequiredArgsConstructor
public class CustomerMapperResolver {

    private final List<CustomerMapper> mappers;

    public CustomerMapper resolve(
            CustomerDocument customer) {

        return mappers.stream()
                .filter(m -> m.supports(customer))
                .findFirst()
                .orElseThrow(() ->
                        new BusinessException(
                                "Mapper not found"));
    }
}
