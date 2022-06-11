package com.example.expensetracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.appstate.ViewState
import com.example.expensetracker.model.Transaction
import com.example.expensetracker.repo.TransactionRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransactionViewModel(private val transactionRepo: TransactionRepo) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)

    // get the state of the view
    val viewState = _viewState.asStateFlow()

    //insert
    fun insertTransaction(transaction: Transaction) = viewModelScope.launch {
        transactionRepo.insert(transaction)
    }
    // get all transactions
    val getAllTransactions = transactionRepo.getAllTransactions().asLiveData()

}