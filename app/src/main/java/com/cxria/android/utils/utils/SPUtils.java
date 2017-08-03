package com.cxria.android.utils.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author 作者: Wushhhhhh
 * @date 创建时间: 2017/7/31
 * @description 描述: SharedPreference 工具类
 */
public class SPUtils {
    /**
     * SharedPreference ID
     */
    private static final String SHARED_PREFERENCE_ID = "android_shared_preference_utils";

    /**
     * 获取SharedPreferences
     *
     * @return SharedPreference
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCE_ID, Context.MODE_PRIVATE);
    }

    /**
     * 保存 String
     *
     * @param context 上下文
     * @param key     键名
     * @param value   值
     */
    public static void saveString(Context context, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 根据 key 获取 String
     *
     * @param context 上下文
     * @param key     键名
     * @return String
     */
    public static String getString(Context context, String key) {
        return getSharedPreferences(context).getString(key, null);
    }

    /**
     * 保存 int
     *
     * @param context 上下文
     * @param key     键名
     * @param value   值
     */
    public static void saveInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 根据 key 获取 int
     *
     * @param context 上下文
     * @param key     键名
     * @return int
     */
    public static int getInt(Context context, String key) {
        return getSharedPreferences(context).getInt(key, -1);
    }

    /**
     * 根据 key 删除某条数据
     *
     * @param context 上下文
     * @param key     键名
     */
    public static void remove(Context context, String key) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 删除所有数据
     *
     * @param context 上下文
     */
    public static void clear(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.apply();
    }
}