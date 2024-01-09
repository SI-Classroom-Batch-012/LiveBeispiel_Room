package com.example.livedatademo2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.livedatademo2.MainViewmodel
import com.example.livedatademo2.database.model.Guest
import com.example.livedatademo2.databinding.FragmentDetailBinding

// Achter Schritt

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewmodel: MainViewmodel by activityViewModels()

    private var guestId: Long = -1
    private lateinit var guest: Guest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            guestId = it.getLong("guestId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(guestId == -1L) {
            this.guest = Guest(name = "", foodPreference = "")
        } else {
            viewmodel.getGuest(guestId).observe(viewLifecycleOwner) { guest ->
                // this.guest ist die Variable des Fragments
                // guest ist die lokale Variable aus der LiveData
                this.guest = guest
                binding.nameET.setText(guest.name)
                binding.foodET.setText(guest.foodPreference)
            }
        }
    }

    // Fragment wird geschlossen und Bearbeitung ist fertig
    // -> Bringe die Änderungen in die Datenbank
    override fun onStop() {
        guest.name = binding.nameET.text.toString()
        guest.foodPreference = binding.foodET.text.toString()
        if(guest.name.isBlank()){
            viewmodel.deleteGuest(guest)
        } else {
            viewmodel.insertGuest(guest)
        }
        super.onStop()
    }
}