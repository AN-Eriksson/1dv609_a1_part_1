package com.lab;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SwedishSocialSecurityNumberTest {

    @Mock
    private SSNHelper ssnHelper;

    private SSN getSSN(String stringInput) throws Exception {
        // Choose implementation to test

//        return new SwedishSocialSecurityNumber(stringInput, ssnHelper);
//        return new BuggySwedishSocialSecurityNumberNoLenCheck(stringInput, ssnHelper);
        return new BuggySwedishSocialSecurityNumberNoLuhn(stringInput, ssnHelper);
//        return new BuggySwedishSocialSecurityNumberNoTrim(stringInput, ssnHelper);
//        return new BuggySwedishSocialSecurityNumberWrongYear(stringInput, ssnHelper);
    }

    // REDUNDANT? constructorShouldCallHelperWithTrimmedInput verifies that isCorrectLenght was called and will fail
    // if method is missing.
    @Test
    public void constructorShouldThrowWhenLengthIsIncorrect() {
        // Setup mock
        when(ssnHelper.isCorrectLength("900101")).thenReturn(false);
        when(ssnHelper.isCorrectFormat("900101")).thenReturn(true);
        when(ssnHelper.isValidMonth("01")).thenReturn(true);
        when(ssnHelper.isValidDay("01")).thenReturn(true);
        when(ssnHelper.luhnIsCorrect("900101")).thenReturn(true);

        // Assert: constructing the SUT should throw
        assertThrows(Exception.class, () -> getSSN("900101"));
    }

    @Test
    public void constructorShouldThrowOnNoLuhn() throws Exception {
        when(ssnHelper.isCorrectLength("900101-0017")).thenReturn(true);
        when(ssnHelper.isCorrectFormat("900101-0017")).thenReturn(true);
        when(ssnHelper.isValidMonth("01")).thenReturn(true);
        when(ssnHelper.isValidDay("01")).thenReturn(true);
        when(ssnHelper.luhnIsCorrect("900101-0017")).thenReturn(false);

        assertThrows(Exception.class, () -> getSSN("900101-0017"));
    }

    @Test
    public void constructorShouldCallHelperWithTrimmedInput() throws Exception {
        String raw = " 900101-0017  ";
        String trimmed = "900101-0017";

        // Stub to pass constructor checks and allow the object to be created
        when(ssnHelper.isCorrectLength(anyString())).thenReturn(true);
        when(ssnHelper.isCorrectFormat(anyString())).thenReturn(true);
        when(ssnHelper.isValidMonth(anyString())).thenReturn(true);
        when(ssnHelper.isValidDay(anyString())).thenReturn(true);
        when(ssnHelper.luhnIsCorrect(anyString())).thenReturn(true);

        // Create object with untrimmed string
        getSSN(raw);

        // Verify that the helper was called with the trimmed string
        verify(ssnHelper).isCorrectLength(trimmed);
    }

    @Test
    public void getYearShouldReturnFirstTwoCharsOfInputString() throws Exception {
        when(ssnHelper.isCorrectLength("900101-0017")).thenReturn(true);
        when(ssnHelper.isCorrectFormat("900101-0017")).thenReturn(true);
        when(ssnHelper.isValidMonth("01")).thenReturn(true);
        when(ssnHelper.isValidDay("01")).thenReturn(true);
        when(ssnHelper.luhnIsCorrect("900101-0017")).thenReturn(true);

        SSN ssn = getSSN("900101-0017");

        assertEquals("90", ssn.getYear());
    }

}