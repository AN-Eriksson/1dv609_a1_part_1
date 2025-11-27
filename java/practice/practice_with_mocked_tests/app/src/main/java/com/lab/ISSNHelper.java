package com.lab;

public interface ISSNHelper {
    boolean isCorrectLength(String stringInput);
    boolean isCorrectFormat(String stringInput);
    boolean isValidMonth(String stringInput);
    boolean isValidDay(String stringInput);
    boolean luhnIsCorrect(String stringInput);
}
