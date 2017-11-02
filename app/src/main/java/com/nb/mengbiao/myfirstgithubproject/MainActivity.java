package com.nb.mengbiao.myfirstgithubproject;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nb.mengbiao.myfirstgithubproject.base.BaseToolBarActivity;
import com.nb.mengbiao.myfirstgithubproject.base.toast.ToastUtil;

public class MainActivity extends BaseToolBarActivity {

    private Bundle savedInstanceState;
    private DrawerLayout drawerLayout;
    private ListView mDrawerList;
    private String[] plantArr;
    private Fragment frameLayout;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        getSupportFragmentManager().putFragment(outState, "mContext", frameLayout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        plantArr = getResources().getStringArray(R.array.planets_array);
        initFramgent();
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, plantArr));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    protected void initializeData(Bundle saveInstance) {
        initTitle("野心");
        setHeaderLeft();
        setHeaderRight("保存");
        setHeaderRightView();
        setHeaderLeftImg(R.drawable.new_detele_airport_information);

    }

    /**
     * 全选控件
     */
    private CheckBox checkBox;
    LinearLayout checkAllView;

    private void setHeaderRightView() {
        checkAllView = new LinearLayout(this);
        checkAllView.setOrientation(LinearLayout.HORIZONTAL);

        TextView textView = new TextView(this);
        textView.setText("全选");
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textView.setTextColor(Color.parseColor("#000000"));

        checkBox = new CheckBox(this);
        checkBox.setWidth(20);
        checkBox.setHeight(20);
        checkBox.setButtonDrawable(new ColorDrawable());
        checkBox.setBackground(ContextCompat.getDrawable(this, R.drawable.btn_invoice_type_selector));

        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams checkBoxParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        checkBoxParams.setMargins(18, 0, 0, 0);

        checkAllView.addView(textView, textViewParams);
        checkAllView.addView(checkBox, checkBoxParams);

        toolbar.addRightContainerChildView(checkAllView);

        checkBox.setOnClickListener(v -> ToastUtil.showToast("全选"));
    }

    @Override
    protected void headerLeftBtnHandle() {
        ToastUtil.showToast("我回退了");
    }

    @Override
    protected void headerRightTextBtnHandle() {
        ToastUtil.showToast("我保存了");
    }

    private void initFramgent() {
        if (savedInstanceState != null) {
            frameLayout = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
        }
        if (frameLayout == null) {
            frameLayout = new HomeFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, frameLayout).commit();
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);

        }
    }

    private void selectItem(int position) {
        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        initTitle(plantArr[position]);
        drawerLayout.closeDrawer(mDrawerList);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                //mTextMessage.setText(R.string.title_home);
                ToastUtil.showToast(getString(R.string.title_home));
                return true;
            case R.id.navigation_dashboard:
                //mTextMessage.setText(R.string.title_dashboard);
                ToastUtil.showToast(getString(R.string.title_dashboard));
                return true;
            case R.id.navigation_notifications:
                // mTextMessage.setText(R.string.title_notifications);
                ToastUtil.showToast(getString(R.string.title_notifications));
                return true;
        }
        return false;
    };
}
