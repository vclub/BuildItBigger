package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.imageactivity.ImageActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ProgressBar mProgressBar;
    private Button mBtnTellJoke;

    InterstitialAd mInterstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                showResult(mJokeResult);
            }
        });

        requestNewInterstitial();

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

    private String mJokeResult;

    public void tellJoke() {
        mProgressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask(new EndpointsAsyncTask.OnFinishedListener() {
            @Override
            public void onFinished(String result) {
                mProgressBar.setVisibility(View.GONE);

                mJokeResult = result;

                if (mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                } else {
                    showResult(result);
                }
            }
        }).execute(getActivity());
    }

    private void showResult(String result){
                        startActivity(new Intent(getContext(), ImageActivity.class)
                        .putExtra("joke_result", result));
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }
}
