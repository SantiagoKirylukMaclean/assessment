package com.technical.assessment.error;// Java program to demonstrate user defined exception

// This program throws an exception whenever balance
// amount is below Rs 1000
public class CustomAssessmentException extends Exception {


    // parametrized constructor
    public CustomAssessmentException(String str) {
        super(str);
    }
} 
