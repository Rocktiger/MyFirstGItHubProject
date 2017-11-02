package com.nb.mengbiao.myfirstgithubproject.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.nb.mengbiao.myfirstgithubproject.R;

/**
 * Created by mengbiao on 2017/10/24.
 */

public abstract class BaseToolBarActivity extends AppCompatActivity {
    public TitleBar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initializeView();
        initializeData(savedInstanceState);
    }

    /**
     * 1 设置布局
     */
    protected abstract void setContentView();

    /**
     * 2. 初始化布局
     */
    protected void initializeView() {
        if (findViewById(R.id.toolbar) != null) {
            toolbar = (TitleBar) findViewById(R.id.toolbar);
            if (hasBackIcon()) {
                toolbar.getBackImgView().setVisibility(View.VISIBLE);
                //公共左边 回退 需要自定义
                toolbar.setBackListener(v -> {
                    finish();
                });
            } else {
                toolbar.getBackImgView().setVisibility(View.GONE);
            }
        }
    }

    /**
     * 是否有回退功能 * * @return
     */
    protected boolean hasBackIcon() {
        return true;
    }

    /**
     * 3. 初始化ui数据
     */
    protected abstract void initializeData(Bundle saveInstance);


    public void setContentView(int layoutId) {
        setContentView(layoutId, true);
    }

    /**
     * 容器模版 * @param layoutId 内容视图
     * * @param isContainerTitle true 带有toolbar的布局容器 false无toolbar
     */
    protected void setContentView(int layoutId, boolean isContainerTitle) {
        if (isContainerTitle) {
            LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.basetoolbaractivity_layout, null);
            LayoutInflater.from(this).inflate(layoutId, root);
            super.setContentView(root);
        } else {
            super.setContentView(layoutId);
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    protected void initTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            toolbar.setLeftTitle(title);
        }
    }

    /**
     * 设置右边文字按钮
     *
     * @param str
     */
    protected void setHeaderRight(String str) {
        if (!TextUtils.isEmpty(str)) {
            toolbar.setRightTextButtonText(str);
            toolbar.setmTextButtonListener(v -> {
                headerRightTextBtnHandle();
            });
        }
    }

    /**
     * 设置左边回退按钮事件
     */
    protected void setHeaderLeft() {
        toolbar.setBackListener(v -> {
            headerLeftBtnHandle();
        });
    }

    protected void setHeaderLeftImg(int imgId) {
        toolbar.setBackImg(imgId);
    }

    /**
     * 点击右边文字按钮处理事件
     */
    protected void headerRightTextBtnHandle() {

    }

    /**
     * 点击左边图片按钮处理事件
     */
    protected void headerLeftBtnHandle() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toolbar.onDestroy();
    }
}
