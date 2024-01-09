package com.example.livedatademo2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.livedatademo2.MainViewmodel
import com.example.livedatademo2.adapter.GuestAdapter
import com.example.livedatademo2.databinding.FragmentHomeBinding

// Siebter Schritt

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewmodel: MainViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GuestAdapter(emptyList())
        binding.guestsRV.adapter = adapter

        viewmodel.allGuests.observe(viewLifecycleOwner){
            adapter.newData(it)
        }
    }
}