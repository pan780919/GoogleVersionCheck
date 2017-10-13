package com.jackpan.googleversioncheck;

import android.content.ContentValues;

public class ApiSetting extends  RequestSetting {
    public static String KEY_DEVICE_ID = "device_id";
    public ApiType rType;

    public ApiSetting(String host, HttpMethod httpMethod, ApiType type) {

        this.url = host + type.method;
        this.httpMethod = httpMethod;
        this.type = type.type;
        this.rType = type;
    }

    public ApiSetting(String host, HttpMethod httpMethod, int type) {

        this.url = host;
        this.httpMethod = httpMethod;
        this.type = type;
    }

    @Override
    protected ContentValues adjustedParameter() {

        addParam(KEY_DEVICE_ID, ApiInfo.ANDROID_ID);
        return null;
    }

    @Override
    protected String postJsonString() {
        return null;
    }
}
