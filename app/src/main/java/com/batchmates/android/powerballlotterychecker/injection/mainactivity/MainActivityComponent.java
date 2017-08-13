package com.batchmates.android.powerballlotterychecker.injection.mainactivity;

import com.batchmates.android.powerballlotterychecker.view.mainactivity.MainActivity;

import dagger.Component;

/**
 * Created by Android on 8/13/2017.
 */
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
