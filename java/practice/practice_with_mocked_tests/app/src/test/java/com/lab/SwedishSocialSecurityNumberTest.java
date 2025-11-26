package com.lab;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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

    @Mock
    private SSNHelper ssnHelper;

}