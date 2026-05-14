package com.template.demo.validators;

import org.openapitools.model.DocumentType;
import org.springframework.stereotype.Component;

import com.template.demo.exception.BusinessException;
import com.template.demo.util.Constants;

@Component
public class DniValidator implements DocumentValidator {

    @Override
    public void validate(String identificationNumber) {
        if (identificationNumber == null || identificationNumber.length() != 8) {
            throw new BusinessException(Constants.INVALID_DNI);
        }
        if (!identificationNumber.matches(Constants.REGEX_DNI)) {
            throw new BusinessException(Constants.INVALID_DNI_NUMBERS);
        }
    }

    @Override
    public DocumentType supportsDocumentType() {
        return DocumentType.DNI;
    }

}
