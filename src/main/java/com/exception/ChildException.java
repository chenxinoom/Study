package com.exception;

public class ChildException extends ParentException{

    public ChildException(){}

    public ChildException(String message){
        super(message);
    }
}
