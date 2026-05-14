package com.template.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.template.demo.document.CustomerDocument;

/** 
 * Principio de Abstracción (Abstraction), 
 * esta interfaz define un contrato para la persistencia de datos de los clientes, 
 * sin exponer los detalles de implementación.  Al extender MongoRepository, se aprovechan las funcionalidades
 * proporcionadas por Spring Data MongoDB, como operaciones CRUD y consultas personalizadas, 
 * sin necesidad de escribir código adicional para la implementación de estas operaciones.
 * *****
 * Principio de Segregación de Interfaces (ISP), 
 * esta interfaz se enfoca únicamente en las operaciones relacionadas con los clientes, 
 * evitando la inclusión de métodos que no son relevantes para esta entidad.
 * *****
 * Principio de Inversión de Dependencias (DIP), 
 * esta interfaz depende de una abstracción (MongoRepository) en lugar de una implementación concreta, 
 * lo que facilita el mantenimiento y la flexibilidad del código.
 * *****
 * Principio de Responsabilidad Única (SRP), esta interfaz tiene una única responsabilidad, 
 * que es definir las operaciones de persistencia para los clientes, 
 * sin mezclarse con otras responsabilidades como la lógica de negocio o la presentación.
 * 
*/
public interface CustomerRepository extends MongoRepository<CustomerDocument, String>{
    Optional<CustomerDocument> findByEmail(String email);

    Optional<CustomerDocument> findByIdentificationNumber(String identificationNumber);

}
