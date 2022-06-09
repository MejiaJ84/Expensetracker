package com.example.expensetracker.repo

import com.example.expensetracker.database.ExpenseDatabase
import com.example.expensetracker.model.Transaction

class TransactionRepo(private val database: ExpenseDatabase) {

    // enter a transaction into the database
    suspend fun insert(transaction: Transaction) = database.transactionDao.insertTransaction(transaction)

    // update existing transaction
    suspend fun update(transaction: Transaction) = database.transactionDao.updateTransaction(transaction)

    // deletes an existing transaction from the database
    suspend fun delete(transaction: Transaction) = database.transactionDao.deleteTransaction(transaction)

    // get all transactions
    fun getAllTransactions() = database.transactionDao.getAllTransactions()

    // get a single transaction of type expense or income
    fun getTransaction(transactionType: String) = database.transactionDao.getTransaction(transactionType)
}