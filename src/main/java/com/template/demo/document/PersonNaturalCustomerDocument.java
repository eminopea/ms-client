package com.template.demo.document;

import java.time.LocalDate;

import org.springframework.data.annotation.TypeAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// La anotación @TypeAlias se utiliza para definir un alias para la clase, lo que facilita su identificación en la base de datos MongoDB.
@TypeAlias("PN")
public class PersonNaturalCustomerDocument extends CustomerDocument {
    private LocalDate birthDate;

    @Override
    public int calculateBonusMiles() {
        return 2000;
    }
}
