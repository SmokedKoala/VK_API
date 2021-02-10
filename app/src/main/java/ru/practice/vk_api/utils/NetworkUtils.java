package ru.practice.vk_api.utils;

import android.net.Uri;
import android.renderscript.ScriptGroup;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

public class NetworkUtils {
    private static final String VK_API_BASE_URL = "https://api.vk.com";
    private static final String VK_USERS_GET = "/method/users.get";
    private static final String PARAM_USER_ID = "user_ids";
    private static final String PARAM_VERSION = "v";
    private static final String ACCESS_TOKEN = "access_token";

    public static URL generateURL(String userId)  {
        Uri builtUri = Uri.parse(VK_API_BASE_URL+VK_USERS_GET)
                .buildUpon().appendQueryParameter(PARAM_USER_ID, userId)
                .appendQueryParameter(PARAM_VERSION, "5.89")
                .appendQueryParameter(ACCESS_TOKEN,
                        "6edf95646edf95646edf9564c96ea9cf2e66edf6edf95640efd38ef96cb97c3d7dfffd2")
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
            Log.i(TAG,url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
;
    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            //получение входного потока данных
            InputStream in = urlConnection.getInputStream();
            //сканнер для считывания данных потока
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
