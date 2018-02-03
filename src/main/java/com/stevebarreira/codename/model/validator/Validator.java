package com.stevebarreira.codename.model.validator;

public interface Validator<T> {

    boolean isValid(T input);
}
