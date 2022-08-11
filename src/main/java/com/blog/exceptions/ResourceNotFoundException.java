package com.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String resourceField;
    int fieldValue;

    public ResourceNotFoundException(String resourceField, String resourceName, Integer fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, resourceField, fieldValue));
        this.resourceField = resourceField;
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }

}
