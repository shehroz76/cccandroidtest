package com.test_project.app.database.repositories

import androidx.lifecycle.LiveData
import com.test_project.app.database.dao.estimateDao
import com.test_project.app.database.db.AppDatabase
import com.test_project.app.models.Estimate

import io.reactivex.Flowable

class EstimateRepo {

    private var estimateDao: estimateDao? = null
    val appDatabase: AppDatabase? = com.test_project.app.app.App.Companion.getInstance()?.let {
        AppDatabase.getInstance(
            it
        )
    }

    init {
        estimateDao = appDatabase?.estimateDao()
    }

    suspend fun saveEstmateObject(estimate: Estimate) {
        estimateDao?.insertEstimate(estimate = estimate)
    }

    fun closeDb() {
        appDatabase?.close()
    }

    fun getEstimate(): Flowable<Estimate?>? {
        return estimateDao?.getEstimate()
    }
    fun getEstimateByLiveData(): LiveData<Estimate?>? {
        return estimateDao?.getEstimateByLiveData()
    }

}