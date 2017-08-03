package com.cxria.android.utils.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * @author 作者: Wushhhhhh
 * @date 创建时间: 2017/8/1
 * @description 描述: 启动系统相关组件的工具类。如果 targetSdkVersion >= 24，则必须使用 FileProvider 授权通过 Uri 访问图片，
 * 否则会报 FileExposedException「比如唤起系统拍照返回 Uri 并裁剪」。
 */
public class IntentUtils {
    /**
     * 调用系统拍照的 Intent「此方法返回的图片是模糊的缩略图」
     * onActivityResult(int requestCode, int resultCode, Intent data)
     * -> Bitmap bitmap = data.getExtras().getParcelable("data")
     *
     * @param activity    唤起组件的 Activity
     * @param requestCode 请求码
     */
    public static void startImageCaptureForResult(Activity activity, int requestCode) {
        activity.startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), requestCode);
    }

    /**
     * 唤起系统相册选择图片
     * onActivityResult(int requestCode, int resultCode, Intent data)
     * -> Bitmap smallBitmap = ImageUtils.compressBitmapByRequest(FileUtils.getImagePathFromUri(this, data.getData()), 500, 500);
     *
     * @param activity    唤起组件的 Activity
     * @param requestCode 请求码
     */
    public static void startPickImageForResult(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 根据 Uri 裁剪图片
     * onActivityResult(int requestCode, int resultCode, Intent data)
     * -> Bitmap clipBitmap = ImageUtils.compressBitmapByRequest(FileUtils.getImagePathFromUri(this, data.getData()), 500, 500);
     *
     * @param activity    唤起组件的 Activity
     * @param requestCode 请求码
     */
    public static void startClipImageForResult(Activity activity, Uri uri, int requestCode) {
        //注意如果 targetSdkVersion >= 24, 使用 FileProvider 去授权访问
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, requestCode);
    }
}
