package com.lab;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//public class SwedishSocialSecurityNumberTest {
//
//    private SSNHelper helper;
//
//    @BeforeEach
//    public void setUp() {
//        helper = new SSNHelper();
//    }
//
//    @Test
//    public void shouldAcceptValidSSN() throws Exception {
//        SwedishSocialSecurityNumber ssn = new SwedishSocialSecurityNumber("900101-0017", helper);
//
//        assertEquals("90", ssn.getYear());
//        assertEquals("01", ssn.getMonth());
//        assertEquals("01", ssn.getDay());
//        assertEquals("0017", ssn.getSerialNumber());
//    }
//}

@ExtendWith(MockitoExtension.class)
public class SwedishSocialSecurityNumberTest {

    private SSN getSSN(String stringInput) throws Exception {
        // Choose implementation to test

//        return new SwedishSocialSecurityNumber(stringInput, ssnHelper);
        return new BuggySwedishSocialSecurityNumberNoLenCheck(stringInput, ssnHelper);
//        return new BuggySwedishSocialSecurityNumberNoLuhn(stringInput, ssnHelper);
//        return new BuggySwedishSocialSecurityNumberNoTrim(stringInput, ssnHelper);
//        return new BuggySwedishSocialSecurityNumberWrongYear(stringInput, ssnHelper);
    }

    @Mock
    private SSNHelper ssnHelper;

    @Test
    public void shouldCreateValidSSNWHenAllChecksPass() throws Exception {
        // Setup mock
        when(ssnHelper.isCorrectLength("900101-0017")).thenReturn(true);
        when(ssnHelper.isCorrectFormat("900101-0017")).thenReturn(true);
        when(ssnHelper.isValidMonth("01")).thenReturn(true);
        when(ssnHelper.isValidDay("01")).thenReturn(true);
        when(ssnHelper.luhnIsCorrect("900101-0017")).thenReturn(true);

        // Create SUT
        SSN ssn = getSSN("900101-0017");

        // Asserts
        assertEquals("90", ssn.getYear());
        assertEquals("01", ssn.getMonth());
        assertEquals("01", ssn.getDay());
        assertEquals("0017", ssn.getSerialNumber());

        // Verify calls to mock
        verify(ssnHelper).isCorrectLength("900101-0017");
        verify(ssnHelper).isCorrectFormat("900101-0017");
        verify(ssnHelper).isValidMonth("01");
        verify(ssnHelper).isValidDay("01");
        verify(ssnHelper).luhnIsCorrect("900101-0017");
    }

    @Test
    public void constructorShouldThrowWhenLengthIsIncorrect() {
        // Setup mock
        when(ssnHelper.isCorrectLength("900101")).thenReturn(false);

        // Assert: constructing the SUT should throw
        assertThrows(Exception.class, () -> getSSN("900101"));

        // Verify that the length check was called
        verify(ssnHelper).isCorrectLength("900101");
    }

}