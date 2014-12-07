package com.code.sliski.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.code.sliski.ui.R;
import com.code.sliski.ui.preference.PrefManager;

public class UserIdFragment extends Fragment {

    private EditText mUserIdEditText;
    private Button mGoForUserButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_id_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUserIdEditText = ((EditText) view.findViewById(R.id.user_id_editText));
        mGoForUserButton = ((Button) view.findViewById(R.id.go_button));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListeners();
    }

    private void setListeners() {
        mGoForUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveUserIdToPrefs();
                    replaceFragmentWithUserInfoFragment();
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Bad id format", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUserIdToPrefs() throws NumberFormatException{
        PrefManager prefManager = new PrefManager(getActivity().getSharedPreferences(getString(R.string.preferences), getActivity().MODE_PRIVATE));
        long id = Long.parseLong(mUserIdEditText.getText().toString());
        prefManager.userId().put(id).commit();
    }

    private void replaceFragmentWithUserInfoFragment() {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new UserInfoFragment())
                .commit();
    }
}
