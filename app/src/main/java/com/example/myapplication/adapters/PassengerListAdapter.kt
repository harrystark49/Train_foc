package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.PassengerlayoutBinding
import com.example.myapplication.databinding.TrainlistbynamelayoutBinding
import com.example.myapplication.entities.Passenger


class PassengerListAdapter(
    var passengerList: ArrayList<Passenger>,
) : RecyclerView.Adapter<PassengerListAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: PassengerlayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PassengerlayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(passengerList[position]){
                binding.fname.text=this.first_name
                binding.lname.text=this.last_name
                binding.add.text=this.address
                binding.city.text=this.city
                binding.country.text=this.county
                binding.ssn.text=this.SSN
                binding.dob.text=this.bdate

            }
        }
    }

    // return the size of trainList
    override fun getItemCount(): Int {
        return passengerList.size
    }
}
