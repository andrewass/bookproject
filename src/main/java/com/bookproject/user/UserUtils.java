package com.bookproject.user;

import com.bookproject.exception.InvalidUserDataException;
import org.apache.commons.codec.digest.DigestUtils;

class UserUtils {

    private UserUtils(){}

    static String getHashedPassword(String password) {
        return DigestUtils.sha1Hex(password);
    }

    static User processSignedUpUser(User user) throws InvalidUserDataException {

        user.setPassword(user.getPassword());

        return user;
    }


}
