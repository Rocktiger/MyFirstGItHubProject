package com.nb.mengbiao.myfirstgithubproject;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        setContentView(R.layout.activity_main);
        initToolBar("野心");
        this.savedInstanceState = savedInstanceState;
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
    protected void initToolBar(String title) {
        super.initToolBar(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
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
        setTitle(plantArr[position]);
        drawerLayout.closeDrawer(mDrawerList);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
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
        }

    };
}
