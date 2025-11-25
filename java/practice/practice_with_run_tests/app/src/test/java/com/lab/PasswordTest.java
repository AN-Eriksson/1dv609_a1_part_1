package com.lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Password implementations.
 * <p>
 * To test different buggy versions, simply uncomment the corresponding
 * getPassword() method and comment out the others.
 * <p>
 * Available implementations:
 * - Password: Correct implementation
 * - BugDoesNotTrim: Does not trim whitespace
 * - BugToShortPassword: Allows passwords shorter than 12 characters
 * - BugVeryShort: Allows way to short passwords
 * - BugWrongExceptionMessage: Wrong exception message for short passwords
 * - BugMissingPasswordLengthCheck: Does not throw exception for short passwords
 * - BugMissingNumberCheck: Does not throw exception if password lacks a number
 * - BugIsPasswordSameAlwaysTrue: isPasswordSame always returns true
 * - BugWrongHashingAlgorithm: Wrong hashing algorithm
 */

public class PasswordTest {
    private static final String ELEVEN_CHARS = "01234567891";

    private IPassword getPassword(String s) throws Exception {
        return (IPassword) new Password(s);
//         return (IPassword) new BugDoesNotTrim(s);
//         return (IPassword) new BugToShortPassword(s);
//         return (IPassword) new BugVeryShort(s);
//         return (IPassword) new BugWrongExceptionMessage(s);
//         return (IPassword) new BugMissingPasswordLengthCheck(s);
//         return (IPassword) new BugMissingNumberCheck(s);
//         return (IPassword) new BugIsPasswordSameAlwaysTrue(s);
//         return (IPassword) new BugWrongHashingAlgorithm(s);
    }

    @Test
    public void constructorShouldTrimWhitespaceFromPasswordInput() throws Exception {
        IPassword p1 = getPassword("1234567891011");
        IPassword p2 = getPassword(" 1234567891011 ");

        assertTrue(p1.isPasswordSame(p2));
    }

    @Test
    public void constructorShouldThrowOnTooShortPasswordWithCorrectExceptionMessage() {
        Exception ex = assertThrows(Exception.class, () -> getPassword(ELEVEN_CHARS));
        assertEquals("To short password", ex.getMessage());
    }

    @Test
    public void constructorShouldThrowIfPasswordLacksNumber() {
        assertThrows(Exception.class, () -> getPassword("jag har inget nummer!"));
    }

    @Test
    public void constructorShouldThrowIfDifferentPasswordsAreSame() throws Exception {
        IPassword p1 = getPassword("1234567891011");
        IPassword p2 = getPassword("1234567891012");

        assertFalse(p1.isPasswordSame(p2));
    }

    @Test
    public void simpleHashShouldCreateExpectedHash() throws Exception {
        IPassword password = getPassword("andreasandreas1");
        int expectedHash = -1914874678;

        int actualHash = password.getPasswordHash();

        assertEquals(expectedHash, actualHash);
    }

}
