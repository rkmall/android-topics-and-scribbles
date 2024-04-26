package com.rm.android_fundamentals.topics.t9_architectures.common

import com.rm.android_fundamentals.topics.t8_coroutinesflow.mock.AndroidVersion

sealed class VersionUiState {
    object Loading : VersionUiState()
    data class Success(val recentVersions: List<AndroidVersion>) : VersionUiState()
    data class Error(val message: String) : VersionUiState()
}