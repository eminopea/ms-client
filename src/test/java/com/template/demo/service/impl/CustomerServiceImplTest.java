package com.template.demo.service.impl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.CustomerBase;
import org.openapitools.model.CustomerType;
import org.openapitools.model.DocumentType;
import org.openapitools.model.PersonNaturalCustomer;

import com.template.demo.document.CustomerDocument;
import com.template.demo.document.PersonNaturalCustomerDocument;
import com.template.demo.exception.BusinessException;
import com.template.demo.factory.CustomerFactory;
import com.template.demo.factory.resolver.CustomerFactoryResolver;
import com.template.demo.factory.resolver.CustomerMapperResolver;
import com.template.demo.factory.resolver.DocumentValidatorResolver;
import com.template.demo.mapper.CustomerMapper;
import com.template.demo.repository.CustomerRepository;
import com.template.demo.validators.DocumentValidator;

// TDD: primero escribes las pruebas, luego implementas el código hasta que las pruebas pasen.
// Junit5: en este ejemplo se utiliza JUnit 5 para escribir las pruebas unitarias, 
// lo que permite una sintaxis más moderna y características avanzadas.
// Mockito: se utiliza Mockito para crear objetos simulados (mocks) de las dependencias, 
// lo que facilita la prueba de la lógica de negocio sin depender de implementaciones concretas.
// InjectMocks: se utiliza la anotación @InjectMocks para inyectar los mocks en la clase que se está probando,
// lo que simplifica la configuración de las pruebas y mejora la legibilidad del código de prueba.
// Principio de Inversion de dependencias (DIP): la clase CustomerServiceImpl depende de abstracciones (interfaces) 
// en lugar de implementaciones concretas, lo que facilita el mantenimiento y las pruebas unitarias.
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerFactoryResolver factoryResolver;

    @Mock
    private CustomerMapperResolver mapperResolver;

    @Mock
    private DocumentValidatorResolver validatorResolver;

    @Mock
    private CustomerFactory customerFactory;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private DocumentValidator documentValidator;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private PersonNaturalCustomer request;

    private PersonNaturalCustomerDocument customerDocument;

    @BeforeEach
    void setup() {

        request = new PersonNaturalCustomer();

        request.setCustomerType(CustomerType.PN);

        request.setFullName("Estefani");

        request.setAge(24);

        request.setEmail("test@gmail.com");

        request.setDocumentType(DocumentType.DNI);

        request.setIdentificationNumber("12345678");

        customerDocument
                = new PersonNaturalCustomerDocument();

        customerDocument.setId("1");

        customerDocument.setFullName(
                request.getFullName());

        customerDocument.setAge(
                request.getAge());

        customerDocument.setEmail(
                request.getEmail());

        customerDocument.setDocumentType(
                request.getDocumentType());

        customerDocument.setIdentificationNumber(
                request.getIdentificationNumber());

        // when(factoryResolver.resolve(any()))
        //         .thenReturn(customerFactory);
        // when(validatorResolver.resolve(any(), any()))
        //         .thenReturn(documentValidator);
        // when(customerMapper.supports(any()))
        //         .thenReturn(true);
        // when(mapperResolver.resolve(any()))
        //         .thenReturn(customerMapper);
    }

    @Test
    void shouldCreateCustomerSuccessfully() {
        when(factoryResolver.resolve(any()))
                .thenReturn(customerFactory);

        when(validatorResolver.resolve(any(), any()))
                .thenReturn(documentValidator);

        // when(customerMapper.supports(any()))
        //         .thenReturn(true);

        when(mapperResolver.resolve(any()))
                .thenReturn(customerMapper);
        when(customerRepository.findByEmail(
                request.getEmail()))
                .thenReturn(Optional.empty());

        when(customerRepository
                .findByIdentificationNumber(
                        request.getIdentificationNumber()))
                .thenReturn(Optional.empty());

        when(customerFactory.create(request))
                .thenReturn(customerDocument);

        when(customerRepository.save(any()))
                .thenReturn(customerDocument);

        when(customerMapper.toResponse(any()))
                .thenReturn(request);

        CustomerBase response
                = customerService.create(request);

        assertNotNull(response);

        verify(customerRepository)
                .save(any(CustomerDocument.class));
    }

    @Test
    void shouldThrowExceptionWhenEmailExists() {

        CustomerDocument existing
                = new PersonNaturalCustomerDocument();

        existing.setId("1");

        when(customerRepository.findByEmail(
                request.getEmail()))
                .thenReturn(Optional.of(existing));

        assertThrows(
                BusinessException.class,
                () -> customerService.create(request));
    }

    @Test
    void shouldFindAllCustomers() {

        when(mapperResolver.resolve(any()))
                .thenReturn(customerMapper);

        when(customerRepository.findAll())
                .thenReturn(List.of(customerDocument));

        when(customerMapper.toResponse(any()))
                .thenReturn(request);

        when(customerRepository.findAll())
                .thenReturn(List.of(customerDocument));

        when(customerMapper.toResponse(any()))
                .thenReturn(request);

        List<CustomerBase> response
                = customerService.findAll();

        assertEquals(1, response.size());
    }

    @Test
    void shouldUpdateCustomerSuccessfully() {

        when(factoryResolver.resolve(any()))
                .thenReturn(customerFactory);

        when(validatorResolver.resolve(any(), any()))
                .thenReturn(documentValidator);

        when(mapperResolver.resolve(any()))
                .thenReturn(customerMapper);

        when(customerRepository.findById("1"))
                .thenReturn(Optional.of(customerDocument));

        when(customerRepository.findByEmail(
                request.getEmail()))
                .thenReturn(Optional.empty());

        when(customerRepository
                .findByIdentificationNumber(
                        request.getIdentificationNumber()))
                .thenReturn(Optional.empty());

        when(customerRepository.save(any()))
                .thenReturn(customerDocument);

        when(customerMapper.toResponse(any()))
                .thenReturn(request);

        CustomerBase response
                = customerService.update(
                        "1",
                        request);

        assertNotNull(response);

        verify(customerFactory)
                .update(customerDocument, request);

        verify(customerRepository)
                .save(customerDocument);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {

        when(customerRepository.findById("1"))
                .thenReturn(Optional.empty());

        assertThrows(
                BusinessException.class,
                () -> customerService.update(
                        "1",
                        request));
    }

    @Test
    void shouldThrowExceptionWhenIdentificationNumberExists() {

        CustomerDocument existing
                = new PersonNaturalCustomerDocument();

        existing.setId("2");

        when(customerRepository
                .findByIdentificationNumber(
                        request.getIdentificationNumber()))
                .thenReturn(Optional.of(existing));

        assertThrows(
                BusinessException.class,
                () -> customerService.create(request));
    }

    @Test
    void shouldThrowExceptionWhenDocumentIsInvalid() {

        // when(factoryResolver.resolve(any()))
        //         .thenReturn(customerFactory);

        when(validatorResolver.resolve(any(), any()))
                .thenReturn(documentValidator);

        doThrow(new BusinessException("DNI inválido"))
                .when(documentValidator)
                .validate("12345678");

        when(customerRepository.findByEmail(any()))
                .thenReturn(Optional.empty());

        when(customerRepository
                .findByIdentificationNumber(any()))
                .thenReturn(Optional.empty());

        assertThrows(
                BusinessException.class,
                () -> customerService.create(request));
    }

    @Test
    void shouldThrowExceptionWhenEmailExistsOnUpdate() {

        CustomerDocument existing
                = new PersonNaturalCustomerDocument();

        existing.setId("1");

        CustomerDocument other
                = new PersonNaturalCustomerDocument();

        other.setId("2");

        when(customerRepository.findById("1"))
                .thenReturn(Optional.of(existing));

        when(customerRepository.findByEmail(
                request.getEmail()))
                .thenReturn(Optional.of(other));

        assertThrows(
                BusinessException.class,
                () -> customerService.update(
                        "1",
                        request));
    }
}
