package com.technical.assessment.utils;


public class UserRoles {

    public static final String LOGGED_USER = "hasRole('ROLE_USER') or hasRole('ROLE_PM') or hasRole('ROLE_SUP')";
    public static final String LOGGED_PRODUCT_MANAGER = "hasRole('ROLE_PM') or hasRole('ROLE_SUP')";
    public static final String LOGGED_PRODUCT_SUPERVISOR = "hasRole('ROLE_SUP')";

}
