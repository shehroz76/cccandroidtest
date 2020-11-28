package com.test_project.app.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test_project.app.models.Estimate
import io.reactivex.Flowable

@Dao
interface estimateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEstimate(estimate: Estimate)


    @Query("SELECT * FROM Estimate WHERE id = :id  LIMIT 1")
    fun getEstimate(id: String?): LiveData<Estimate?>?

    @Query("SELECT * FROM Estimate LIMIT 1")
    fun getEstimate(): Flowable<Estimate?>?

    @Query("SELECT * FROM Estimate LIMIT 1")
    fun getEstimateByLiveData(): LiveData<Estimate?>?
}