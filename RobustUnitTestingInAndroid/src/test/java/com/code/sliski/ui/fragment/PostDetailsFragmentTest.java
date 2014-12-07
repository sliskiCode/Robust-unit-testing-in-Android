package com.code.sliski.ui.fragment;

import android.widget.TextView;
import com.code.sliski.ui.R;
import com.code.sliski.ui.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18, manifest = "././src/main/AndroidManifest.xml")
public class PostDetailsFragmentTest {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void viewsShouldDisplayInfoAboutPost() throws Exception {
        PostDetailsFragment postDetailsFragment = PostDetailsFragment.getInstance(Post.Builder
                .withPostId(1)
                .withLink("www.stack.com/baaah")
                .withScore(100)
                .build());
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