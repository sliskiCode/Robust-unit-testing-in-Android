package com.code.sliski.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import com.code.sliski.ui.R;
import com.code.sliski.ui.fragment.UserIdFragment;
import com.code.sliski.ui.fragment.UserInfoFragment;
import com.code.sliski.ui.preference.PrefManager;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            addFragment();
        }
    }

    private void addFragment() {
        PrefManager prefManager = new PrefManager(getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE));
        long userId = prefManager.userId().getOr(0L);
        Fragment fragment;
        if (userId == 0L) {
            fragment = new UserIdFragment();
        } else {
            fragment = new UserInfoFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        for (Fragment frag : fm.getFragments()) {
            if (frag.isVisible()) {
                FragmentManager childFm = frag.getChildFragmentManager();
                if (childFm.getBackStackEntryCount() > 0) {
                    childFm.popBackStack();
                    return;
                }
            }
        }
        super.onBackPressed();
    }
}
