package com.bookproject.user;

import org.apache.commons.codec.digest.DigestUtils;

class UserUtils {

    private UserUtils(){}

    static String getHashedPassword(String password) {
        return DigestUtils.sha1Hex(password);
    }
}
