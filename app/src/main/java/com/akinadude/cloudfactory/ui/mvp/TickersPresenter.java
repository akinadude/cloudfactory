package com.akinadude.cloudfactory.ui.mvp;

import android.util.Log;

import com.akinadude.cloudfactory.model.Ticker;
import com.akinadude.cloudfactory.model.TickersResponse;
import com.akinadude.cloudfactory.network.PoloniexClient;
import com.akinadude.cloudfactory.viewholder.TickerVH;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class TickersPresenter implements Contract.TickersPresenter {

    private static final String TAG = TickersPresenter.class.getSimpleName();

    private Contract.TickersView view;
    private PoloniexClient poloniexClient;
    private CompositeDisposable compositeDisposable;

    private boolean isDataRequested;

    public TickersPresenter(PoloniexClient pc) {
        poloniexClient = pc;
    }

    @Override
    public void attachView(Contract.TickersView v) {
        view = v;
    }

    @Override
    public void detachView() {
        view = null;
        if (compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

    @Override
    public void start() {
        if (!isDataRequested)
            view.loadingStarted();
        Disposable disposable = poloniexClient.getTickersRequest()
                .map(TickersResponse::getTickers)
                .subscribeOn(Schedulers.io())
                .repeatWhen(f -> f.delay(5, TimeUnit.SECONDS))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<List<Ticker>>() {
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError()");
                    }

                    @Override
                    public void onNext(List<Ticker> tickers) {
                        Log.d(TAG, "onNext()");
                        Log.d(TAG, "Tickers: " + tickers);

                        if (!isDataRequested) {
                            view.loadingCompleted();
                            isDataRequested = true;
                        }
                        view.showTickers(tickers);
                    }
                });
        Log.d(TAG, "Disposable created: " + disposable);
        addToCompositeDisposable(disposable);
    }

    @Override
    public void stop() {
        Log.d(TAG, "composite disposable: " + compositeDisposable);
        compositeDisposable.dispose();
    }

    private void addToCompositeDisposable(Disposable disposable) {
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable);
    }
}
