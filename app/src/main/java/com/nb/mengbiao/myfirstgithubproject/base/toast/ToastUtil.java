package com.nb.mengbiao.myfirstgithubproject.base.toast;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.nb.mengbiao.myfirstgithubproject.MyApplication;


/**
 * Created by mengbiao on 2017/10/24.
 */

public class ToastUtil {
    private static IToast mToast;

    /**
     * 使用Application的Toast
     * 建议全局使用该方法弹窗Toast
     *
     * @param msg
     */
    public static void showToast(String msg) {
        showToast(MyApplication.getInstance(), msg);
    }

    protected static void showToast(Context context, String msg) {
        if (context == null || TextUtils.isEmpty(msg)) {
            return;
        }
        if (mToast == null) {
            mToast = ToastCompat.makeText(context, msg, Toast.LENGTH_LONG);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
