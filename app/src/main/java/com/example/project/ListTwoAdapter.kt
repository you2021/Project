package com.example.project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project.databinding.ItemTittleBinding
import com.example.project.databinding.ItemTittleTwoBinding

class ListTwoAdapter(var items : ArrayList<Item>): RecyclerView.Adapter<ListTwoAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
       val binding = ItemTittleTwoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class VH(private val binding: ItemTittleTwoBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item : Item ){
            binding.type.text = item.type
            binding.user.text = item.user

            val day = item.dt_reg.substring(0,10)

            binding.reg.text = day
            binding.cor.text = item.dt_cor
        }
    }
}