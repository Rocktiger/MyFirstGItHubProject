package com.nb.mengbiao.myfirstgithubproject.base.toast;

import android.view.View;

/**
 * Created by mengbiao on 2017/10/24.
 */

public interface IToast {
    IToast setGravity(int gravity, int xOffset, int yOffset);

    IToast setDuration(int duration);

    IToast setView(View view);

    IToast setMargin(float horizontalMargin, float verticalMargin);

    IToast setText(String text);

    void show();

    void cancel();
}
