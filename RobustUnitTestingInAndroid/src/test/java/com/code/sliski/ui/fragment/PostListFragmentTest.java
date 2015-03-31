package com.code.sliski.ui.fragment;

import com.code.sliski.api.Client;
import com.code.sliski.event.OnPostClickedEvent;
import com.code.sliski.R;
import com.code.sliski.ui.TestBaseApplication;
import com.code.sliski.ui.TestBaseApplicationForDataLoaded;
import com.code.sliski.model.Post;
import de.greenrobot.event.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

import javax.inject.Inject;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class PostListFragmentTest {

    private PostListFragment mPostListFragment;

    @Inject
    Client mClient;
    @Inject
    EventBus mEventBus;
    @Inject
    ArrayList<Post> mPosts;

    @Before
    public void setUp() throws Exception {
        mPostListFragment = new PostListFragment();
    }

    @Test
    @Config(application = TestBaseApplication.class)
    public void postListFragmentShouldLoadDataFromApi() throws Exception {
        TestBaseApplication.inject(Robolectric.application.getApplicationContext(), this);
        FragmentTestUtil.startFragment(mPostListFragment);
        verify(mClient).getPosts(1408086l);
    }

    @Test
    @Config(application = TestBaseApplicationForDataLoaded.class)
    public void postListFragmentShouldNotLoadDataFromApi() throws Exception {
        TestBaseApplicationForDataLoaded.inject(Robolectric.application.getApplicationContext(), this);
        FragmentTestUtil.startFragment(mPostListFragment);
        verify(mClient, never()).getPosts(1408086l);
    }

    @Test
    @Config(application = TestBaseApplicationForDataLoaded.class, qualifiers = "layout")
    public void onItemClickOnListShouldReplaceFragmentWithPostDetailsFragment() throws Exception {
        TestBaseApplicationForDataLoaded.inject(Robolectric.application.getApplicationContext(), this);

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
    @Config(application = TestBaseApplicationForDataLoaded.class, qualifiers = "layout-large")
    public void onItemClickOnListShouldSendOnPostClickedEventToPostDetailsFragment() throws Exception {
        TestBaseApplicationForDataLoaded.inject(Robolectric.application.getApplicationContext(), this);

        UserInfoFragment userInfoFragment = new UserInfoFragment();
        FragmentTestUtil.startFragment(userInfoFragment);
        mPostListFragment = (PostListFragment) userInfoFragment.getChildFragmentManager().findFragmentById(R.id.list_container);

        Robolectric.shadowOf(mPostListFragment.getListView()).performItemClick(1);

        verify(mEventBus).post(Mockito.any(OnPostClickedEvent.class));
    }
}