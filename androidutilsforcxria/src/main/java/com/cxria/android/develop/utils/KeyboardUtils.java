package com.cxria.android.develop.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * @author 作者: Wushhhhhh
 * @date 创建时间: 2017/8/2
 * @description 描述: 软键盘相关操作工具类。避免输入法面板遮挡，在 manifest 的 activity 中设置 android:windowSoftInputMode="adjustPan"
 */
public class KeyboardUtils {
    /**
     * 软键盘状态 toggle 改变
     *
     * @param context 上下文
     */
    public static void toggleKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }
}
