package com.template.demo.factory.resolver;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.openapitools.model.CustomerType;
import org.openapitools.model.DocumentType;
import org.springframework.stereotype.Component;

import com.template.demo.exception.BusinessException;
import com.template.demo.validators.DocumentValidator;

// Cumple con el principio de responsabilidad única (SRP) al encargarse exclusivamente de 
// resolver el validador adecuado para un tipo de documento específico.
// Además, sigue el principio de abierto/cerrado (OCP) al permitir la adición de nuevos validadores 
// sin modificar el resolver, simplemente agregando nuevas implementaciones de DocumentValidator y 
// actualizando el mapa de validadores.

@Component
public class DocumentValidatorResolver {

    private final Map<DocumentType, DocumentValidator>
            validators;

    private static final Map<DocumentType, CustomerType>
            RULES = Map.of(
            DocumentType.DNI, CustomerType.PN,
            DocumentType.RUC, CustomerType.PJ
    );

    public DocumentValidatorResolver(
            List<DocumentValidator> validatorList) {

        this.validators = validatorList.stream()
                .collect(Collectors.toMap(
                        DocumentValidator::supportsDocumentType,
                        Function.identity()));
    }

    public DocumentValidator resolve(
            DocumentType documentType,
            CustomerType customerType) {

        validateCustomerType(
                documentType,
                customerType);

        DocumentValidator validator =
                validators.get(documentType);

        if (validator == null) {
            throw new BusinessException(
                    "Unsupported document type: "
                            + documentType);
        }

        return validator;
    }

    private void validateCustomerType(
            DocumentType documentType,
            CustomerType customerType) {

        CustomerType expected =
                RULES.get(documentType);

        if (expected != customerType) {

            throw new BusinessException(
                    String.format(
                            "%s no es válido para %s",
                            documentType,
                            customerType
                    )
            );
        }
    }
}
