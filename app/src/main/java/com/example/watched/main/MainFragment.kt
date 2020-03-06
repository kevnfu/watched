package com.example.watched.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.watched.databinding.FragmentMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.internal.MainDispatcherFactory

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.getData()

//        viewModel.data.observe(viewLifecycleOwner, Observer {
//            binding.messageTextView.text = it.toString()
//        })

        binding.messageTextView.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections
                    .actionMainFragmentToSearchFragment()
            )
        }

//        viewModel.navigateToSearch.observe(viewLifecycleOwner, Observer {
//            MainFragmentDirections.actionMainFragmentToSearchFragment()
//            viewModel.navigateToSearchComplete()
//        })

        return binding.root
    }


}