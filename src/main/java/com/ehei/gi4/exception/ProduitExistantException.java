package com.ehei.gi4.exception;

public class ProduitExistantException extends RuntimeException {
    public ProduitExistantException(String message) {
        super(message);
    }
}