package com.example.livedatademo2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.livedatademo2.database.model.Guest
import com.example.livedatademo2.databinding.GuestItemBinding

class GuestAdapter(
    var dataset: List<Guest>,
) : RecyclerView.Adapter<GuestAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: GuestItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun newData(newList: List<Guest>) {
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

        if (position < dataset.size) {

            //Steuere die Sichtbarkeit der Views damit das "recyclen" der ViewHolder
            //nicht zu Problemen führt.
            holder.binding.nameTV.visibility = View.VISIBLE
            holder.binding.foodPrefTV.visibility = View.VISIBLE
            holder.binding.newGuestTV.visibility = View.GONE

            //Für alle Gäste in der Liste, fülle den ViewHolder mit Inhalt
            val guest = dataset[position]

            holder.binding.nameTV.text = guest.name
            holder.binding.foodPrefTV.text = guest.foodPreference

            holder.binding.guestItemCV.setOnClickListener {
                val navcontroller = holder.binding.root.findNavController()
                navcontroller.navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                        guest.id
                    )
                )
            }
        } else if(position == dataset.size){

            //Für das extra(letzte) Element, richten den ViewHolder so ein
            //dass wir einen neuen Guest erstellen können.
            holder.binding.nameTV.visibility = View.GONE
            holder.binding.foodPrefTV.visibility = View.GONE
            holder.binding.newGuestTV.visibility = View.VISIBLE

            holder.binding.guestItemCV.setOnClickListener {
                val navcontroller = holder.binding.root.findNavController()
                //übergebe guestId -1, damit das DetailFragment weiss,
                //dass ein neuer Guest erstellt werden soll
                navcontroller.navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                        -1L
                    )
                )
            }
        }

    }

    override fun getItemCount(): Int {
        return dataset.size + 1
    }
}