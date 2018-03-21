package com.nb.mengbiao.myfirstgithubproject.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nb.mengbiao.myfirstgithubproject.R;

/**
 * Created by mengbiao on 2018/3/21.
 */

public class CountEditText extends RelativeLayout {
    private EditText et_input;
    private TextView tv_input_count;

    public CountEditText(Context context) {
        super(context);
        init(context);
    }

    public CountEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CountEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private static final int REMARK_MUM_MAX = 50;  // 限定备注长度

    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_count_edittext, this, true);
        et_input = (EditText) findViewById(R.id.et_input);
        tv_input_count = (TextView) findViewById(R.id.tv_input_count);
        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null) return;

                int num = s.toString().trim().length();

                //限定备注长度为 REMARK_MUM_MAX
                if (num > REMARK_MUM_MAX) {
                    String remark = et_input.getText().toString().substring(0, 50);
                    et_input.setText(remark);
                    et_input.setSelection(remark.length());
                    num = REMARK_MUM_MAX;
                }
                tv_input_count.setText(String.format(context.getString(R.string.input_number), num + ""));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
