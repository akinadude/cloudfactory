package com.akinadude.cloudfactory.ui.mvp;


import com.akinadude.cloudfactory.model.Ticker;

import java.util.List;

public interface Contract {

    interface TickersView extends LoadingView {
        void showTickers(List<Ticker> tickers);
    }

    interface TickersPresenter extends MvpPresenter<TickersView> {
        void start();
        void stop();
    }
}
