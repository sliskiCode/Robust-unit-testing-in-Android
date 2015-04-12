package com.code.sliski.ui.activity;

import android.support.v4.app.Fragment;
import com.code.sliski.App;
import com.code.sliski.R;
import com.code.sliski.TestApp;
import com.code.sliski.ui.fragment.LoginFragment;
import com.code.sliski.ui.fragment.UserInfoFragment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("ConstantConditions")
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    Fragment fragment;

    @Before
    public void setUp() throws Exception {
        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .visible()
                .get();
        fragment = mainActivity
                .getSupportFragmentManager()
                .findFragmentById(R.id.container);
    }

    @Test
    @Config(application = App.class)
    public void addFragment_ShouldAddUserIdFragment() throws Exception {
        assertTrue(fragment instanceof LoginFragment);
    }

    @Test
    @Config(application = TestApp.class)
    public void addFragment_ShouldAddUserInfoFragment() throws Exception {
        assertTrue(fragment instanceof UserInfoFragment);
    }
}