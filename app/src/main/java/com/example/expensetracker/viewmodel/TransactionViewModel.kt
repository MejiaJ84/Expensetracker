package com.example.expensetracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.appstate.ViewState
import com.example.expensetracker.model.Transaction
import com.example.expensetracker.repo.TransactionRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TransactionViewModel(private val transactionRepo: TransactionRepo) : ViewModel() {

    private val _transactionFilter = MutableStateFlow("Overall")
    val filterState: StateFlow<String> = _transactionFilter

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)

    // get the state of the view
    val viewState = _viewState.asStateFlow()

    //insert
    fun insertTransaction(transaction: Transaction) = viewModelScope.launch {
        transactionRepo.insert(transaction)
    }

    //update
    fun updateTransaction(transaction: Transaction) = viewModelScope.launch {
        transactionRepo.update(transaction)
    }

    //delete
    fun deleteTransaction(transaction: Transaction) = viewModelScope.launch {
        transactionRepo.delete(transaction)
    }

    fun allIncome() {
        _transactionFilter.value = "Income"
    }

    fun allExpense() {
        _transactionFilter.value = "Expense"
    }

    fun overall() {
        _transactionFilter.value = "Overall"
    }

    // get all transactions
    fun getTransactions(type: String) = viewModelScope.launch {
        transactionRepo.getAllOrSingleTransaction(type).collect { result ->
            // if there are no transactions, set ViewState to empty
            if (result.isEmpty()) {
                _viewState.value = ViewState.Empty
            }
            // otherwise set ViewState to success and pass the list of transactions (result)
            else {
                _viewState.value = ViewState.Success(result)
            }
        }
    }
}