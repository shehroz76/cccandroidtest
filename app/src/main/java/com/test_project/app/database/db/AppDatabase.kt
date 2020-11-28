package com.test_project.app.database.db

import android.content.Context
import android.security.identity.PersonalizationData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test_project.app.database.dao.PersonDao
import com.test_project.app.database.dao.estimateDao
import com.test_project.app.models.Estimate
import com.test_project.app.models.Person

@Database(entities = [Person::class, Estimate::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private const val DB_NAME = "test_app"

        fun getInstance(context: Context): AppDatabase? {
            var instance = instance
            if (instance == null) {
                synchronized(AppDatabase::class.java) {
                    instance = AppDatabase.instance
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            DB_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                        AppDatabase.instance = instance
                    }
                }
            }
            return instance
        }
    }

    abstract fun personDao(): PersonDao
    abstract fun estimateDao(): estimateDao
}

