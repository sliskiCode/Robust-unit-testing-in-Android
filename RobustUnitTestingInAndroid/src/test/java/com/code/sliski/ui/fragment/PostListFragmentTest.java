package com.code.sliski.ui.fragment;

import com.code.sliski.ui.R;
import com.code.sliski.ui.TestApp;
import com.code.sliski.ui.TestAppForDataLoaded;
import com.code.sliski.ui.api.StackoverflowClient;
import com.code.sliski.ui.event.OnPostClickedEvent;
import com.code.sliski.ui.model.Post;
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

@RunWith(RobolectricTestRunner.class)
public class PostListFragmentTest {

    private PostListFragment mPostListFragment;

    @Inject
    StackoverflowClient mClient;
    @Inject
    EventBus mEventBus;
    @Inject
    ArrayList<Post> mPosts;

    @Before
    public void setUp() throws Exception {
        mPostListFragment = new PostListFragment();
    }

    @Test
    @Config(application = TestApp.class)
    public void postListFragmentShouldLoadDataFromApi() throws Exception {
        TestApp.inject(Robolectric.application.getApplicationContext(), this);
        FragmentTestUtil.startFragment(mPostListFragment);
        verify(mClient).getPosts(1408086l);
    }

    @Test
    @Config(application = TestAppForDataLoaded.class)
    public void postListFragmentShouldNotLoadDataFromApi() throws Exception {
        TestAppForDataLoaded.inject(Robolectric.application.getApplicationContext(), this);
        FragmentTestUtil.startFragment(mPostListFragment);
        verify(mClient, never()).getPosts(1408086l);
    }

    @Test
    @Config(application = TestAppForDataLoaded.class, qualifiers = "layout")
    public void onItemClickOnListShouldReplaceFragmentWithPostDetailsFragment() throws Exception {
        TestAppForDataLoaded.inject(Robolectric.application.getApplicationContext(), this);

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
    @Config(application = TestAppForDataLoaded.class, qualifiers = "layout-large")
    public void onItemClickOnListShouldSendOnPostClickedEventToPostDetailsFragment() throws Exception {
        TestAppForDataLoaded.inject(Robolectric.application.getApplicationContext(), this);

        UserInfoFragment userInfoFragment = new UserInfoFragment();
        FragmentTestUtil.startFragment(userInfoFragment);
        mPostListFragment = (PostListFragment) userInfoFragment.getChildFragmentManager().findFragmentById(R.id.list_container);

        Robolectric.shadowOf(mPostListFragment.getListView()).performItemClick(1);

        verify(mEventBus).post(Mockito.any(OnPostClickedEvent.class));
    }
}