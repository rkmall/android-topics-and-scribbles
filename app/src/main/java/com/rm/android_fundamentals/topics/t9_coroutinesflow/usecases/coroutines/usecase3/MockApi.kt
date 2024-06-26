package com.rm.android_fundamentals.topics.t9_coroutinesflow.usecases.coroutines.usecase3

import com.google.gson.Gson
import com.rm.android_fundamentals.mocknetwork.mock.createMockApi
import com.rm.android_fundamentals.mocknetwork.mock.mockAndroidVersions
import com.rm.android_fundamentals.mocknetwork.mock.mockVersionFeaturesAndroid10
import com.rm.android_fundamentals.mocknetwork.mock.mockVersionFeaturesOreo
import com.rm.android_fundamentals.mocknetwork.mock.mockVersionFeaturesPie
import com.rm.android_fundamentals.mocknetwork.utils.MockNetworkInterceptor

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            path = "http://localhost/recent-android-versions",
            body = { Gson().toJson(mockAndroidVersions) },
            status = 200,
            delayInMs = 500
        )
        .mock(
            path = "http://localhost/android-version-features/27",
            body = { Gson().toJson(mockVersionFeaturesOreo) },
            status = 500,
            delayInMs = 500
        )
        .mock(
            path = "http://localhost/android-version-features/28",
            body = { Gson().toJson(mockVersionFeaturesPie) },
            status = 200,
            delayInMs = 500
        )
        .mock(
            path = "http://localhost/android-version-features/29",
            body = { Gson().toJson(mockVersionFeaturesAndroid10) },
            status = 200,
            delayInMs = 500
        )
)