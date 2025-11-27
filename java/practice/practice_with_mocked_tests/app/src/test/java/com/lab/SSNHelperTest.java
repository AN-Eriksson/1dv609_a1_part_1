package com.lab;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SSNHelperTest {
    private SSNHelper ssnHelper;

    @BeforeEach
    public void instantiateHelper() throws Exception {
        this.ssnHelper = getSSNHelper();
    }

    private SSNHelper getSSNHelper() throws Exception {
        // Choose implementation to test

        return new SSNHelper();
//        return new BuggySSNHelperWrongLength();
//        return new BuggySSNHelperIncorrectFormat();
//        return new BuggySSNHelperIncorrectFormatFalse();
//        return new BuggySSNHelperAllowMonth0();
//        return new BuggySSNHelperAllowDayUpTo30();
//        return new BuggySSNHelperMessyLuhn();
    }

    @Test
    public void isCorrectLengthShouldReturnFalseOnIncorrectLength() throws Exception {
        assertFalse(this.ssnHelper.isCorrectLength("tenCharsXX"));
        assertFalse(this.ssnHelper.isCorrectLength("twelveCharsX"));
    }
}
