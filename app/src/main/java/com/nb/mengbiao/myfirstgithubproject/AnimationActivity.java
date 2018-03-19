package com.nb.mengbiao.myfirstgithubproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.nb.mengbiao.myfirstgithubproject.util.StatusBarUtil;

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
    }

    private void intanimation() {
//        ImageView iv_shade = (ImageView) findViewById(R.id.iv_shade);
//        Animation translateAnimation = new TranslateAnimation(0,500,0,500);
//        translateAnimation.setDuration(5000);
//        translateAnimation.setRepeatMode(Animation.RESTART);
//        translateAnimation.setRepeatCount(100);
//        iv_shade.startAnimation(translateAnimation);
    }
}
