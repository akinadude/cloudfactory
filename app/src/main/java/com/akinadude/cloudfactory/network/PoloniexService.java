package com.akinadude.cloudfactory.network;


import com.akinadude.cloudfactory.model.TickersResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PoloniexService {

    //@GET("public?command=returnTicker")
    @GET("public")
    Flowable<TickersResponse> getTickers2(@Query("command") String command);
}
