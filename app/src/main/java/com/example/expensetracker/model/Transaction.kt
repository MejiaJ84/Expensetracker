package com.example.expensetracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.DateFormat

@Entity(tableName = "all_transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int = 0,

    @ColumnInfo(name = "title")
    var title:String,

    @ColumnInfo(name = "amount")
    var amount:Double,

    @ColumnInfo(name = "transactionType")
    var transactionType:String,

    @ColumnInfo(name = "tag")
    var tag:String,

    @ColumnInfo(name = "data")
    var data:String,

    @ColumnInfo(name = "note")
    var note:String,

    @ColumnInfo(name = "createdAt")
    var createdAt: Long = System.currentTimeMillis()
) : Serializable {
    val createdAtDataFormat: String
    get() = DateFormat.getDateInstance().format(createdAt) // Example: July 4, 1776, 7:30 PM

}
