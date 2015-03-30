package com.code.sliski.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.code.sliski.api.Client;
import com.code.sliski.event.GetPostsResponseEvent;
import com.code.sliski.event.OnPostClickedEvent;
import com.code.sliski.ui.BaseApplication;
import com.code.sliski.ui.R;
import com.code.sliski.model.Post;
import com.code.sliski.ui.preference.PrefManager;
import de.greenrobot.event.EventBus;

import javax.inject.Inject;
import java.util.ArrayList;

public class PostListFragment extends ListFragment {

    @Inject
    Client mClient;
    @Inject
    EventBus mEventBus;
    @Inject
    PrefManager mPrefManager;
    @Inject
    ArrayList<Post> mPosts;

    private static final String POSTS = "POSTS";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.inject(getActivity(), this);
        mEventBus.register(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mPosts = savedInstanceState.getParcelableArrayList(POSTS);
        }
        if (mPosts == null) {
            getPosts();
        } else {
            setAdapter();
        }
        setListeners();
    }

    private void getPosts() {
        Long userId = mPrefManager.userId().getOr(0l);
        mClient.getPosts(userId);
    }

    private void setAdapter() {
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mPosts));
    }

    private void setListeners() {
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post post = mPosts.get(position);
                if (((UserInfoFragment) getParentFragment()).isTablet()) {
                    mEventBus.post(new OnPostClickedEvent(post));
                } else {
                    getParentFragment().getChildFragmentManager()
                            .beginTransaction()
                            .replace(R.id.list_container, PostDetailsFragment.Factory.getInstance(post))
                            .addToBackStack("details")
                            .commit();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mEventBus.unregister(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(POSTS, mPosts);
    }

    @SuppressWarnings("unused")
    public void onEvent(GetPostsResponseEvent event) {
        mPosts = new ArrayList<>(event.getPosts());
        setAdapter();
    }
}
