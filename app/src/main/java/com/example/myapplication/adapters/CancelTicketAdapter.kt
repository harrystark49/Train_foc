package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityCancelTicketBinding
import com.example.myapplication.databinding.DeletelayoutBinding
import com.example.myapplication.databinding.TrainlistbynamelayoutBinding
import com.example.myapplication.entities.Booked
import com.example.myapplication.entities.Train


class CancelTicketAdapter(
    var trainList: ArrayList<Booked>,
    var deletetick: (pssn:String,trainN:String)->Unit
) : RecyclerView.Adapter<CancelTicketAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: DeletelayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DeletelayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(trainList[position]){
                binding.pssn.text=this.p_ssn
                binding.status.text=this.Status
                binding.tnum.text=this.train_number
                binding.ttype.text=this.ticket_type

                binding.delete.setOnClickListener {
                    deletetick(this.p_ssn,this.train_number)
                }

            }
        }
    }

    // return the size of trainList
    override fun getItemCount(): Int {
        return trainList.size
    }

}
