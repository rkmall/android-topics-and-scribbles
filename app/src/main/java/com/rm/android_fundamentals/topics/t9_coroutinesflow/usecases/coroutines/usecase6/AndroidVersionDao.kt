package com.rm.android_fundamentals.topics.t9_coroutinesflow.usecases.coroutines.usecase6

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AndroidVersionDao {

    @Query("SELECT * FROM androidversions")
    suspend fun getAndroidVersions(): List<AndroidVersionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(androidVersionEntity: AndroidVersionEntity)

    @Query("DELETE FROM androidversions")
    suspend fun clear()
}