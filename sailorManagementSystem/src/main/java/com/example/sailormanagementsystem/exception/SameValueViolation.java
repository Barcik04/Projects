package com.example.sailormanagementsystem.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class SameValueViolation extends DataIntegrityViolationException {
    public SameValueViolation(String message) { super(message); }
}
