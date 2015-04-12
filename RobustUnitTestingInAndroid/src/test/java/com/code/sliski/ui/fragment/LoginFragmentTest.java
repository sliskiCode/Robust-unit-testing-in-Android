package com.code.sliski.ui.fragment;

import android.support.v4.app.Fragment;
import com.code.sliski.R;
import com.code.sliski.ui.activity.MainActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("ConstantConditions")
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LoginFragmentTest {

    private LoginFragmentView userIdFragment;

    @Before
    public void setUp() throws Exception {
        userIdFragment = new LoginFragment();
        FragmentTestUtil.startFragment(((LoginFragment) userIdFragment), MainActivity.class);
    }

    @Test
    public void login_ShouldReplaceFragmentWithUserInfoFragment() throws Exception {
        userIdFragment.setId("2");
        userIdFragment.onClickGoButton();
        Fragment fragment = ((LoginFragment) userIdFragment)
                .getActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.container);
        assertTrue(fragment instanceof UserInfoFragment);
    }
}