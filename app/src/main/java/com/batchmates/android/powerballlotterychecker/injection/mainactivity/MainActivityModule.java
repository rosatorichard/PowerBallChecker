package com.batchmates.android.powerballlotterychecker.injection.mainactivity;

import com.batchmates.android.powerballlotterychecker.view.mainactivity.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android on 8/13/2017.
 */
@Module
public class MainActivityModule {

    @Provides
    public MainActivityPresenter mainActivityPresenter()
    {
        return new MainActivityPresenter();
    }
}
