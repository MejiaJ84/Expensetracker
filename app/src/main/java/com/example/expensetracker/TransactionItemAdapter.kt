package com.example.expensetracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class TransactionItemAdapter : RecyclerView.Adapter<TransactionItemAdapter.TransactionItemViewHolder>() {
    var data = listOf<Transaction>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        : TransactionItemViewHolder = TransactionItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: TransactionItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class TransactionItemViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
        val transactionLabel = rootView.findViewById<TextView>(R.id.label)
        val transactionAmount = rootView.findViewById<TextView>(R.id.amount)
        companion object {
            fun inflateFrom(parent: ViewGroup): TransactionItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.fragment_add_transaction, parent, false) as CardView
                return TransactionItemViewHolder(view)
            }
        }
        fun bind(item: Transaction) {
            transactionLabel.text = item.transactionLabel
            transactionAmount.text = item.transactionAmount.toString()
        }
    }
}