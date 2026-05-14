package com.template.demo.document;

import java.time.LocalDate;

import org.springframework.data.annotation.TypeAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// La anotación @TypeAlias se utiliza para definir un alias para la clase, lo que facilita su identificación en la base de datos MongoDB.
@TypeAlias("PJ") 
public class PersonJuridicalCustomerDocument extends CustomerDocument {
    private LocalDate constitutionDate;
    private String legalRepresentative;

    @Override
    public int calculateBonusMiles() {
        return 5000;
    }
    
}
