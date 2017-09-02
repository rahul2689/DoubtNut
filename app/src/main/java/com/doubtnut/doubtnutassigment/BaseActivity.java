package com.doubtnut.doubtnutassigment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.doubtnut.doubtnutassigment.dagger.DaggerIDaggerInjector;
import com.doubtnut.doubtnutassigment.dagger.IDaggerInjector;
import com.doubtnut.doubtnutassigment.network_connection.NetworkModule;

import java.io.File;

/**
 * Created by Rahul on 2/09/17.
 */
public class BaseActivity extends AppCompatActivity {

    private IDaggerInjector daggerInjector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        daggerInjector = DaggerIDaggerInjector.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public IDaggerInjector getDaggerInjector() {
        return daggerInjector;
    }

}
