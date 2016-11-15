package com.brainbeanapps.core.net;

import com.brainbeanapps.core.BaseException;

/**
 * Created by Rosty on 10/19/2016.
 */

public class NetworkException extends BaseException {

    public int code;

    public NetworkException(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
