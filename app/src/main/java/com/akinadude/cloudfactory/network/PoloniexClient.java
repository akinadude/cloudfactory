package com.akinadude.cloudfactory.network;

import com.akinadude.cloudfactory.model.TickersResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PoloniexClient {

    private static final String BASE_URL = "https://poloniex.com/";

    private static PoloniexClient instance;
    private PoloniexService poloniexService;

    private PoloniexClient() {
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(TickersResponse.class, new TickersResponse.Deserializer())
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LoggingInterceptor())
                .build();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        poloniexService = retrofit.create(PoloniexService.class);
    }

    public static PoloniexClient getInstance() {
        if (instance == null)
            instance = new PoloniexClient();
        return instance;
    }

    public Flowable<TickersResponse> getTickersRequest() {
        return poloniexService.getTickers2("returnTicker");
    }
}
