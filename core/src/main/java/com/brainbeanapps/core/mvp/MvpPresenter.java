package com.brainbeanapps.core.mvp;

import java.util.List;
import java.util.Set;

/**
 * Created by Rosty on 10/12/2016.
 */

public interface MvpPresenter<V extends MvpView> {

    void attach(V mvpView);

    void detach();

    void destroy();

    void onRequestPermissionsResult(int requestCode);
}
