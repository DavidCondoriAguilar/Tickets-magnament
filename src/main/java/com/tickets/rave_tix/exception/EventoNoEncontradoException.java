package com.tickets.rave_tix.exception;

public class EventoNoEncontradoException extends RuntimeException {
    public EventoNoEncontradoException(String message) {
        super(message);
    }
}