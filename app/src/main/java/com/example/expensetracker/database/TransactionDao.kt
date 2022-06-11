package com.example.expensetracker.database

import androidx.room.*
import com.example.expensetracker.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao // Dao: Data Access Object
interface TransactionDao {

    // Inserts new transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE) // replaces any existing data with new data that are duplicates
    suspend fun insertTransaction(transaction: Transaction)

    // Updates existing transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTransaction(transaction: Transaction)

    // Deletes a transaction
    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    // gets all transactions and stores in a list
    @Query("SELECT * FROM all_transactions")
    fun getAllTransactions() : Flow<List<Transaction>>

    // gets a list of all income or expenses
    @Query("SELECT * FROM all_transactions WHERE transactionType == :transactionType ORDER by createdAt DESC")
    fun getTransactionByType(transactionType: String): Flow<List<Transaction>>
}