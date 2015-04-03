package com.code.sliski.ui.fragment;

import com.code.sliski.R;
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
public class UserInfoFragmentTest {

    private UserInfoFragment mUserInfoFragment;

    @Before
    public void setUp() throws Exception {
        mUserInfoFragment = new UserInfoFragment();
    }

    @Test
    public void fragmentShouldContainsPhoneLayout() throws Exception {
        FragmentTestUtil.startFragment(mUserInfoFragment);
        assertTrue(
                "Fragment should contains one pane layout for phone, but it does not!",
                mUserInfoFragment.getView().findViewById(R.id.preview_container) == null
        );
    }

    @Test
    @Config(qualifiers = "layout-large")
    public void fragmentShouldContainsTwoPaneLayout() throws Exception {
        FragmentTestUtil.startFragment(mUserInfoFragment);
        assertTrue(
                "Fragment should contains two pane layout for tablet, but it does not!",
                mUserInfoFragment.getView().findViewById(R.id.preview_container) != null
        );
    }
}