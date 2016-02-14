package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 * Created by libin on 16/2/9.
 */
public class GetJokeTaskTest extends AndroidTestCase{

    private static final String TAG = "GetJokeTaskTest";

    public void testGetJokeTask() throws InterruptedException {

        final CountDownLatch signal = new CountDownLatch(1);

        Log.d(TAG, "testGetJokeTask: ");

        new EndpointsAsyncTask(new EndpointsAsyncTask.OnFinishedListener() {
            @Override
            public void onFinished(String result) {
                Log.d(TAG, "onFinished: " + result);
                assertNotNull(result);
                signal.countDown();
            }
        }).execute(getContext());

        signal.await();
    }
}
