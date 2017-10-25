package com.nb.mengbiao.myfirstgithubproject;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mengbiao on 2017/10/20.
 */

public class HomeFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";
    private TextView mTextMessage;
    private View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.home_fragment, container, false);
        mTextMessage = (TextView) rootView.findViewById(R.id.message);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void setText(String text) {
        if (!TextUtils.isEmpty(text))
            mTextMessage.setText(text);
    }
}
