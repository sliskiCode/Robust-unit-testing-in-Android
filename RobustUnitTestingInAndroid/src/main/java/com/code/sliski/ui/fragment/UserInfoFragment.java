package com.code.sliski.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.code.sliski.ui.R;

public class UserInfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_info_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            addFragments();
        }
    }

    private void addFragments() {
        if (isTablet()) {
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.list_container, new PostListFragment())
                    .commit();
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.preview_container, new PostDetailsFragment())
                    .commit();
        } else {
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.list_container, new PostListFragment())
                    .commit();
        }
    }

    @SuppressWarnings("ConstantConditions")
    public boolean isTablet() {
        return getView().findViewById(R.id.preview_container) != null;
    }
}
