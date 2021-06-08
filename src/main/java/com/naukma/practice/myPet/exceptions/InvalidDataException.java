package com.naukma.practice.myPet.exceptions;


public class InvalidDataException extends Exception {
    public InvalidDataException(String errorMessage) {
        super(errorMessage);
    }
}