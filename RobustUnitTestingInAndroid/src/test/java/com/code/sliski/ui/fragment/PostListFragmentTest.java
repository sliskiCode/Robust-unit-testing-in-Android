package com.code.sliski.ui.fragment;

import com.code.sliski.R;
import com.code.sliski.TestApp;
import com.code.sliski.TestAppDataLoaded;
import com.code.sliski.event.OnPostClickedEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class PostListFragmentTest {

    private PostListFragment mPostListFragment;

    @Before
    public void setUp() throws Exception {
        mPostListFragment = new PostListFragment();
    }

    @Test
    @Config(application = TestApp.class)
    public void postListFragmentShouldLoadDataFromApi() throws Exception {
        FragmentTestUtil.startFragment(mPostListFragment);
        verify(mPostListFragment.getmClient()).getPosts(1408086l);
    }

    @Test
    @Config(application = TestAppDataLoaded.class)
    public void postListFragmentShouldNotLoadDataFromApi() throws Exception {
        FragmentTestUtil.startFragment(mPostListFragment);
        verify(mPostListFragment.getmClient(), never()).getPosts(1408086l);
    }

    @Test
    @Config(application = TestAppDataLoaded.class, qualifiers = "layout")
    public void onItemClickOnListShouldReplaceFragmentWithPostDetailsFragment() throws Exception {
        UserInfoFragment userInfoFragment = new UserInfoFragment();
        FragmentTestUtil.startFragment(userInfoFragment);
        mPostListFragment = (PostListFragment) userInfoFragment.getChildFragmentManager().findFragmentById(R.id.list_container);

        Robolectric.shadowOf(mPostListFragment.getListView()).performItemClick(1);
        assertTrue(
                "PostListFragment should be replaced with PostListFragment instance fragment, but it is not!",
                userInfoFragment.getChildFragmentManager().findFragmentById(R.id.list_container) instanceof PostDetailsFragment
        );
    }

    @Test
    @Config(application = TestAppDataLoaded.class, qualifiers = "layout-large")
    public void onItemClickOnListShouldSendOnPostClickedEventToPostDetailsFragment() throws Exception {
        UserInfoFragment userInfoFragment = new UserInfoFragment();
        FragmentTestUtil.startFragment(userInfoFragment);
        mPostListFragment = (PostListFragment) userInfoFragment.getChildFragmentManager().findFragmentById(R.id.list_container);

        Robolectric.shadowOf(mPostListFragment.getListView()).performItemClick(1);

        verify(mPostListFragment.getmEventBus()).post(Mockito.any(OnPostClickedEvent.class));
    }
}