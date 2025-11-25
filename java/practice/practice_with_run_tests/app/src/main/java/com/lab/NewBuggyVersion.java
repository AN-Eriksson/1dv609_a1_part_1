package com.lab;

public class NewBuggyVersion implements IPassword {
    protected int passwordHash;

    public NewBuggyVersion(String pw) throws Exception {
        String trimmedPW = pw.trim();
        if (isToShort(trimmedPW)) {
            throw new Exception("To short password");
        }
        if (containsNumber(trimmedPW) == false) {
            throw new Exception("Does not contain a number");
        }
        this.passwordHash = simpleHash(trimmedPW);
    }
    
    private int simpleHash(String input) {
        int hash = 7;
        for (int i = 0; i < input.length(); i++) {
            hash = hash * 31 + input.charAt(i);
        }
        return hash;
    }
    
    private boolean isToShort(String pw) {
        // BUG: Rejects 12 character passwords. The current test only checks 11.
        return pw.length() <= 12;
    }
    
    private boolean containsNumber(String text) {
        return text.matches(".*\\d.*");
    }
    
    public int getPasswordHash() {
        return this.passwordHash;
    }
    
    public boolean isPasswordSame(IPassword other) {
        return this.passwordHash == other.getPasswordHash();
    }
}
