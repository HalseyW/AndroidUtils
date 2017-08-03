package com.cxria.android.utils.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * @author 作者: Wushhhhhh
 * @date 创建时间: 2017/7/31
 * @description 描述: 屏幕相关工具类
 */
public class ScreenUtils {
    /**
     * 获取屏幕绝对宽度「单位：px」
     *
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕绝对高度「单位：px」
     *
     * @param context 上下文
     * @return 屏幕高度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕截图
     *
     * @param activity 当前 Activity
     * @return 屏幕截图
     */
    public static Bitmap getScreenShot(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Bitmap screenShotBitmap = Bitmap.createBitmap(bitmap, 0, 0, dm.widthPixels, dm.heightPixels);
        view.destroyDrawingCache();
        return screenShotBitmap;
    }
}