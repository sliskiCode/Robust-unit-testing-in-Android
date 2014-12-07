package com.code.sliski.ui.fragment;

import com.code.sliski.ui.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18, manifest = "././src/main/AndroidManifest.xml")
public class UserInfoFragmentTest {

    private UserInfoFragment mUserInfoFragment;

    @Before
    public void setUp() throws Exception {
        mUserInfoFragment = new UserInfoFragment();
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void fragmentShouldContainsPhoneLayout() throws Exception {
        FragmentTestUtil.startFragment(mUserInfoFragment);
        assertTrue(
                "Fragment should contains one pane layout for phone, but it does not!",
                mUserInfoFragment.getView().findViewById(R.id.preview_container) == null
        );
    }

    @SuppressWarnings("ConstantConditions")
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