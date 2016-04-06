package com.lidong.demo.mvp_dagger2.api;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiManagerServiceModule {

        public static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("http://v.juhe.cn");

        @Provides
        @Singleton
        OkHttpClient provideOkHttpClient() {
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.newBuilder().connectTimeout(60 * 1000, TimeUnit.MILLISECONDS).readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
                return okHttpClient;
        }

        @Provides
        @Singleton
        public HttpUrl provideBaseUrl() {
            return PRODUCTION_API_URL;
        }

        @Provides
        @Singleton
        public Retrofit provideRetrofit() {
            return new Retrofit.Builder().client(provideOkHttpClient()) //
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
