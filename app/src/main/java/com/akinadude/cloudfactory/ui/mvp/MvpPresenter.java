package com.akinadude.cloudfactory.ui.mvp;


public interface MvpPresenter<V extends MvpView> {
    void attachView(V mvpView);
    void detachView();
}
