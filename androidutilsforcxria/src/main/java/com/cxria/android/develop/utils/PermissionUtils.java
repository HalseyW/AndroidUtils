package com.cxria.android.develop.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * @author 作者: Wushhhhhh
 * @date 创建时间: 2017/7/31
 * @description 描述: Android 6.0 动态权限申请工具类，通过 Runnable 实现与 Activity 完全解耦
 */
public class PermissionUtils {
    /**
     * 请求码
     */
    private static final int PERMISSION_REQUEST_CODE = 1;

    /**
     * 检查单个权限是否被允许
     *
     * @param permission 权限
     * @return permission 是否被允许
     */
    private static boolean checkPermission(Activity activity, String permission) {
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(activity, permission);
    }

    /**
     * 检查权限是否全部被允许
     *
     * @param permissions 申请的权限
     * @return 所有被拒绝的权限的集合
     */
    private static ArrayList<String> checkPermissions(Activity activity, String[] permissions) {
        if (permissions == null) {
            return null;
        }
        ArrayList<String> denyPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
                denyPermissionList.add(permission);
            }
        }
        return denyPermissionList;
    }

    /**
     * 请求权限
     *
     * @param permissionGrantRunnable 当权限全部被允许时执行的 Runnable。
     */
    public static void requestPermission(Activity activity, String[] permissions, Runnable permissionGrantRunnable) {
        if (permissions == null || activity == null) {
            return;
        }
        ArrayList<String> denyPermissionList = checkPermissions(activity, permissions);
        if (denyPermissionList.size() != 0) {
            ActivityCompat.requestPermissions(activity, denyPermissionList.toArray(new String[denyPermissionList.size()]), PERMISSION_REQUEST_CODE);
        } else {
            permissionGrantRunnable.run();
        }
    }

    /**
     * 请求权限结果回调
     *
     * @param requestCode   请求码
     * @param grantResults  权限申请结果的数组，PackageManager.PERMISSION_DENIED 或者 PackageManager.PERMISSION_GRANTED
     * @param grantRunnable 权限全部被允许执行的 Runnable
     * @param denyRunnable  权限被拒绝时执行的 Runnable
     */
    public static void onRequestPermissionsResult(int requestCode, int[] grantResults, Runnable grantRunnable, Runnable denyRunnable) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_DENIED) {
                    denyRunnable.run();
                    return;
                }
            }
            grantRunnable.run();
        } else {
            denyRunnable.run();
        }
    }
}