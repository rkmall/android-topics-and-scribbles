package com.rm.android_fundamentals.topics.t10_architectures.common

import com.google.gson.Gson
import com.rm.android_fundamentals.mocknetwork.mock.AndroidVersion
import com.rm.android_fundamentals.mocknetwork.mock.VersionFeatures
import com.rm.android_fundamentals.mocknetwork.mock.mockAndroidVersions
import com.rm.android_fundamentals.mocknetwork.utils.MockNetworkInterceptor
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

fun rxMockApi(): RxMockApi = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/recent-android-versions",
            { Gson().toJson(mockAndroidVersions) },
            200,
            1500
        )
)

interface RxMockApi {

    @GET("recent-android-versions")
    fun getRecentAndroidVersions(): Single<List<AndroidVersion>>

    @GET("android-version-features/{apiLevel}")
    fun getAndroidVersionFeatures(@Path("apiLevel") apiLevel: Int): Single<VersionFeatures>
}

fun createMockApi(interceptor: MockNetworkInterceptor): RxMockApi {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()

    return retrofit.create(RxMockApi::class.java)
}