package com.example.androidlab2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidlab2.databinding.RecyclerItemBinding
import com.example.androidlab2.model.Celebrity

class AdapterClass() :
    RecyclerView.Adapter<AdapterClass.ViewHolder>() {
    private val items: ArrayList<Celebrity> = arrayListOf()

    fun setCelebrities(celebrityList: List<Celebrity>) {
        val diffResult = DiffUtil.calculateDiff(CelebrityDiffCallBack(items, celebrityList))
        items.clear()
        items.addAll(celebrityList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterClass.ViewHolder {
        return ViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterClass.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: RecyclerItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(celebrity: Celebrity) {
            with(binding) {
                textName.text = celebrity.name
                maxNetWorth.text = "${celebrity.netWorth.toString()}$"
                textGender.text = celebrity.gender
                textNation.text = celebrity.nationality
                textBirthday.text = celebrity.birthday
                if(celebrity.heigth != null) {
                    textHeight.text = "${celebrity.heigth.toString()}m"
                } else if (celebrity.maxHeight != null) {
                    textHeight.text = "${celebrity.maxHeight.toString()}m"
                } else {
                    textHeight.text = "${celebrity.minHeight.toString()}mr"
                }
            }
        }

    }

    class CelebrityDiffCallBack(
        private val oldList: List<Celebrity>,
        private val newList: List<Celebrity>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }
}