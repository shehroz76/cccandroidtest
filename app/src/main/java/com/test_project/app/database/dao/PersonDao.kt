package com.test_project.app.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test_project.app.models.Person
import io.reactivex.Flowable

@Dao
interface PersonDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: Person)

    // Using RxJava
    @Query("SELECT * FROM Person WHERE id = :id  LIMIT 1")
    fun getUser(id: String?): Flowable<Person?>?

    // using LiveData
    @Query("SELECT * FROM Person WHERE id = :id  LIMIT 1")
    fun getUserUsingLiveData(id: String?): LiveData<Person?>?

}
