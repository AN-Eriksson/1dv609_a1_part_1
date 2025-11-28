package com.lab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SSNHelperTest {
    private ISSNHelper ssnHelper;

    @BeforeEach
    public void instantiateHelper() throws Exception {
        this.ssnHelper = getSSNHelper();
    }

    private ISSNHelper getSSNHelper() throws Exception {
        // Choose implementation to test

//        return new SSNHelper();
//        return new BuggySSNHelperWrongLength();
//        return new BuggySSNHelperIncorrectFormat();
//        return new BuggySSNHelperIncorrectFormatFalse();
//        return new BuggySSNHelperAllowMonth0();
//        return new BuggySSNHelperAllowDayUpTo30();
        return new BuggySSNHelperMessyLuhn();
    }

    @Test
    public void isCorrectLengthShouldReturnFalseOnTooHighLength() throws Exception {
        assertFalse(this.ssnHelper.isCorrectLength("twelveCharsX"));
    }

    @Test
    public void isCorrectFormatShouldReturnFalseOnIncorrectFormat() {
        assertFalse(this.ssnHelper.isCorrectFormat("9001010017"));
    }

    @Test
    public void isCorrectFormatShouldReturnTrueOnCorrectFormat() {
        assertTrue(this.ssnHelper.isCorrectFormat("900101-0017"));
    }

    @Test
    public void isValidMonthShouldReturnFalseOnMonth0() {
        assertFalse(this.ssnHelper.isValidMonth("00"));
    }

    @Test
    public void isValidDayShouldReturnTrueOnDay31() {
        assertTrue(this.ssnHelper.isValidDay("31"));
    }

    @Test
    public void luhnIsCorrectShouldReturnTrueForCorrectLuhn() {
        assertTrue(this.ssnHelper.luhnIsCorrect("900101-0017"));
    }
}
