package com.nb.mengbiao.myfirstgithubproject.view;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nb.mengbiao.myfirstgithubproject.R;

/**
 * Created by mengbiao on 2018/3/20.
 */

public class SmileRatingView extends LinearLayout implements View.OnClickListener {

    private ImageView ratingOne, ratingTwo, ratingThird, ratingFour, ratingFive;
    private TranslateAnimation translateAnimation1,translateAnimation2,translateAnimation3,translateAnimation4,translateAnimation5;
    private final int delayTime =15;
    public SmileRatingView(Context context) {
        super(context);
        init(context);
    }

    public SmileRatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SmileRatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    //评分等级
    private int ratingGrade = 5;

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_smilerating, this, true);
        ratingOne = (ImageView) findViewById(R.id.rating_one);
        ratingTwo = (ImageView) findViewById(R.id.rating_two);
        ratingThird = (ImageView) findViewById(R.id.rating_third);
        ratingFour = (ImageView) findViewById(R.id.rating_four);
        ratingFive = (ImageView) findViewById(R.id.rating_five);
        initEvent();
        initAnimation();
    }
    private void initEvent() {
        ratingOne.setOnClickListener(this);
        ratingTwo.setOnClickListener(this);
        ratingThird.setOnClickListener(this);
        ratingFour.setOnClickListener(this);
        ratingFive.setOnClickListener(this);
    }
    private void initAnimation() {
        translateAnimation1 = new TranslateAnimation(0, 0, 0, 30);
        translateAnimation1.setDuration(80);
        translateAnimation1.setRepeatCount(1);
        translateAnimation1.setRepeatMode(translateAnimation1.REVERSE);

        translateAnimation2 = new TranslateAnimation(0, 0, 0, 30);
        translateAnimation2.setDuration(80);
        translateAnimation2.setRepeatCount(1);
        translateAnimation2.setRepeatMode(translateAnimation1.REVERSE);

        translateAnimation3 = new TranslateAnimation(0, 0, 0, 30);
        translateAnimation3.setDuration(80);
        translateAnimation3.setRepeatCount(1);
        translateAnimation3.setRepeatMode(translateAnimation1.REVERSE);

        translateAnimation4 = new TranslateAnimation(0, 0, 0, 30);
        translateAnimation4.setDuration(80);
        translateAnimation4.setRepeatCount(1);
        translateAnimation4.setRepeatMode(translateAnimation1.REVERSE);

        translateAnimation5 = new TranslateAnimation(0, 0, 0, 30);
        translateAnimation5.setDuration(80);
        translateAnimation5.setRepeatCount(1);
        translateAnimation5.setRepeatMode(translateAnimation1.REVERSE);
    }

    private void startAnimation(int ratingGrade) {
        clearAnimation();
        switch (ratingGrade) {
            case 1:
                ratingOne.startAnimation(translateAnimation1);
                break;
            case 2:
                ratingTwo.startAnimation(translateAnimation2);
                new Handler().postDelayed(() -> {
                    ratingOne.startAnimation(translateAnimation1);
                }, delayTime);
                break;
            case 3:
                ratingThird.startAnimation(translateAnimation3);
                new Handler().postDelayed(() -> {
                    ratingTwo.startAnimation(translateAnimation2);
                }, delayTime);
                new Handler().postDelayed(() -> {
                    ratingOne.startAnimation(translateAnimation1);
                }, delayTime*2);
                break;
            case 4:
                ratingFour.startAnimation(translateAnimation4);
                new Handler().postDelayed(() -> {
                    ratingThird.startAnimation(translateAnimation3);
                }, delayTime);
                new Handler().postDelayed(() -> {
                    ratingTwo.startAnimation(translateAnimation2);
                }, delayTime*2);
                new Handler().postDelayed(() -> {
                    ratingOne.startAnimation(translateAnimation1);
                }, delayTime*3);
                break;
            case 5:
                ratingFive.startAnimation(translateAnimation5);
                new Handler().postDelayed(() -> {
                    ratingFour.startAnimation(translateAnimation4);
                }, delayTime);
                new Handler().postDelayed(() -> {
                    ratingThird.startAnimation(translateAnimation3);
                }, delayTime*2);
                new Handler().postDelayed(() -> {
                    ratingTwo.startAnimation(translateAnimation2);
                }, delayTime*3);
                new Handler().postDelayed(() -> {
                    ratingOne.startAnimation(translateAnimation1);
                }, delayTime*4);
                break;
            default:
                break;
        }

    }

    public void clearAnimation() {
        ratingOne.clearAnimation();
        ratingTwo.clearAnimation();
        ratingThird.clearAnimation();
        ratingFour.clearAnimation();
        ratingFive.clearAnimation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rating_one:
                ratingOne.setImageDrawable(getResources().getDrawable(R.drawable.rating_anger));
                ratingTwo.setImageDrawable(getResources().getDrawable(R.drawable.rating_sad_gray));
                ratingThird.setImageDrawable(getResources().getDrawable(R.drawable.rating_normal_face_gray));
                ratingFour.setImageDrawable(getResources().getDrawable(R.drawable.rating_smile_gray));
                ratingFive.setImageDrawable(getResources().getDrawable(R.drawable.rating_risus_gray));
                if (ratingGrade != 1) {
                    ratingGrade = 1;
                    if (onSmileRatingChangeListener != null) {
                        onSmileRatingChangeListener.SmileRatingChange(ratingGrade);
                    }
                    startAnimation(ratingGrade);

                }
                break;
            case R.id.rating_two:
                ratingOne.setImageDrawable(getResources().getDrawable(R.drawable.rating_sad));
                ratingTwo.setImageDrawable(getResources().getDrawable(R.drawable.rating_sad));
                ratingThird.setImageDrawable(getResources().getDrawable(R.drawable.rating_normal_face_gray));
                ratingFour.setImageDrawable(getResources().getDrawable(R.drawable.rating_smile_gray));
                ratingFive.setImageDrawable(getResources().getDrawable(R.drawable.rating_risus_gray));
                if (ratingGrade != 2) {
                    ratingGrade = 2;
                    if (onSmileRatingChangeListener != null) {
                        onSmileRatingChangeListener.SmileRatingChange(ratingGrade);
                    }

                    startAnimation(ratingGrade);

                }
                break;
            case R.id.rating_third:
                ratingOne.setImageDrawable(getResources().getDrawable(R.drawable.rating_normal_face));
                ratingTwo.setImageDrawable(getResources().getDrawable(R.drawable.rating_normal_face));
                ratingThird.setImageDrawable(getResources().getDrawable(R.drawable.rating_normal_face));
                ratingFour.setImageDrawable(getResources().getDrawable(R.drawable.rating_smile_gray));
                ratingFive.setImageDrawable(getResources().getDrawable(R.drawable.rating_risus_gray));
                if (ratingGrade != 3) {
                    ratingGrade = 3;
                    if (onSmileRatingChangeListener != null) {
                        onSmileRatingChangeListener.SmileRatingChange(ratingGrade);
                    }

                    startAnimation(ratingGrade);

                }
                break;
            case R.id.rating_four:
                ratingOne.setImageDrawable(getResources().getDrawable(R.drawable.rating_smile));
                ratingTwo.setImageDrawable(getResources().getDrawable(R.drawable.rating_smile));
                ratingThird.setImageDrawable(getResources().getDrawable(R.drawable.rating_smile));
                ratingFour.setImageDrawable(getResources().getDrawable(R.drawable.rating_smile));
                ratingFive.setImageDrawable(getResources().getDrawable(R.drawable.rating_risus_gray));
                if (ratingGrade != 4) {
                    ratingGrade = 4;
                    if (onSmileRatingChangeListener != null) {
                        onSmileRatingChangeListener.SmileRatingChange(ratingGrade);
                    }

                    startAnimation(ratingGrade);

                }
                break;
            case R.id.rating_five:
                ratingOne.setImageDrawable(getResources().getDrawable(R.drawable.rating_risus));
                ratingTwo.setImageDrawable(getResources().getDrawable(R.drawable.rating_risus));
                ratingThird.setImageDrawable(getResources().getDrawable(R.drawable.rating_risus));
                ratingFour.setImageDrawable(getResources().getDrawable(R.drawable.rating_risus));
                ratingFive.setImageDrawable(getResources().getDrawable(R.drawable.rating_risus));
                if (ratingGrade != 5) {
                    ratingGrade = 5;
                    if (onSmileRatingChangeListener != null) {
                        onSmileRatingChangeListener.SmileRatingChange(ratingGrade);
                    }

                    startAnimation(ratingGrade);

                }
                break;
            default:
                break;
        }
    }

    OnSmileRatingChangeListener onSmileRatingChangeListener;

    public void setOnSmileRatingChangeListener(OnSmileRatingChangeListener onSmileRatingChangeListener) {
        this.onSmileRatingChangeListener = onSmileRatingChangeListener;
    }

    public interface OnSmileRatingChangeListener {
        void SmileRatingChange(int ratingGrade);
    }
}
