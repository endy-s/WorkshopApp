package io.ckl.workshopapp

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by endysilveira on 27/06/17.
 */
object RetrofitHelper {

    val retrofit = Retrofit.Builder()
            .baseUrl("http://r3desenhando.com.br:1880")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}