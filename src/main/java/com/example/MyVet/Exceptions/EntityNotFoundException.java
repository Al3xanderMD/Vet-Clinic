package com.example.MyVet.Exceptions;
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, String id) {
        super(String.format("%s with id %s not found", entityName, id));
    }
    public EntityNotFoundException(String entityName) {
        super(String.format("%s not found", entityName));
    }
}
