package com.test_project.app.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.test_project.app.database.repositories.EstimateRepo
import com.test_project.app.database.repositories.PersonRepo
import com.test_project.app.models.Estimate
import com.test_project.app.models.Person
import com.test_project.app.utils.Constants.Companion.dummyDataObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

class mainViewModel : ViewModel() {


    private val personRepo = PersonRepo()
    private val estimateRepo = EstimateRepo()

    fun getPerson(id: String) {
        personRepo.getUser(id)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ user ->
                if (user != null) {
                    // user data
                }
            }, { error ->
                // show error

            })
    }

    fun RetrieveData() {
        estimateRepo.getEstimate()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ estimate ->
                if (estimate != null) {
                    // get user by created user
                    estimate.created_by?.let { getPerson(it) }
                }
            }, { error ->

            })
    }

    // example using live data
    fun getEstimateData()=estimateRepo.getEstimateByLiveData()
    fun getPersonData(id:String)=personRepo.getUserUsingLiveData(id)

    fun saveObject() {
        // save data using kotlin coroutine scope
        viewModelScope.launch {
            try {
                val jsonObject = JSONObject(dummyDataObject)
                // get person data from json object
                val personData =
                    Gson().fromJson(
                        jsonObject.getJSONObject("person").toString(),
                        Person::class.java
                    )
                // get estimate data from json object
                val estimateData = Gson().fromJson(
                    jsonObject.getJSONObject("estimate").toString(),
                    Estimate::class.java
                )
                // save person data
                personRepo.savePersonObject(personData)
                // save estimate data
                estimateRepo.saveEstmateObject(estimateData)
            } catch (err: JSONException) {
                Log.d("Error", err.toString())
            }
        }
    }
}