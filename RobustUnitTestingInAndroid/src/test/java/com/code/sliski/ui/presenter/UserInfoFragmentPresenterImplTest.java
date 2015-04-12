package com.code.sliski.ui.presenter;

import android.os.Bundle;
import com.code.sliski.ui.fragment.UserInfoFragmentView;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class UserInfoFragmentPresenterImplTest {

    private UserInfoFragmentPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new UserInfoFragmentPresenterImpl();
        presenter.setUserInfoFragmentView(mock(UserInfoFragmentView.class));
    }

    @Test
    public void addFragments_ShouldNotAddFragments() throws Exception {
        presenter.addFragments(mock(Bundle.class), true);
        verify(
                presenter.getUserInfoFragmentView(), never()
        ).addPostListFragment();
        verify(
                presenter.getUserInfoFragmentView(), never()
        ).addPostDetailsFragment();
    }

    @Test
    public void addFragments_ShouldAddPostDetailsFragment() throws Exception {
        presenter.addFragments(null, true);
        verify(presenter.getUserInfoFragmentView()).addPostDetailsFragment();
    }

    @Test
    public void addFragments_ShouldNotAddPostDetailsFragment() throws Exception {
        presenter.addFragments(null, false);
        verify(
                presenter.getUserInfoFragmentView(), never()
        ).addPostDetailsFragment();
    }
}
