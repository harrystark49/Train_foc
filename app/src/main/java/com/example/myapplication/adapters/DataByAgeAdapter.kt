package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.AgedetailslayoutBinding
import com.example.myapplication.entities.DetailsByAgedc


class DataByAgeAdapter(
    var passengerList: ArrayList<DetailsByAgedc>,
) : RecyclerView.Adapter<DataByAgeAdapter.vh>() {


    inner class vh(val binding: AgedetailslayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {
        val binding = AgedetailslayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return vh(binding)
    }

    override fun onBindViewHolder(holder: vh, position: Int) {
        with(holder){
            with(passengerList[position]){
                holder.binding.tname.text=this.train_name
                holder.binding.tnumber.text=this.train_num
                holder.binding.add.text=this.add
                holder.binding.dstation.text=this.dest_station
                holder.binding.sstation.text=this.source_station
                holder.binding.category.text=this.category
                holder.binding.status.text=this.ticket_status
                holder.binding.name.text=this.name
            }
        }
    }

    // return the size of trainList
    override fun getItemCount(): Int {
        return passengerList.size
    }
}
