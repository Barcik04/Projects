package com.example.sailormanagementsystem.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class SameEmailException extends DataIntegrityViolationException {
    public SameEmailException(String message) { super(message); }
}
