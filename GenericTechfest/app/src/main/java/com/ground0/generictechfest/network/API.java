package com.ground0.generictechfest.network;

import com.ground0.generictechfest.network.model.Workshops;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * Created by Arjun on 02-02-2015.
 */
public class API {
    private static String API_URL = "http://192.168.0.105/AndWor";
    private static ApiInterface apiInterface = null;

    public static ApiInterface getApi() {
        if(apiInterface == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API_URL)
                    .build();

            apiInterface = restAdapter.create(ApiInterface.class);
        }
        return apiInterface;
    }

    public interface ApiInterface {
        @GET("/server.php?query=work")
        Workshops getWorkshop();

        @GET("/server.php?query=work")
        void getWorkshop(Callback<Workshops> workshopsCallback);
    }
}