package com.bookproject.user;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserUtils {

    static String getHashedPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return DigestUtils.sha1Hex(password);
    }
}
