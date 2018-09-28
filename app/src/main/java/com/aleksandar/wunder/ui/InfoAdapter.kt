package com.aleksandar.wunder.ui

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aleksandar.wunder.R
import com.aleksandar.wunder.vo.Placemarks

class InfoAdapter(private val placemarks: Placemarks, val listItemClickListener: ListItemClickListener) : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val layout: ConstraintLayout = itemView.findViewById(R.id.item_info_layout)
        val addressTextView: TextView = itemView.findViewById(R.id.item_info_address)
        val engineTypeTextView: TextView = itemView.findViewById(R.id.item_info_engineType)
        val exteriorTextView: TextView = itemView.findViewById(R.id.item_info_exterior)
        val fuelTextView: TextView = itemView.findViewById(R.id.item_info_fuel)
        val interiorTextView: TextView = itemView.findViewById(R.id.item_info_interior)
        val nameTextView: TextView = itemView.findViewById(R.id.item_info_name)
        val vinTextView: TextView = itemView.findViewById(R.id.item_info_vin)

        init {
            layout.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val itemPosition = adapterPosition

            when (view!!.id){
                layout.id -> listItemClickListener.selectItem(itemPosition, placemarks.placemark.get(itemPosition))
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.addressTextView.text = (placemarks.placemark.get(position).address)
        holder.engineTypeTextView.text = (placemarks.placemark.get(position).engineType)
        holder.exteriorTextView.text = (placemarks.placemark.get(position).exterior)
        holder.fuelTextView.text = (placemarks.placemark.get(position).fuel.toString())
        holder.interiorTextView.text = (placemarks.placemark.get(position).interior)
        holder.nameTextView.text = (placemarks.placemark.get(position).name)
        holder.vinTextView.text = (placemarks.placemark.get(position).vin)
    }

    // Return the size of the dataSet
    override fun getItemCount(): Int {
        return placemarks.placemark.size
    }
}