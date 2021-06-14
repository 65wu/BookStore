package com.example.bookstore.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

// 测试是否联网
public class NetworkUtil {
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
