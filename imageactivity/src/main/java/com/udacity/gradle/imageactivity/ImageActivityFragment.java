package com.udacity.gradle.imageactivity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class ImageActivityFragment extends Fragment {

    public ImageActivityFragment() {
    }

    private TextView mJokeResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image, container, false);
        mJokeResult =  (TextView) view.findViewById(R.id.libraryWelcomeTextView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity().getIntent().hasExtra("joke_result")){
            if (mJokeResult != null){
                mJokeResult.setText(getActivity().getIntent().getStringExtra("joke_result"));
            }
        }

    }
}
