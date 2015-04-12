package com.code.sliski.ui.fragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ConstantConditions")
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class UserInfoFragmentTest {

    private int childFragmentCount;

    @Before
    public void setUp() throws Exception {
        UserInfoFragment userInfoFragment = new UserInfoFragment();
        FragmentTestUtil.startFragment(userInfoFragment);
        childFragmentCount = userInfoFragment
                .getChildFragmentManager()
                .getFragments()
                .size();
    }

    @Test
    public void fragment_ShouldContainsOneChild() throws Exception {
        assertEquals(1, childFragmentCount);
    }

    @Test
    @Config(qualifiers = "layout-large")
    public void fragment_ShouldContainsTwoChildren() throws Exception {
        assertEquals(2, childFragmentCount);
    }
}