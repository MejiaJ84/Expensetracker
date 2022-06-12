package com.example.expensetracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var transactionId: Long = 0L,

    @ColumnInfo(name = "transaction_label")
    var transactionLabel: String,

    @ColumnInfo(name = "transaction_amount")
    var transactionAmount: Double
)