package co.fyinews.fyinewsapp;

import android.app.Application;

import fyinews.global.ConnectivityReceiver;

/**
 * Created by dakshkapur on 2018-06-07.
 */

public class MyApplication extends Application {
    public static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
