package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityTrainWithPassengerCountBinding
import com.example.myapplication.databinding.PassengercountBinding
import com.example.myapplication.databinding.PassengerlayoutBinding
import com.example.myapplication.entities.Passenger
import com.example.myapplication.entities.TwoValues


class passengercountAdapter(
    var passengerList: ArrayList<TwoValues>,
) : RecyclerView.Adapter<passengercountAdapter.vh>() {


    inner class vh(val binding: PassengercountBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {
        val binding = PassengercountBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return vh(binding)
    }

    override fun onBindViewHolder(holder: vh, position: Int) {
        with(holder){
            with(passengerList[position]){
                holder.binding.tname1.text=this.val1
                holder.binding.tcount1.text=this.val2
            }
        }
    }

    // return the size of trainList
    override fun getItemCount(): Int {
        return passengerList.size
    }
}
