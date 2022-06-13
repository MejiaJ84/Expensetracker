package com.example.expensetracker

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TransactionViewModel(val dao: TransactionDao) : ViewModel() {
    var newTransactionLabel = ""
    var newAmount = ""
    val transactions = dao.getAll()



    fun addTransaction() {
        viewModelScope.launch {
            val transaction = Transaction(0, newTransactionLabel, newAmount.toDouble())
            dao.insert(transaction)
        }
    }




}