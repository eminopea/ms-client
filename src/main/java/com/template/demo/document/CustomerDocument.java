package com.template.demo.document;

import org.openapitools.model.CustomerType;
import org.openapitools.model.DocumentType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

// La anotación @Document indica que esta clase es un documento de MongoDB y especifica la colección a la que pertenece.
// Es una clase abstracta que representa a un cliente
// Tiene un metodo abstracto calculateBonusMiles() 
// que debe ser implementado por las subclases para calcular los bonus millas 
// de acuerdo con el tipo de cliente.
@Document(collection = "customers")
@Getter
@Setter
public abstract class CustomerDocument {

    @Id
    private String id;

    private String fullName;

    private Integer age;

    private String email; 

    private DocumentType documentType;

    private String identificationNumber;

    private int bonusMiles;

    private CustomerType customerType;

    // Aplicaciòn de Polimorfismo, este método abstracto permite que las subclases 
    // de Customer implementen su propia lógica para calcular los bonus millas, 
    // lo que facilita la extensión del sistema sin modificar el código existente.
    public abstract int calculateBonusMiles();

}
