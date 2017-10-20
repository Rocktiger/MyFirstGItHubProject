package com.nb.mengbiao.myfirstgithubproject;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        this.savedInstanceState = savedInstanceState;
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        plantArr = getResources().getStringArray(R.array.planets_array);
        initFramgent();
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, plantArr));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
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
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(HomeFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(plantArr[position]);
        drawerLayout.closeDrawer(mDrawerList);
    }
}
