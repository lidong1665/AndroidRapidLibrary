package com.lidong.demo.mvp.api;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

@Module(
        complete = false,
        library = true,
        injects = {
                ApiManagerService.class
        }
)
public class ApiModule {

        public static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("http://v.juhe.cn");

        @Provides
        @Singleton
        static HttpUrl provideBaseUrl() {
            return PRODUCTION_API_URL;
        }

        @Provides
        @Singleton
         Retrofit provideRetrofit() {
            return new Retrofit.Builder() //
                    .baseUrl(provideBaseUrl()) //
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //
                    .build();
        }

        @Provides
        @Singleton   ApiManagerService provideApiManagerService(Retrofit retrofit) {
            return provideRetrofit().create(ApiManagerService.class);
        }

}
