package com.example.satellitesinspace.presentation.satellite_list

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.satellitesinspace.R
import com.example.satellitesinspace.data.model.SatelliteListItem
import com.example.satellitesinspace.databinding.SatelliteListRvItemBinding

class SatelliteRecyclerView(
    val context: Context,
    var satelliteList: MutableList<SatelliteListItem>,
    val isClicked: (Int,String) -> Unit
) :
    RecyclerView.Adapter<SatelliteRecyclerView.SatelliteViewHolder>() {

    inner class SatelliteViewHolder(var binding: SatelliteListRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(satelliteItem: SatelliteListItem) {
            if (satelliteItem.active) {
                binding.isSatelliteActive.text = "Active"
                binding.imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.bg_green_circle))
                binding.satelliteName.setTypeface(binding.satelliteName.typeface, Typeface.BOLD)
                binding.isSatelliteActive.setTextColor(AppCompatResources.getColorStateList(context, R.color.black))
                binding.satelliteName.setTextColor(AppCompatResources.getColorStateList(context, R.color.black))
            } else {
                binding.isSatelliteActive.text = "Passive"
                binding.imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.bg_red_circle))
            }
            binding.satelliteName.text = satelliteItem.name
            binding.satelliteItemRoot.setOnClickListener {
                isClicked.invoke(satelliteItem.id,satelliteItem.name)
            }
            if (satelliteList.last() == satelliteItem && satelliteList.size != 1) binding.seperator.visibility =
                View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatelliteViewHolder {
        val binding = SatelliteListRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SatelliteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SatelliteViewHolder, position: Int) {
        val satelliteListItem = satelliteList[position]
        holder.bind(satelliteListItem)
    }

    override fun getItemCount(): Int {
        return satelliteList.size
    }

    fun updateList(newList: MutableList<SatelliteListItem>) {
        satelliteList.clear()
        satelliteList = newList
        notifyDataSetChanged()
    }

}