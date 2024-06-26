package com.rm.android_fundamentals.topics.t9_coroutinesflow.usecases.coroutines.usecase6

import com.google.gson.Gson
import com.rm.android_fundamentals.mocknetwork.mock.createMockApi
import com.rm.android_fundamentals.mocknetwork.mock.mockAndroidVersions
import com.rm.android_fundamentals.mocknetwork.utils.MockNetworkInterceptor

fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/recent-android-versions",
            { Gson().toJson(mockAndroidVersions) },
            200,
            5000
        )
)