package com.example.expensetracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.expensetracker.model.Transaction

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {

    abstract val transactionDao: TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: ExpenseDatabase? = null
        // Check if there is an instance of the database yet and if not, create one
        fun getInstance(context: Context): ExpenseDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExpenseDatabase::class.java,
                        "transaction_database"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}