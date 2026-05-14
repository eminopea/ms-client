package com.template.demo.factory;
 
import org.openapitools.model.CustomerBase;
import org.openapitools.model.CustomerType; 

import com.template.demo.document.CustomerDocument;

// Esta interfaz define la estructura de una fábrica de clientes, 
// que es responsable de crear y actualizar instancias de CustomerDocument 
// basándose en los datos proporcionados en CustomerBase.
// Cada implementación de esta interfaz se encargará de manejar un tipo específico de cliente, 
// lo que permite una mayor flexibilidad y adherencia al principio de responsabilidad única (SRP), 
// ya que cada fábrica tiene la responsabilidad de crear y actualizar un tipo específico de cliente 
// sin mezclarse con otros tipos.
public interface CustomerFactory {

    CustomerDocument create(
            CustomerBase request);

    void update(
            CustomerDocument customer,
            CustomerBase request);

    CustomerType supports();
}
