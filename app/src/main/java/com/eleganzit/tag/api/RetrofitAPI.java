package com.eleganzit.tag.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eleganz on 30/4/19.
 */

public class RetrofitAPI {

  static OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();
    public static String BASE_URL="http://itechgaints.com/";
    public static String BASE_URLN="http://eleganzit.online/";

    public static Retrofit retrofit=null;
    public static Retrofit retrofitn=null;

    public static Retrofit getRetrofit()
    {
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofitN()
    {
        if(retrofitn==null)
        {
            retrofitn = new Retrofit.Builder()
                    .baseUrl(BASE_URLN)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitn;
    }

}
