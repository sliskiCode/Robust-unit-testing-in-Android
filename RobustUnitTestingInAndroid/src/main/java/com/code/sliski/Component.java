package com.code.sliski;

import com.code.sliski.ui.activity.MainActivity;
import com.code.sliski.ui.fragment.LoginFragment;
import com.code.sliski.ui.fragment.PostDetailsFragment;
import com.code.sliski.ui.fragment.PostListFragment;
import com.code.sliski.ui.fragment.UserInfoFragment;

public interface Component {
    void inject(PostListFragment postListFragment);
    void inject(PostDetailsFragment postDetailsFragment);
    void inject(MainActivity mainActivity);
    void inject(LoginFragment loginFragment);
    void inject(UserInfoFragment userInfoFragment);
}