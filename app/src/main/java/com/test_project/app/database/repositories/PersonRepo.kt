package com.test_project.app.database.repositories

import androidx.lifecycle.LiveData
import com.test_project.app.database.dao.PersonDao
import com.test_project.app.database.db.AppDatabase
import com.test_project.app.models.Person

import io.reactivex.Flowable


class PersonRepo {

    private var personDao: PersonDao? = null
    val appDatabase: AppDatabase? = com.test_project.app.app.App.Companion.getInstance()?.let {
        AppDatabase.getInstance(
            it
        )
    }

    init {
        personDao = appDatabase?.personDao()
    }


    suspend fun savePersonObject(person: Person) {
        personDao?.insertPerson(person = person)
    }

    fun getUser(personId: String?): Flowable<Person?>? {
        return personDao?.getUser(personId)
    }

    fun getUserUsingLiveData(personId: String?): LiveData<Person?>? {
        return personDao?.getUserUsingLiveData(personId)
    }
}