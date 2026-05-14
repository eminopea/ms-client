package com.template.demo.validators;

import org.openapitools.model.DocumentType;

public interface DocumentValidator {
    void validate(String identificationNumber);
    DocumentType supportsDocumentType();
}
