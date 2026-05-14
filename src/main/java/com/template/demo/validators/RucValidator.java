package com.template.demo.validators;

import org.openapitools.model.DocumentType;
import org.springframework.stereotype.Component;

import com.template.demo.exception.BusinessException;
import com.template.demo.util.Constants;

@Component
public class RucValidator implements DocumentValidator {

    @Override
    public void validate(String identificationNumber) {
        if (identificationNumber == null || identificationNumber.length() != 11) {
            throw new BusinessException(Constants.INVALID_RUC);
        }
        
        if (!identificationNumber.matches(Constants.REGEX_RUC)) {
            throw new BusinessException(Constants.INVALID_RUC);
        }
    }

    @Override
    public DocumentType supportsDocumentType() {
         return DocumentType.RUC; 
    }
    
}
