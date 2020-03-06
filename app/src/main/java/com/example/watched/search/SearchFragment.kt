package com.example.watched.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.watched.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by lazy {
        val application = requireNotNull(this.activity).application
        val viewModelFactory = SearchViewModel.Factory(application)
        ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.searchResult.observe(viewLifecycleOwner, Observer {
            binding.textView.text = it.toString()
        })

        binding.textInputLayout.editText

        return binding.root
    }
}