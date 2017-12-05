package com.nb.mengbiao.myfirstgithubproject.base;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nb.mengbiao.myfirstgithubproject.MyApplication;
import com.nb.mengbiao.myfirstgithubproject.R;


/**
 * Base TitleBar wrapper
 * <p>
 * this is added from 1.6.1 version
 * <p>
 * Created by mengbiao on 2017/11/1.
 */

public class TitleBar extends FrameLayout {

    /**
     * back arrow,finish activity or go to target activity
     */
    private ImageView iv_titlebar_back;
    /**
     * title right view container,you can custom right view
     */
    private FrameLayout fl_right_container;
    /**
     * title view,show title
     */
    private TextView tv_left_title;

    private TextView tv_right_btn;
    private Context mContext;

    public TitleBar(Context context) {
        super(context);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_titlebar, this);
        iv_titlebar_back = (ImageView) findViewById(R.id.btn_Left);
        fl_right_container = (FrameLayout) findViewById(R.id.fl_right_container);
        tv_left_title = (TextView) findViewById(R.id.tv_head_title);
        tv_right_btn = (TextView) findViewById(R.id.tv_head_text_button);
        this.mContext = context;
        // Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Light.ttf");
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Medium.ttf");
        // 设置字体类型、
        tv_left_title.getPaint().setTypeface(typeface);
        tv_left_title.getPaint().setFakeBoldText(true);

    }

    private View.OnClickListener mBackListener;

    /**
     * set Back View Listener
     *
     * @param listener
     */
    public void setBackListener(View.OnClickListener listener) {
        mBackListener = listener;
        iv_titlebar_back.setOnClickListener(mBackListener);
    }

    private View.OnClickListener mTextButtonListener;

    /**
     * set RightTextButton Listener
     *
     * @param listener
     */
    public void setmTextButtonListener(View.OnClickListener listener) {
        mTextButtonListener = listener;
        tv_right_btn.setOnClickListener(mTextButtonListener);
    }

    public void setBackImg(int imgResId) {
        iv_titlebar_back.setVisibility(VISIBLE);
        iv_titlebar_back.setImageResource(imgResId);
    }

    /**
     * set Title View by string
     *
     * @param title
     */
    public void setLeftTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            tv_left_title.setVisibility(VISIBLE);
            tv_left_title.setText(title);
        } else {
            tv_left_title.setVisibility(GONE);
        }
    }

    /**
     * set Title View by resource id
     *
     * @param titleResId
     */
    public void setLeftTitle(int titleResId) {
        tv_left_title.setText(getResources().getText(titleResId));
    }

    /**
     * set Right TextButton by string
     *
     * @param str
     */
    public void setRightTextButtonText(String str) {
        if (!TextUtils.isEmpty(str)) {
            tv_right_btn.setVisibility(VISIBLE);
            tv_right_btn.setText(str);
        } else {
            tv_right_btn.setVisibility(GONE);
        }
    }


    /**
     * add a custom view to right container view ,this view maybe a view or a ViewGroup
     *
     * @param view
     */
    public void addRightContainerChildView(View view) {
        fl_right_container.removeAllViews();
        fl_right_container.addView(view);
        if (fl_right_container.getVisibility() == GONE)
            fl_right_container.setVisibility(VISIBLE);
    }


    /**
     * get title View
     *
     * @return
     */
    public TextView getTitleView() {
        return tv_left_title;
    }

    /**
     * get RightTextButton view
     *
     * @return
     */
    public TextView getRightTextButton() {
        return tv_right_btn;
    }

    public ImageView getBackImgView() {
        return iv_titlebar_back;
    }

    /**
     * get right view container
     * <p>
     * note:the view default visibility is gone
     *
     * @return
     */
    public FrameLayout getRightContainer() {
        return fl_right_container;
    }

    /**
     * release  listener ,this can unbundled with activity,in case of activity memory leak
     */
    public void onDestroy() {
        if (null != mBackListener) {
            mBackListener = null;
            iv_titlebar_back.setOnClickListener(null);
        }
        if (null != mTextButtonListener) {
            mTextButtonListener = null;
            tv_right_btn.setOnClickListener(null);
        }
    }
}
