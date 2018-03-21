package com.nb.mengbiao.myfirstgithubproject.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nb.mengbiao.myfirstgithubproject.R;
import com.nb.mengbiao.myfirstgithubproject.base.ScreenUtils;

/**
 * Created by mengbiao on 2018/3/21.
 */

public class CommentDetailView extends LinearLayout {
    private LinearLayout ll_refunds;
    private RelativeLayout rlHead;
    private ImageView iv_expand;
    private ImageView comment_detail_line;
    private boolean bolExpand = false;

    public CommentDetailView(Context context) {
        super(context);
        init(context);
    }

    public CommentDetailView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CommentDetailView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_comment_detail, this, true);
        ll_refunds = (LinearLayout) findViewById(R.id.ll_refunds);
        rlHead = (RelativeLayout) findViewById(R.id.rl_head);
        iv_expand = (ImageView) findViewById(R.id.iv_arrow_bottom);
        comment_detail_line = (ImageView) findViewById(R.id.comment_detail_line);
        rlHead.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                comment_detail_line.setVisibility(!bolExpand ? GONE : VISIBLE);
                bolExpand = !bolExpand;
                ll_refunds.setVisibility(ll_refunds.getVisibility() == View.VISIBLE ? GONE : VISIBLE);
                iv_expand.setImageResource(bolExpand ? R.drawable.icon_arrow_top : R.drawable.icon_arrow_bottom);
            }
        });
    }

    final int height = ScreenUtils.dip2px(getContext(), 40);
    final int margin = ScreenUtils.dip2px(getContext(), 16);

    //添加tagview
    public void addTagView(String tag) {
        //设置tagview
        RelativeLayout ll_tag = new RelativeLayout(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
        ll_tag.setLayoutParams(params);

        RelativeLayout.LayoutParams tv_tag_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        tv_tag_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        tv_tag_params.addRule(RelativeLayout.CENTER_VERTICAL);
        tv_tag_params.leftMargin = margin;


        TextView tv_tag = new TextView(getContext());
        tv_tag.setText(tag);
        tv_tag.setTextColor(getResources().getColor(R.color.color_9E9E9E));
        tv_tag.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);

        ll_tag.addView(tv_tag, tv_tag_params);
        ll_refunds.addView(ll_tag);
    }

    public void addLineView() {
        //设置分割线
        ImageView divider = new ImageView(getContext());
        LinearLayout.LayoutParams divider_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.spit_small));
        divider.setBackgroundResource(R.drawable.shape_dash_3);
        divider.setLayerType(LAYER_TYPE_SOFTWARE, null);
        ll_refunds.addView(divider, divider_params);
    }

    public void addSuggestView(String suggest) {
        //设置建议view
        RelativeLayout ll_suggest = new RelativeLayout(getContext());
        LinearLayout.LayoutParams params_suggest = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
        ll_suggest.setLayoutParams(params_suggest);

        RelativeLayout.LayoutParams tv_suggest_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        tv_suggest_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        tv_suggest_params.addRule(RelativeLayout.CENTER_VERTICAL);
        tv_suggest_params.leftMargin = margin;

        TextView tv_suggest = new TextView(getContext());
        tv_suggest.setText(suggest);
        tv_suggest.setTextColor(getResources().getColor(R.color.color_4A4A4A));
        tv_suggest.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        ll_suggest.addView(tv_suggest, tv_suggest_params);
        ll_refunds.addView(ll_suggest);
    }
}
