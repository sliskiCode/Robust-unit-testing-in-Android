package com.code.sliski.ui.presenter;

import com.code.sliski.api.Client;
import com.code.sliski.event.OnPostClickedEvent;
import com.code.sliski.model.Post;
import com.code.sliski.preference.PreferencesManager;
import com.code.sliski.ui.fragment.PostListFragmentView;
import com.tale.prettysharedpreferences.LongEditor;
import de.greenrobot.event.EventBus;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostListFragmentPresenterImplTest {

    private PostListFragmentPresenterImpl postListFragmentPresenter;

    @Before
    public void setUp() throws Exception {
        postListFragmentPresenter = new PostListFragmentPresenterImpl();
        postListFragmentPresenter.setPostListFragmentView(mock(PostListFragmentView.class));
        postListFragmentPresenter.setClient(mock(Client.class));
        postListFragmentPresenter.setEventBus(mock(EventBus.class));
    }

    @Test
    public void getPosts_ShouldLoadPostsFromCache() throws Exception {
        ArrayList<Post> arrayList = new ArrayList<>();
        postListFragmentPresenter.setPostList(arrayList);
        postListFragmentPresenter.getPosts();
        verify(postListFragmentPresenter.getPostListFragmentView()).setAdapter(arrayList);
    }

    @Test
    public void getPosts_ShouldLoadPostFromServer() throws Exception {
        PreferencesManager preferencesManager = getPreferencesManager(1408086L);
        postListFragmentPresenter.setPreferencesManager(preferencesManager);
        postListFragmentPresenter.setPostList(null);
        postListFragmentPresenter.getPosts();
        verify(postListFragmentPresenter.getClient()).getPosts(1408086L);
    }

    @Test
    public void onItemClick_ShouldPostOnPostClickedEvent() throws Exception {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(1, 0, ""));
        postListFragmentPresenter.setPostList(posts);
        postListFragmentPresenter.onItemClick(0, true);
        verify(postListFragmentPresenter.getEventBus()).post(Mockito.any(OnPostClickedEvent.class));
    }

    @Test
    public void onItemClick_ShouldAddToBackStack() throws Exception {
        ArrayList<Post> posts = new ArrayList<>();
        Post post = new Post(1, 0, "");
        posts.add(post);
        postListFragmentPresenter.setPostList(posts);
        postListFragmentPresenter.onItemClick(0, false);
        verify(postListFragmentPresenter.getPostListFragmentView()).addToBackStack(post);
    }

    @NotNull
    private PreferencesManager getPreferencesManager(long id) {
        PreferencesManager preferencesManager = mock(PreferencesManager.class);
        LongEditor mock = mock(LongEditor.class);
        when(mock.getOr(0L)).thenReturn(id);
        when(preferencesManager.userId()).thenReturn(mock);
        return preferencesManager;
    }
}
