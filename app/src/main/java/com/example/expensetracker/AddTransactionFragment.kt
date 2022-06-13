package com.example.expensetracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.databinding.FragmentAddTransactionBinding


class AddTransactionFragment : Fragment() {
    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView
        (inflater: LayoutInflater, container: ViewGroup?, savedInstance: Bundle?
    ): View {
        _binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = TransactionDatabase.getInstance(application).transactionDao
        val viewModelFactory = TransactionViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(TransactionViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = TransactionItemAdapter()
        binding.transactionList.adapter = adapter
        viewModel.transactions.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}