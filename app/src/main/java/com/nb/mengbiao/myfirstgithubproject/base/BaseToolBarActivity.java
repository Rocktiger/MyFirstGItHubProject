package com.nb.mengbiao.myfirstgithubproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.nb.mengbiao.myfirstgithubproject.R;

/**
 * Created by mengbiao on 2017/10/24.
 */

public class BaseToolBarActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected Toolbar getToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        }
        return toolbar;
    }

    protected void initToolBar(String title) {
        if (getToolbar() == null) {
            return;
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (!TextUtils.isEmpty(title)) {
            TextView textView = (TextView) findViewById(R.id.tv_head_title);
            if (textView == null) {
                setTitle(title);
            } else {
                setTitle(" ");
                textView.setText(title);
            }

        } else {
            setTitle(" ");
        }
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

    }
}
