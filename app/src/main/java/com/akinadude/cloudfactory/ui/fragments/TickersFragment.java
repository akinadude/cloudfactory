package com.akinadude.cloudfactory.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.akinadude.cloudfactory.R;
import com.akinadude.cloudfactory.model.Ticker;
import com.akinadude.cloudfactory.network.PoloniexClient;
import com.akinadude.cloudfactory.ui.adapters.TickersAdapter;
import com.akinadude.cloudfactory.ui.mvp.Contract;
import com.akinadude.cloudfactory.ui.mvp.TickersPresenter;

import java.util.List;

public class TickersFragment extends Fragment implements Contract.TickersView {

    private static final String TAG = TickersFragment.class.getSimpleName();

    private TickersAdapter mAdapter;
    private RecyclerView mTickersRv;
    private ProgressBar mProbress;

    private TickersPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tickers, container, false);
        mTickersRv = view.findViewById(R.id.tickers_rv);
        mProbress = view.findViewById(R.id.progress_pb);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new TickersAdapter();
        mTickersRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTickersRv.setAdapter(mAdapter);

        presenter = new TickersPresenter(PoloniexClient.getInstance());
        presenter.attachView(this);
        presenter.start();
    }

    public void start() {
        Log.d(TAG, "start is called");
        presenter.start();
    }

    public void stop() {
        Log.d(TAG, "stop is called");
        presenter.stop();
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void loadingStarted() {
        mTickersRv.setVisibility(View.GONE);
        mProbress.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadingCompleted() {
        mProbress.setVisibility(View.GONE);
        mTickersRv.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTickers(List<Ticker> tickers) {
        mAdapter.setItems(tickers);
    }
}
