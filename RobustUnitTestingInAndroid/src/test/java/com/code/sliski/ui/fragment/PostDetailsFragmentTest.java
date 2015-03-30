package com.code.sliski.ui.fragment;

import android.widget.TextView;
import com.code.sliski.ui.R;
import com.code.sliski.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.FragmentTestUtil;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class PostDetailsFragmentTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void viewsShouldDisplayInfoAboutPost() throws Exception {
        PostDetailsFragment postDetailsFragment = PostDetailsFragment.Factory.getInstance(new Post(1, 100, "www.stack.com/baaah"));
        FragmentTestUtil.startFragment(postDetailsFragment);
        TextView score = ((TextView) postDetailsFragment.getView().findViewById(R.id.score));
        TextView link = ((TextView) postDetailsFragment.getView().findViewById(R.id.link));
        assertEquals(
                "100",
                score.getText().toString()
        );
        assertEquals(
                "www.stack.com/baaah",
                link.getText().toString()
        );
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void viewsShouldNotDisplayInfoAboutPost() throws Exception {
        PostDetailsFragment postDetailsFragment = new PostDetailsFragment();
        FragmentTestUtil.startFragment(postDetailsFragment);
        TextView score = ((TextView) postDetailsFragment.getView().findViewById(R.id.score));
        TextView link = ((TextView) postDetailsFragment.getView().findViewById(R.id.link));
        assertEquals(
                "",
                score.getText().toString()
        );
        assertEquals(
                "",
                link.getText().toString()
        );
    }
}