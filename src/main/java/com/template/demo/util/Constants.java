package com.template.demo.util;

public class Constants {
    public static final String EMAIL_ALREADY_EXISTS = "El correo electrónico ya está registrado.";
    public static final String IDENTIFICATION_NUMBER_ALREADY_EXISTS = "El número de identificación ya está registrado.";
    public static final String RUC_ALREADY_EXISTS = "El RUC ya está registrado.";
    public static final String DNI_ALREADY_EXISTS = "El DNI ya está registrado.";
    public static final String INVALID_DNI = "DNI solo debería tener 8 caracteres.";
    public static final String INVALID_DNI_NUMBERS = "DNI solo debería tener números.";
    public static final String INVALID_RUC = "RUC solo debería tener 11 caracteres.";
    public static final String INVALID_RUC_NUMBERS = "RUC solo debería tener números.";  
    public static final String DOCUMENT_TYPE_NO_SUPPORTED = "Tipo documento no soportado.";  
    public static final String CUSTOMER_NOT_FOUND = "Cliente no encontrado.";
    public static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String REGEX_DNI = "\\d{8}";
    public static final String REGEX_RUC = "\\d{11}";

}
