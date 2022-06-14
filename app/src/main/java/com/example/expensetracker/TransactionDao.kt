package com.example.expensetracker

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: Transaction)

    @Update
    suspend fun update(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    @Query("SELECT * FROM transactions ORDER BY transactionId DESC")
    fun getAll(): LiveData<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE transactionId = :transactionId")
    fun get(transactionId: Long): LiveData<Transaction>
}