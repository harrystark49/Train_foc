package com.example.myapplication.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.TrainlistbynamelayoutBinding
import com.example.myapplication.entities.Train


class getTrainListByNameAdapter(
    var trainList: List<Train>,
) : RecyclerView.Adapter<getTrainListByNameAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: TrainlistbynamelayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TrainlistbynamelayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(trainList[position]){
                binding.tname.text=this.train_name
                binding.dstation.text=this.destination_source
                binding.sstation.text=this.source_station
                binding.gfair.text=this.general_fair
                binding.pfair.text=this.premium_fair
                binding.tnumber.text=this.train_number

            }
        }
    }

    // return the size of trainList
    override fun getItemCount(): Int {
        return trainList.size
    }
}
