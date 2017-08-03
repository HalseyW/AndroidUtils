package com.cxria.android.utils.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * @author 作者: Wushhhhhh
 * @date 创建时间: 2017/8/2
 * @description 描述: 网络相关工具类
 * 注意权限 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>; <uses-permission android:name="android.permission.INTERNET"/>
 */
public class NetworkUtils {
    private static NetworkInfo getNetworkInfo(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    }

    /**
     * 检测网络是否连接
     *
     * @param context 上下文
     * @return 网络是否连接
     */
    public static boolean isNetworkConnected(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo.isConnected();
    }

    /**
     * 判断是不是 4G
     * 注意添加权限 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
     *
     * @param context 上下文
     * @return 当前网络是否为 4G
     */
    public static boolean is4G(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE;
    }
}
