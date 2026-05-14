package com.template.demo.mapper;

import org.openapitools.model.CustomerBase; 

import com.template.demo.document.CustomerDocument;

public interface CustomerMapper {

    boolean supports(
            CustomerDocument customer);

    CustomerBase toResponse(
            CustomerDocument customer);
}