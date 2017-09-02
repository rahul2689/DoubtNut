package com.doubtnut.doubtnutassigment.dagger;


import com.doubtnut.doubtnutassigment.network_connection.NetworkModule;
import com.doubtnut.doubtnutassigment.view.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Rahul on 2/09/17.
 */
@Singleton
@Component(modules = {NetworkModule.class,})
public interface IDaggerInjector {
    void inject(HomeActivity homeActivity);
}
