package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.gradle.imageactivity.ImageActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ProgressBar mProgressBar;
    private Button mBtnTellJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        mBtnTellJoke = (Button)root.findViewById(R.id.btnTellJoke);
        mBtnTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        return root;
    }

    public void tellJoke() {
        mProgressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask(new EndpointsAsyncTask.OnFinishedListener() {
            @Override
            public void onFinished(String result) {
                mProgressBar.setVisibility(View.GONE);
//                Toast.makeText(getActivity(), "result:" + result, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), ImageActivity.class)
                        .putExtra("joke_result", result));
            }
        }).execute(getActivity());
    }
}
