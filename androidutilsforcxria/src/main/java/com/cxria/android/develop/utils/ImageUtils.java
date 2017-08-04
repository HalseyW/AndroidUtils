package com.cxria.android.develop.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.IntRange;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 作者: Wushhhhhh
 * @date 创建时间: 2017/8/1
 * @description 描述: 图片相关操作
 */
public class ImageUtils {
    /**
     * bitmap 转 byte 数组
     *
     * @param bitmap         原始图片
     * @param compressFormat 图片格式
     * @return 转换的数组
     */
    public static byte[] bitmapToBytes(final Bitmap bitmap, final Bitmap.CompressFormat compressFormat) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 数组转 bitmap
     *
     * @param bytes 图片信息数组
     * @return 转换后的 bitmap
     */
    public static Bitmap bytesToBitmap(final byte[] bytes) {
        return (bytes == null || bytes.length == 0) ? null : BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * 将 bitmap 保存到文件
     *
     * @param bitmap  需要保存的 bitmap
     * @param file    文件
     * @param format  保存格式
     * @param recycle 是否需要回收
     * @return 是否保存成功
     */
    public static boolean saveBitmap(final Bitmap bitmap, final File file, final Bitmap.CompressFormat format, final boolean recycle) {
        OutputStream os = null;
        boolean ret = false;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            ret = bitmap.compress(format, 100, os);
            if (recycle && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 将 Bitmap 保存在文件中并返回 Uri
     *
     * @param bitmap   需要保存的 Bitmap
     * @param file     保存的文件
     * @param fileName 文件名
     * @return Bitmap 文件的 Uri
     */
    public static Uri saveBitmapToFileReturnUri(Bitmap bitmap, File file, String fileName) {
        if (!file.exists()) {
            file.mkdir();
        }
        File img = new File(file.getAbsolutePath() + "/" + fileName + ".jpg");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把 bitmap 按照指定质量压缩「指定质量」
     *
     * @param bitmap  需要压缩的bitmap
     * @param quality 质量
     * @param recycle 是否需要回收
     * @return 被压缩后的 bitmap
     */
    public static Bitmap compressBitmapByQuality(final Bitmap bitmap, @IntRange(from = 0, to = 100) final int quality, final boolean recycle) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        if (recycle && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * 将 bitmap 按质量压缩「指定最大图片大小」
     *
     * @param bitmap      需要压缩的 bitmap
     * @param maxByteSize 指定的最大图片大小
     * @param recycle     是否需要回收
     * @return 被压缩的 bitmap
     */
    public static Bitmap compressBitmapByQuality(final Bitmap bitmap, final long maxByteSize, final boolean recycle) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        while (baos.toByteArray().length > maxByteSize && quality > 0) {
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality -= 5, baos);
        }
        if (quality < 0) return null;
        byte[] bytes = baos.toByteArray();
        if (recycle && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * 根据路径图片获得并压缩返回bitmap
     *
     * @param filePath  文件路径
     * @param reqWidth  要求宽度
     * @param reqHeight 要求高度
     * @return 裁剪后的图片
     */
    public static Bitmap compressBitmapByRequest(String filePath, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}