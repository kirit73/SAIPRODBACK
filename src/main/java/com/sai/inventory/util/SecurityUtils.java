package com.sai.inventory.util;

import java.security.MessageDigest;
import java.util.Base64;

public class SecurityUtils {

    /*public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public static String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    /*public static void main(String[] args) {
        System.out.println(hashPassword("DKGLabs@2024"));
    }*/
}
