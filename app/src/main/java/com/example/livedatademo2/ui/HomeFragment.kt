package com.example.livedatademo2.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.example.livedatademo2.MainViewmodel
import com.example.livedatademo2.R
import com.example.livedatademo2.database.model.Guest
import com.example.livedatademo2.databinding.ActivityMainBinding
import com.example.livedatademo2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewmodel: MainViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.allGuests.observe(viewLifecycleOwner) {
            Log.d("Datenbanktest", "$it")
        }

        binding.addBTN.setOnClickListener {
            val guest = Guest(name = "Max", foodPreference = "Alles")
            viewmodel.insertGuest(guest)
        }
    }


}