package com.template.demo.factory.resolver;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.openapitools.model.CustomerType;
import org.springframework.stereotype.Component;

import com.template.demo.exception.BusinessException;
import com.template.demo.factory.CustomerFactory;

// Este resolver cumple con el principio de responsabilidad única (SRP)
// al encargarse exclusivamente de resolver la fábrica adecuada para un tipo de cliente específico.
@Component
public class CustomerFactoryResolver {

// este mapeo es una forma eficiente de resolver la fábrica adecuada para un tipo de cliente específico,
// ya que permite una búsqueda rápida en tiempo constante (O(1)) en lugar de iterar sobre
// una lista de fábricas cada vez que se necesita resolver una fábrica para un tipo de cliente.
    private final Map<CustomerType, CustomerFactory> factories;

    public CustomerFactoryResolver(
            List<CustomerFactory> factoryList) {

        // mapea cada tipo de cliente a su fábrica correspondiente,
        // lo que permite una resolución rápida y eficiente de la fábrica adecuada para cada tipo de cliente.
        this.factories = factoryList.stream()
                .collect(Collectors.toMap(
                        CustomerFactory::supports,
                        Function.identity()));
    }

    // obtiene la fábrica adecuada para un tipo de cliente específico, 
    // lo que permite una creación y actualización específica para cada tipo de cliente 
    // sin acoplar el servicio a implementaciones concretas.
    public CustomerFactory resolve(
            CustomerType type) {

        CustomerFactory factory
                = factories.get(type);

        if (factory == null) {
            throw new BusinessException("Unsupported customer type: " + type);
        }

        return factory;
    }
}
