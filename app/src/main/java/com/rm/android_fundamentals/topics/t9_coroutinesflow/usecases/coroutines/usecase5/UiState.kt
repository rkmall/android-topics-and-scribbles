package com.rm.android_fundamentals.topics.t9_coroutinesflow.usecases.coroutines.usecase5

import com.rm.android_fundamentals.mocknetwork.mock.VersionFeatures

sealed class UiState {
    data object Loading : UiState()
    data class Success(val versionFeaturesList: List<VersionFeatures>) : UiState()
    data class Error(val message: String) : UiState()
}
