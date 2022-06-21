package com.example.project

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.project.databinding.ItemTittleBinding

class ListAdapter(var items : ArrayList<Item>): RecyclerView.Adapter<ListAdapter.VH>(){

    companion object{
        val idNm = ArrayList<Int>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
       val binding = ItemTittleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class VH(private val binding: ItemTittleBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item : Item ){
            binding.name.text = item.name
            binding.serial.text = item.serial
            binding.state.text = item.state

            binding.check.setOnCheckedChangeListener { compoundButton, isChecked ->
                if (isChecked){
                    when(compoundButton.id){
                        R.id.check -> { idNm.add(item.id.toInt())}
                    }
                }else {
                    if (idNm.size == 1) {
                        idNm.clear()
                        return@setOnCheckedChangeListener
                    }
                    for (t in 1..idNm.size){
                        if (t == item.id.toInt()) {
                            idNm.remove(t)
                        }
                    }
                }
            }
            (binding.root.context as MainActivity).binding.delete.setOnClickListener {
                var num:String = idNm[0].toString()
                for(k in 1..idNm.size-1) num+= ", ${idNm[k]}"

                (binding.root.context as MainActivity).deleteModel.delete(num)
            }

        }

    }
}