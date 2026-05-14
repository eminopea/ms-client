package com.template.demo.mapper.impl;
 
import org.openapitools.model.CustomerBase;
import org.openapitools.model.CustomerType;
import org.openapitools.model.PersonJuridicaCustomer;
import org.springframework.stereotype.Component;

import com.template.demo.document.CustomerDocument;
import com.template.demo.document.PersonJuridicalCustomerDocument;
import com.template.demo.mapper.CustomerMapper;

@Component
public class PersonJuridicaCustomerMapper
        implements CustomerMapper {

    @Override
    public boolean supports(
            CustomerDocument customer) {

        return customer instanceof
                PersonJuridicalCustomerDocument;
    }

    @Override
    public CustomerBase toResponse(
            CustomerDocument customer) {

        PersonJuridicalCustomerDocument entity =
                (PersonJuridicalCustomerDocument) customer;

        PersonJuridicaCustomer response =
                new PersonJuridicaCustomer();

        response.setCustomerType(
                CustomerType.PJ);

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

        response.setConstitutionDate(
                entity.getConstitutionDate());

        response.setLegalRepresentative(
                entity.getLegalRepresentative());

        return response;
    }
}