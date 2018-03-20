package com.nb.mengbiao.myfirstgithubproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.nb.mengbiao.myfirstgithubproject.base.toast.ToastUtil;
import com.nb.mengbiao.myfirstgithubproject.tablayout.FlowLayout;
import com.nb.mengbiao.myfirstgithubproject.tablayout.TagAdapter;
import com.nb.mengbiao.myfirstgithubproject.tablayout.TagFlowLayout;
import com.nb.mengbiao.myfirstgithubproject.util.StatusBarUtil;
import com.nb.mengbiao.myfirstgithubproject.view.SmileRatingView;

/**
 * Created by mengbiao on 2018/3/16.
 */

public class AnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        StatusBarUtil.setTransparentForImageView(this,null);
        intanimation();
        intTagFlow();
    }
    private final String[] COMMENT_HIGH = new String[]{"神准时", "车技高超", "服务热情", "干净整洁", "活地图"};
    private void intTagFlow() {
        final LayoutInflater mInflater = LayoutInflater.from(this);
        TagFlowLayout tfl = (TagFlowLayout) findViewById(R.id.tfl_tag);
        tfl.setAdapter(new TagAdapter<String>(COMMENT_HIGH) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        tfl, false);
                tv.setText(s);
                return tv;
            }
        });
    }

    private void intanimation() {
        SmileRatingView smileRatingView = (SmileRatingView) findViewById(R.id.smile_rating);
        smileRatingView.setOnSmileRatingChangeListener(ratingGrade -> {
            ToastUtil.showToast(ratingGrade+"");

        });
//        ImageView iv_shade = (ImageView) findViewById(R.id.iv_shade);
//        Animation translateAnimation = new TranslateAnimation(0,500,0,500);
//        translateAnimation.setDuration(5000);
//        translateAnimation.setRepeatMode(Animation.RESTART);
//        translateAnimation.setRepeatCount(100);
//        iv_shade.startAnimation(translateAnimation);
    }
}
