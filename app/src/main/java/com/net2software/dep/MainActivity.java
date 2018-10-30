package com.net2software.dep;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.viven.fragmentstatemanager.FragmentStateManager;

public class MainActivity extends AppCompatActivity {

    FragmentStateManager fragmentStateManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int position = getNavPositionFromMenuItem(item);
            if (position != -1) {
                fragmentStateManager.changeFragment(getNavPositionFromMenuItem(item));
                return true;
            }

            return false;
        }

    };

    private BottomNavigationView.OnNavigationItemReselectedListener
            mOnNavigationItemReselectedListener =
            new BottomNavigationView.OnNavigationItemReselectedListener() {
                @Override
                public void onNavigationItemReselected(@NonNull MenuItem item) {
                    int position = getNavPositionFromMenuItem(item);
                    if (position != -1) {
                        fragmentStateManager.removeFragment(position);
                        fragmentStateManager.changeFragment(position);
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout content = findViewById(R.id.content);
        fragmentStateManager = new FragmentStateManager(content, getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                // A switch case should be here for showing different fragments for
                // different positions which is omitted for simplicity
                switch (position){
                    case 0:

                        return new OneFragment();
                    case 1:

                        return new TwoFragment();
                    case 2:
                        return new ThreeFragment();
                    default:
                        return null;
                }
            }
        };

        if (savedInstanceState == null) {
            fragmentStateManager.changeFragment(0);
        }


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setOnNavigationItemReselectedListener(mOnNavigationItemReselectedListener);
    }


    int getNavPositionFromMenuItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                return 0;
            case R.id.navigation_myorder:
                return 1;
            case R.id.navigation_profil:
                return 2;
            default:
                return -1;
        }
    }
}