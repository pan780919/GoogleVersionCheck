package com.jackpan.googleversioncheck;

public interface DoneListener {

    public void onError();
    public void onRequesting();
    public void onHasNewVersion();
    public void onLatestVersion();
    public void onConnectionFailed();
}
