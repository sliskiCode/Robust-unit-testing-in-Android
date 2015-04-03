package com.code.sliski;

import com.code.sliski.ui.fragment.PostDetailsFragment;
import com.code.sliski.ui.fragment.PostListFragment;

public interface Component {
    void inject(PostListFragment postListFragment);
    void inject(PostDetailsFragment postDetailsFragment);
}
