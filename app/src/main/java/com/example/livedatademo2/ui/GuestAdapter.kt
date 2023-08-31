package com.example.livedatademo2.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.livedatademo2.database.model.Guest
import com.example.livedatademo2.databinding.GuestItemBinding

class GuestAdapter(
    var dataset: List<Guest>
): RecyclerView.Adapter<GuestAdapter.ItemViewHolder>() {



    class ItemViewHolder(val binding: GuestItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun newData(newList: List<Guest>){
        dataset = newList
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            GuestItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val guest = dataset[position]

        holder.binding.nameTV.text = guest.name
        holder.binding.foodPrefTV.text = guest.foodPreference

        holder.binding.guestItemCV.setOnClickListener {
            val navcontroller =  holder.binding.root.findNavController()
            navcontroller.navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(guest.id))
        }

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}