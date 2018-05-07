package com.nb.mengbiao.myfirstgithubproject;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nb.mengbiao.myfirstgithubproject.base.ScreenUtils;
import com.nb.mengbiao.myfirstgithubproject.base.toast.ToastUtil;
import com.nb.mengbiao.myfirstgithubproject.tablayout.FlowLayout;
import com.nb.mengbiao.myfirstgithubproject.tablayout.TagAdapter;
import com.nb.mengbiao.myfirstgithubproject.tablayout.TagFlowLayout;
import com.nb.mengbiao.myfirstgithubproject.util.StatusBarUtil;
import com.nb.mengbiao.myfirstgithubproject.view.CommentDetailView;
import com.nb.mengbiao.myfirstgithubproject.view.CountEditText;
import com.nb.mengbiao.myfirstgithubproject.view.SmileRatingView;

import cn.dreamtobe.kpswitch.util.KPSwitchConflictUtil;
import cn.dreamtobe.kpswitch.widget.KPSwitchRootRelativeLayout;

/**
 * Created by mengbiao on 2018/3/16.
 */

public class AnimationActivity extends AppCompatActivity {
    private RelativeLayout rootRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        StatusBarUtil.setTransparentForImageView(this, null);
        rootRelativeLayout = (RelativeLayout) findViewById(R.id.kp_rl);
        intanimation();
        intTagFlow();
        initDeatil();
        initCountEditText();
    }

    private void initDeatil() {
        CommentDetailView cdv_deatil = (CommentDetailView) findViewById(R.id.cdv_deatil);
        cdv_deatil.addTagView("接驾准时、车技高超、服务热情、干净整洁、活地图");
        cdv_deatil.addLineView();
        cdv_deatil.addSuggestView("很好的乘车体验，感谢司机热情服务");

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
            ToastUtil.showToast(ratingGrade + "");

        });
//        ImageView iv_shade = (ImageView) findViewById(R.id.iv_shade);
//        Animation translateAnimation = new TranslateAnimation(0,500,0,500);
//        translateAnimation.setDuration(5000);
//        translateAnimation.setRepeatMode(Animation.RESTART);
//        translateAnimation.setRepeatCount(100);
//        iv_shade.startAnimation(translateAnimation);
    }

    private void initCountEditText() {
        ImageView iv_dasa = (ImageView) findViewById(R.id.iv_dasa);
        CountEditText cet_suees = (CountEditText) findViewById(R.id.cet_suees);
        EditText count = cet_suees.getEditText();
        // KPSwitchConflictUtil.attach(rootRelativeLayout, mPlusIv, count);
        FrameLayout.LayoutParams tv_tag_params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        count.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //获取当前界面可视部分
                AnimationActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight = AnimationActivity.this.getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                int heightDifference = screenHeight - r.bottom;
                int margin = ScreenUtils.dip2px(AnimationActivity.this, heightDifference);
                tv_tag_params.bottomMargin = margin;
                rootRelativeLayout.setLayoutParams(tv_tag_params);
                if (heightDifference > 500) {
                    iv_dasa.setVisibility(View.GONE);
                }else{
                    new Handler().postDelayed(() -> {
                        iv_dasa.setVisibility(View.VISIBLE);
                    }, 200);
                }
                Log.d("Keyboard Size", "Size: " + heightDifference);

            }
        });
    }
}
