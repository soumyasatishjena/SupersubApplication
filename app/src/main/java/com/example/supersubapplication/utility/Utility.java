package com.example.supersubapplication.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    public static final String YOUTUBE_API_KEY = "AIzaSyC9Vb2_G6nJMfZ-T4QeJYeEwyBMm73Z3MQ";
    public static final String BASE_URL = "https://run.mocky.io/v3/";
    private final static String expression =
            "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D" +
                    "|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F" +
                    "|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

    public static boolean isInternetAvailable(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if(netInfo != null) {
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                if (netInfo.isConnected()) {
                    haveConnectedWifi = true;
                }
            }
            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (netInfo.isConnected()) {
                    haveConnectedMobile = true;
                }
            }
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


    public static String getVideoId(String videoUrl) {
        String data = "";
        if (videoUrl == null || videoUrl.trim().length() <= 0){
            return null;
        }
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(videoUrl);
        try {
            if (matcher.find())
                        data = matcher.group();
            return data;
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
