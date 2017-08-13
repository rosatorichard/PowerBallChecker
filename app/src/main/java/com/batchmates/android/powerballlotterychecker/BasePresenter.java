package com.batchmates.android.powerballlotterychecker;

import android.content.Context;

/**
 * Created by Android on 8/13/2017.
 */

public interface BasePresenter <V extends BaseView>{

    void addView(V view, Context context);
    void removeView();

}
