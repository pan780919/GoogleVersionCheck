package com.jackpan.googleversioncheck;

public abstract  class ApiType {

    public int type = 0;
    public String method = "";

    public ApiType(int type, String method) {
        this.type = type;
        this.method = method;
    }
}
