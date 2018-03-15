package com.nb.mengbiao.myfirstgithubproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.nb.mengbiao.myfirstgithubproject.util.ScreenUtil;

/**
 * Created by mengbiao on 2017/12/28.
 */

public class CustomImageView extends android.support.v7.widget.AppCompatImageView {
    private Context mContext;
    private int ViewWidth;
    private int ViewHeight;

    private int ParentWight;
    private int ParentHeight;


    private int lastX;
    private int lastY;


    public CustomImageView(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewWidth = getMeasuredWidth();
        ViewHeight = getMeasuredHeight();
        ParentWight = ScreenUtil.getScreenWidthPixels(mContext);
        ParentHeight = ScreenUtil.getScreenHeightPixels(mContext) - ScreenUtil.getStatusHeight(mContext);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                int offsetX = x - lastX;
                int offsetY = y - lastY;

                int left = getLeft() + offsetX;
                int right = getRight() + offsetX;
                int top = getTop() + offsetY;
                int bottom = getBottom() + offsetY;
                if (left < 0) {
                    left = 0;
                    right = left + ViewWidth;
                } else if (right > ParentWight) {
                    right = ParentWight;
                    left = right - ViewWidth;
                }
                if (top < 0) {
                    top = 0;
                    bottom = top + ViewHeight;
                } else if (bottom > ParentWight) {
                    bottom = ParentWight;
                    top = bottom - ViewHeight;
                }
                //调用layout方法来重新放置它的位置
                layout(left, top,
                        right, bottom);
                break;
        }

        return true;
    }
}
