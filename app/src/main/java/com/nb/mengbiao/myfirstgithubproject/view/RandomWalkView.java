package com.nb.mengbiao.myfirstgithubproject.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.nb.mengbiao.myfirstgithubproject.R;

import java.util.Random;

/**
 * Created by mengbiao on 2018/3/16.
 */

public class RandomWalkView extends View {
    private final Random mRandom = new Random();
    private Bitmap mBitmap;

    public RandomWalkView(Context context) {
        super(context);
    }

    public RandomWalkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    class Ball {
        int radius; // 半径
        float cx;   // 圆心
        float cy;   // 圆心
        float vx; // X轴速度
        float vy; // Y轴速度
        Paint mPaint;

        // 移动
        void move() {
            //向角度的方向移动，偏移圆心
            cx += vx;
            cy += vy;
        }

        int left() {
            return (int) (cx - radius);
        }

        int right() {
            return (int) (cx + radius);
        }

        int bottom() {
            return (int) (cy + radius);
        }

        int top() {
            return (int) (cy - radius);
        }

    }

    private int mCount = 1;   // 小球个数
    private int maxRadius;  // 小球最大半径
    private int minRadius; // 小球最小半径
    private int minSpeed = 5; // 小球最小移动速度
    private int maxSpeed = 20; // 小球最大移动速度

    private int mWidth = 200;
    private int mHeight = 200;

    public Ball mBall;   // 用来保存所有小球的数组


    public RandomWalkView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBall = new Ball();
        mBitmap =BitmapFactory.decodeResource(getResources(), R.drawable.icon_shade);

        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        // 设置想要的大小
        int newWidth = 200;
        int newHeight = 300;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix,
                true);
        // 设置速度
        float speedX = (minSpeed) / 10f;
        float speedY = (minSpeed) / 10f;

        mBall.vx = mRandom.nextBoolean() ? speedX : -speedX;
        mBall.vy = mRandom.nextBoolean() ? speedY : -speedY;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = resolveSize(mWidth, widthMeasureSpec);
        mHeight = resolveSize(mHeight, heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
        maxRadius = mWidth / 12;
        minRadius = mHeight / 2;
        mBall.radius = 100;
//            mBalls[i].mass = (int) (Math.PI * mBalls[i].radius * mBalls[i].radius);
        // 初始化圆心的位置， x最小为 radius， 最大为mwidth- radius
        mBall.cx = mRandom.nextInt(mWidth - mBall.radius) + mBall.radius;
        mBall.cy = mRandom.nextInt(mHeight - mBall.radius) + mBall.radius;

    }

    private float mScale; //图片的缩放比例

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long startTime = System.currentTimeMillis();

        Ball ball1 = mBall;
        canvas.drawBitmap(mBitmap, ball1.cx, ball1.cy, new Paint());

        // 球碰撞边界
        Ball ball2 = mBall;
        collisionDetectingAndChangeSpeed(ball2); // 碰撞边界的计算
        ball2.move(); // 移动


        long stopTime = System.currentTimeMillis();
        long runTime = stopTime - startTime;
        // 16毫秒执行一次
        postInvalidateDelayed(Math.abs(runTime - 16));
    }

    // 判断球是否碰撞碰撞边界
    public void collisionDetectingAndChangeSpeed(Ball ball) {
        int left = getLeft();
        int top = getTop();
        int right = getRight();
        int bottom = getBottom();

        float speedX = ball.vx;
        float speedY = ball.vy;

        // 碰撞左右，X的速度取反。 speed的判断是防止重复检测碰撞，然后黏在墙上了=。=
        if (ball.left() <= left && speedX < 0) {
            ball.vx = -ball.vx;
        } else if (ball.top() <= top && speedY < 0) {
            ball.vy = -ball.vy;
        } else if (ball.right() >= right && speedX > 0) {
            ball.vx = -ball.vx;
        } else if (ball.bottom() >= bottom && speedY > 0) {
            ball.vy = -ball.vy;
        }
    }

}
