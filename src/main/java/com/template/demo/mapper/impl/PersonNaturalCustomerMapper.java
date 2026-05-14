package com.template.demo.mapper.impl;

import org.openapitools.model.CustomerBase;
import org.openapitools.model.CustomerType;
import org.openapitools.model.PersonNaturalCustomer; 
import org.springframework.stereotype.Component;

import com.template.demo.document.CustomerDocument;
import com.template.demo.document.PersonNaturalCustomerDocument;
import com.template.demo.mapper.CustomerMapper;

@Component
public class PersonNaturalCustomerMapper
        implements CustomerMapper {

    @Override
    public boolean supports(
            CustomerDocument customer) {

        return customer instanceof
                PersonNaturalCustomerDocument;
    }

    @Override
    public CustomerBase toResponse(
            CustomerDocument customer) {

        PersonNaturalCustomerDocument entity =
                (PersonNaturalCustomerDocument) customer;

        PersonNaturalCustomer response =
                new PersonNaturalCustomer();

        response.setCustomerType(
                CustomerType.PN);

        response.setFullName(
                entity.getFullName());

        response.setAge(
                entity.getAge());

        response.setEmail(
                entity.getEmail());

        response.setDocumentType(
                entity.getDocumentType());

        response.setIdentificationNumber(
                entity.getIdentificationNumber());

        response.setBonusMiles(
                entity.getBonusMiles());

        response.setBirthDate(
                entity.getBirthDate());

        return response;
    }
}