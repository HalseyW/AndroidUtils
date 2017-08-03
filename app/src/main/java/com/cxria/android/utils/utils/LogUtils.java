package com.cxria.android.utils.utils;

import android.util.Log;

/**
 * @author 作者: Wushhhhhh
 * @date 创建时间: 2017/7/31
 * @description 描述: Log 工具类
 */
public class LogUtils {
    private static final String TAG = "wushhhhhh";

    private static boolean isDebug = true;

    /**
     * info log
     *
     * @param text log 文字
     */
    public static void I(String text) {
        if (isDebug) {
            Log.i(TAG, text);
        }
    }
}