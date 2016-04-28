/****************************************************************************
 * Name: Fraction Worksheet Creator
 * Team: Elementary Engineers 
 * Date produced: 04/28/2016
 * ________________________________
 * Purpose of program:
 * The Fraction Worksheet Creator (FWC) is a new stand-alone product 
 * that allows teachers and students to create random exercise worksheets 
 * to practice operations with fractions.The generated worksheets can contain 
 * fraction problems of various difficulty levels, from basic addition and 
 * subtraction problems with visuals and images suitable for small children, 
 * to quite advanced fraction equations. 
 * ****************************************************************************
 */

package com.elementaryengineers.fwc.model;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Note: This class was written by Thomas McNulty for the class:
 * CSCI 400: Special Topic in Computer Science - GUI Programming
 * Instructor: Andrew Jung
 * Date: Spring 2015
 * Framingham State University
 *
 * This class provides methods for encrypting a password into a salt + hash,
 * and for checking/validating a password against a stored salt + hash.
 *
 * @author Thomas McNulty
 **/
public class EncryptedPassword {

    private final String hash, salt;
    private static final SecureRandom random = new SecureRandom();

    /**
     * Creates a new encrypted password using a given password by
     * generating a hash and salt for that password.
     * Encrypts the given password by assigning a salt to the password, hashing
     * the salted password, and returning an array containing the hexadecimal
     * string representations of the salt and the hash.
     *
     * @param password String password that needs to be encrypted
     **/
    public EncryptedPassword(String password) {
        byte[] saltBytes = new byte[24];
        random.nextBytes(saltBytes);
        this.salt = DatatypeConverter.printHexBinary(saltBytes);

        byte[] hashBytes = generateHash(password, saltBytes);
        this.hash = DatatypeConverter.printHexBinary(hashBytes);
    }

    /**
     * Creates a new EncryptedPassword object using the given
     * hash and salt strings that can then be used to check
     * passwords for a match.
     *
     * @param hash String hash of this encrypted password
     * @param salt String salt of this encrypted password
     **/
    public EncryptedPassword(String hash, String salt) {
        this.hash = hash;
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public String getSalt() {
        return salt;
    }

    /**
     * Checks a given password against this encrypted password's pair of
     * salt and hash strings. The method generates a hash of the
     * password + salt string and checks if it matches the hash string.
     **/
    public boolean checkPassword(String password) {
        byte[] checkSalt = DatatypeConverter.parseHexBinary(this.salt);
        byte[] checkHash = generateHash(password, checkSalt);
        String checkString = DatatypeConverter.printHexBinary(checkHash);

        return this.hash.equals(checkString);
    }

    /**
     * Uses the PBKDF2 hashing algorithm to generate a hash of the password + salt.
     * This will always return the same hashcode if given the same password and salt.
     *
     * The entire method is basically just a single method call to
     * SecretKeyFactory.generateSecretKey(...), with some wrapping to format input + output.
     **/
    private static byte[] generateHash(String password, byte[] salt) {
        byte[] hash;
        try {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 1000, 24 * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            hash = skf.generateSecret(spec).getEncoded();
        } catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
            hash = new byte[0];
            System.out.println("Error: " + e.getClass());
        }
        return hash;
    }
}