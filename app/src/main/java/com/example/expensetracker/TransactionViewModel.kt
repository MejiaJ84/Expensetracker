package com.example.expensetracker

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TransactionViewModel(val dao: TransactionDao) : ViewModel() {
    var newTransactionLabel = ""
    var newAmount = ""
    val transactions = dao.getAll()

    val transactionString = Transformations.map(transactions) {
            transactions -> formatTransactions(transactions)
    }

    fun addTransaction() {
        viewModelScope.launch {
            val transaction = Transaction(0, newTransactionLabel, newAmount.toDouble())
            dao.insert(transaction)
        }
    }

    fun formatTransactions(transactions: List<Transaction>): String {
        return transactions.fold("") {
            str, item -> str + '\n' + formatTransaction(item)
        }
    }

    fun formatTransaction(transaction: Transaction): String {
        var str = "ID: ${transaction.transactionId}"
        str += '\n' + "Label: ${transaction.transactionLabel}"
        str += '\n' + "Amount: ${transaction.transactionAmount}"
        return str
    }
}