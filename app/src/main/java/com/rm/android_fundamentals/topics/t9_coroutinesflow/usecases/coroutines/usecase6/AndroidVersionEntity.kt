package com.rm.android_fundamentals.topics.t9_coroutinesflow.usecases.coroutines.usecase6

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rm.android_fundamentals.mocknetwork.mock.AndroidVersion

@Entity(tableName = "androidversions")
data class AndroidVersionEntity(@PrimaryKey val apiLevel: Int, val name: String)

fun List<AndroidVersionEntity>.mapToUiModelList() = map {
    AndroidVersion(it.apiLevel, it.name)
}

fun AndroidVersion.mapToEntity() = AndroidVersionEntity(this.apiLevel, this.name)

