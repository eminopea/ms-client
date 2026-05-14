package com.template.demo.service.impl;

import java.util.List;

import org.openapitools.model.CustomerBase;
import org.openapitools.model.CustomerType;
import org.openapitools.model.DocumentType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.template.demo.document.CustomerDocument;
import com.template.demo.exception.BusinessException;
import com.template.demo.factory.CustomerFactory;
import com.template.demo.factory.resolver.CustomerFactoryResolver;
import com.template.demo.factory.resolver.CustomerMapperResolver;
import com.template.demo.factory.resolver.DocumentValidatorResolver;
import com.template.demo.repository.CustomerRepository;
import com.template.demo.service.ICustomerService;
import com.template.demo.util.Constants;
import com.template.demo.util.CustomerRequestUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl
        implements ICustomerService {

    // Persistencia de datos: Utilizamos un repositorio para interactuar con la base
    // de datos y realizar operaciones CRUD sobre los clientes.
    private final CustomerRepository customerRepository;

    // Se opta por construir un resolver para las fábricas de clientes, lo que permite una mayor flexibilidad y modularidad
    //  en la creación de objetos de cliente específicos según su tipo.
    // Tipo de patron: Factory Method + Resolver; 
    // Factory Method encapsula la creación de objetos
    // Resolver se encarga de resolver la implementación adecuada en tiempo de ejecución.
    private final CustomerFactoryResolver factoryResolver;

    // Se opta por construir resolvers para los mappers y validadores, lo que permite una mayor flexibilidad y
    // modularidad en la gestión de las conversiones y validaciones específicas para cada tipo de cliente y documento.
    // Esto facilita la extensión futura del sistema para soportar nuevos tipos de clientes o documentos 
    // sin necesidad de modificar el servicio principal.
    // Tipo de Patron: Strategy + Factory Method + Resolver; cada uno significa una parte del diseño que contribuye a la flexibilidad y mantenibilidad del código.
    // Strategy porque cada tipo de cliente tiene una estrategia de creación y actualización diferente, 
    // Factory Method para encapsular la creación de objetos 
    // Resolver para resolver la implementación adecuada en tiempo de ejecución.
    private final CustomerMapperResolver mapperResolver;

    private final DocumentValidatorResolver validatorResolver;

    @Override
    public CustomerBase create(
            CustomerBase request) {

        validateCustomerData(
                null,
                request.getEmail(),
                request.getIdentificationNumber(),
                request.getDocumentType(),
                request.getCustomerType());

        CustomerFactory factory = factoryResolver.resolve(
                CustomerType.valueOf(
                        request.getCustomerType()
                                .getValue()));

        CustomerDocument customer = factory.create(request);

        customer.setBonusMiles(
                customer.calculateBonusMiles());

        CustomerDocument saved = customerRepository.save(customer);

        return mapperResolver
                .resolve(saved)
                .toResponse(saved);
    }

    @Override
    public CustomerBase update(
            String id,
            CustomerBase request) {

        CustomerDocument customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                Constants.CUSTOMER_NOT_FOUND));

        validateCustomerData(
                id,
                request.getEmail(),
                request.getIdentificationNumber(),
                request.getDocumentType(),
                request.getCustomerType());

        CustomerFactory factory = factoryResolver.resolve(
                customer.getCustomerType());

        factory.update(customer, request);

        CustomerDocument updated = customerRepository.save(customer);

        return mapperResolver
                .resolve(updated)
                .toResponse(updated);
    }

    @Override
    public List<CustomerBase> findAll() {

        return customerRepository.findAll()
                .stream()
                .map(customer -> mapperResolver
                .resolve(customer)
                .toResponse(customer))
                .toList();
    }

    private void validateCustomerData(
            String currentCustomerId,
            String email,
            String identificationNumber,
            DocumentType documentType,
            CustomerType customerType) {

        validateDuplicateIdentificationNumber(
                currentCustomerId,
                identificationNumber);

        validateDuplicateEmail(
                currentCustomerId,
                email);

        validateDocument(
                identificationNumber,
                documentType,
                customerType);
    }

    private void validateDuplicateIdentificationNumber(
            String currentCustomerId,
            String identificationNumber) {

        customerRepository
                .findByIdentificationNumber(
                        identificationNumber)
                .ifPresent(customer -> {

                    boolean isAnotherCustomer = !customer.getId()
                            .equals(currentCustomerId);

                    if (isAnotherCustomer) {

                        throw new BusinessException(
                                Constants.IDENTIFICATION_NUMBER_ALREADY_EXISTS);
                    }
                });
    }

    private void validateDuplicateEmail(
            String currentCustomerId,
            String email) {

        customerRepository
                .findByEmail(email)
                .ifPresent(customer -> {
                    // sirve para verificar si el cliente encontrado con el email proporcionado es diferente al cliente actual (en caso de actualización),
                    // lo que indicaría que el email ya está en uso por otro cliente, lo cual
                    // no está permitido y se lanzaría una excepción de negocio.
                    boolean isAnotherCustomer = !customer.getId()
                            .equals(currentCustomerId);

                    if (isAnotherCustomer) {

                        throw new BusinessException(
                                Constants.EMAIL_ALREADY_EXISTS);
                    }
                });
    }

    private void validateDocument(
            String identificationNumber,
            DocumentType documentType,
            CustomerType customerType) {

        // cumple con el patron de diseño: Strategy + Factory Method + Resolver; 
        // cada uno significa una parte del diseño que contribuye a la flexibilidad y mantenibilidad del código.
        // Strategy porque cada tipo de cliente tiene una estrategia de validación de documento diferente,
        // Factory Method para encapsular la creación de objetos de validación de documentos
        // Resolver para resolver la implementación adecuada en tiempo de ejecución.
        validatorResolver
                .resolve(documentType, customerType)
                .validate(identificationNumber);
    }

    @Override

    public CustomerBase findByDocument(String dni) {

        CustomerDocument customer = customerRepository
                .findByIdentificationNumber(dni)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Cliente no encontrado"
        ));

        return mapperResolver
                .resolve(customer)
                .toResponse(customer);
    }

}
