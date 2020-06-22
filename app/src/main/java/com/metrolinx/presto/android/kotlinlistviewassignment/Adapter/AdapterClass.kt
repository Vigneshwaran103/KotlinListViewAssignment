package com.metrolinx.presto.android.kotlinlistviewassignment.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metrolinx.presto.android.kotlinlistviewassignment.DataModel.DetailModelClass
import com.metrolinx.presto.android.kotlinlistviewassignment.R
import com.metrolinx.presto.android.kotlinlistviewassignment.databinding.DetailsBinding

class AdapterClass(): RecyclerView.Adapter<AdapterClass.AdapterViewHolder>() {

    var detail :List<DetailModelClass> = ArrayList()

    fun updateList(detailList:List<DetailModelClass>){
        this.detail = detailList
        notifyDataSetChanged()
    }


    class AdapterViewHolder(val binding: DetailsBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(item : DetailModelClass){
            Glide.with(binding.root.context).asDrawable().load(item.image).into(binding.image)
            binding.title.setText(item.title)
            binding.description.setText(item.description)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val detailsBinding =
            DataBindingUtil.inflate<DetailsBinding>(
                LayoutInflater.from(parent.context),
                R.layout.details,
                parent,
                false
            )
        return AdapterViewHolder(detailsBinding)
    }

    override fun getItemCount(): Int {
        return detail.size

    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.onBind(detail.get(position))

    }
}