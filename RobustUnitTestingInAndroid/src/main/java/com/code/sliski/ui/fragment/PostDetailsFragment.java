package com.code.sliski.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.code.sliski.ui.App;
import com.code.sliski.ui.R;
import com.code.sliski.ui.event.OnPostClickedEvent;
import com.code.sliski.ui.model.Post;
import de.greenrobot.event.EventBus;

import javax.inject.Inject;

public class PostDetailsFragment extends Fragment {

    @Inject
    EventBus mEventBus;
    private Post mPost;

    private TextView mScoreTextView;
    private TextView mLinkTextView;

    public static final String POST = "POST";

    public static PostDetailsFragment getInstance(Post post) {
        PostDetailsFragment postDetailsFragment = new PostDetailsFragment();
        postDetailsFragment.mPost = post;
        return postDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.inject(getActivity(), this);
        mEventBus.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        mScoreTextView = ((TextView) view.findViewById(R.id.score));
        mLinkTextView = ((TextView) view.findViewById(R.id.link));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mPost = savedInstanceState.getParcelable(POST);
        }
        if (mPost != null) {
            setViews();
        }
    }

    private void setViews() {
        mScoreTextView.setText(String.valueOf(mPost.getScore()));
        mLinkTextView.setText(mPost.getLink());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mEventBus.unregister(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(POST, mPost);
    }

    @SuppressWarnings("unused")
    public void onEvent(OnPostClickedEvent event) {
        mPost = event.getPost();
        setViews();
    }
}
