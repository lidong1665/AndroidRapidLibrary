package com.lidong.demo.mvp.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

        public static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("http://v.juhe.cn");

        @Provides
        @Singleton
        public HttpUrl provideBaseUrl() {
            return PRODUCTION_API_URL;
        }

        @Provides
        @Singleton
        public Retrofit provideRetrofit() {
            return new Retrofit.Builder() //
                    .baseUrl(provideBaseUrl()) //
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //
                    .build();
        }

        @Provides
        @Singleton
        public ApiManagerService provideApiManagerService() {
            return provideRetrofit().create(ApiManagerService.class);
        }

}
