package com.jackpan.googleversioncheck;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleVersionCheck  implements  RequestResponseListener {

    private Context context;
    private RequestHandler requestHandler;
    private DoneListener doneListener;
    private static String url;

    public String packageName;

    public GoogleVersionCheck(Context context) {
        this.context = context;
        requestHandler = new RequestHandler();
        packageName = context.getPackageName();

    }

    public void check(DoneListener listener) {
        if (context == null || listener == null) return;

        doneListener = listener;

        String host = "https://play.google.com/store/apps/details?id=%s";


        ApiSetting setting = new ApiSetting(String.format(host, packageName), HttpMethod.GET, 111111);

        requestHandler.sendRequest(setting, this);

        this.url = String.format(host, packageName);
    }

    public static void checkOnce(Context context, DoneListener listener) {
        GoogleVersionCheck checker = new GoogleVersionCheck(context);
        checker.check(listener);
    }

    public Intent openMartketIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
    }

    public void compareVersion(String html) {
        if (html == null || doneListener == null) return;

        String onlineVersion = getVersionFromHtml(html);

        String appVersion = getAppVersion();

        if (onlineVersion == null || appVersion == null) return;

        if (isHeigherVersion(appVersion, onlineVersion)) doneListener.onHasNewVersion();
        else if (!isHeigherVersion(appVersion, onlineVersion)) doneListener.onLatestVersion();

    }

    public boolean isHeigherVersion(String oldVersion, String newVersion) {
        try {

            String[] olds = oldVersion.split("\\.");
            String[] news = newVersion.split("\\.");
            for (int i = 0; i < olds.length; i++) {
                if (news.length <= i) break;

                int o = Integer.valueOf(olds[i]);
                int n = Integer.valueOf(news[i]);


                if (n > o) {
                    return true;
                } else if (n == o) {
                    continue;
                } else if (n < o) {
                    return false;
                }
            }

            if (news.length > olds.length) return true;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    public String getAppVersion() {
        String appVersion = null;
        try {
            appVersion = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

            doneListener.onError();
        } finally {
            return appVersion;
        }
    }

    public String getVersionFromHtml(String html) {
        Pattern p = Pattern.compile("\"softwareVersion\">\\s*([\\d.]*)\\s*</div>");
        Matcher m = p.matcher(html);

        if (m.find()) return m.group(1);
        else {
            doneListener.onError();
            return null;
        }
    }


    @Override
    public void requesting(RequestSetting setting) {
        if (doneListener != null) doneListener.onRequesting();


    }

    @Override
    public void requestComplete(RequestSetting setting, String result) {
        if (result == null || "".equals(result)) {
            if (doneListener != null) doneListener.onError();
            return;
        }

        compareVersion(result);

    }

    @Override
    public void requestFault(RequestSetting setting) {
        if (doneListener != null) doneListener.onConnectionFailed();

    }

    @Override
    public void requestDone(RequestSetting setting) {

    }

    public static class DoneAdapter implements DoneListener {

        @Override
        public void onError() {

        }

        @Override
        public void onRequesting() {

        }

        @Override
        public void onHasNewVersion() {

        }

        @Override
        public void onLatestVersion() {

        }

        @Override
        public void onConnectionFailed() {

        }
    }

    /**
     * 檢查 網址是否正確
     */
    public static String getUrl() {
        return url;
    }
    public static  boolean checkGoogleService(Context context,GoogleServiceResponse googleServiceResponse){
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(context);
        if(result != ConnectionResult.SUCCESS) {
            googleServiceResponse.getErrorＭessage(googleAPI.getErrorString(result));
            return false;
        }

        return true;
    }

}