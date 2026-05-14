package com.template.demo.util;

import org.openapitools.model.CustomerType;
import org.openapitools.model.DocumentType;
import org.openapitools.model.PersonJuridicaCustomer;
import org.openapitools.model.PersonNaturalCustomer;

public final class CustomerRequestUtils {

    private CustomerRequestUtils() {
    }

    public static String getEmail(
            Object request) {

        if (request instanceof PersonNaturalCustomer pn) {
            return pn.getEmail();
        }

        if (request instanceof PersonJuridicaCustomer pj) {
            return pj.getEmail();
        }

        throw new IllegalArgumentException(
                "Invalid customer type");
    }

    public static String getIdentificationNumber(
            Object request) {

        if (request instanceof PersonNaturalCustomer pn) {
            return pn.getIdentificationNumber();
        }

        if (request instanceof PersonJuridicaCustomer pj) {
            return pj.getIdentificationNumber();
        }

        throw new IllegalArgumentException(
                "Invalid customer type");
    }

    public static DocumentType getDocumentType(
            Object request) {

        if (request instanceof PersonNaturalCustomer pn) {
            return pn.getDocumentType();
        }

        if (request instanceof PersonJuridicaCustomer pj) {
            return pj.getDocumentType();
        }

        throw new IllegalArgumentException(
                "Invalid customer type");
    }

    public static CustomerType getCustomerType(
            Object request) {

        if (request instanceof PersonNaturalCustomer) {
            return CustomerType.PN;
        }

        if (request instanceof PersonJuridicaCustomer) {
            return CustomerType.PJ;
        }

        throw new IllegalArgumentException(
                "Invalid customer type");
    }
}