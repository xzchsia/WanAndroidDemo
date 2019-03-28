package io.github.xzchsia.wanandroiddemo;

import javax.inject.Singleton;

import io.github.xzchsia.wanandroiddemo.Constants;
import io.github.xzchsia.wanandroiddemo.WanAndroidService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAppInit {

    @Singleton
    Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Singleton
    WanAndroidService provideWanAndroidService(Retrofit retrofit) {
        return retrofit.create(WanAndroidService.class);
    }
}
