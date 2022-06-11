package com.example.expensetracker.appstate

import com.example.expensetracker.model.Transaction

sealed class ViewState {
    object Loading: ViewState()
    object Empty: ViewState()

    data class Success(val transaction: List<Transaction>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}