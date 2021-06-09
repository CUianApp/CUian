package com.kushalsharma.cuian.ui.clubs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kushalsharma.cuian.R

class clubsAdapter() : RecyclerView.Adapter<clubsAdapter.clubsViewHolder>() {

    private val images = intArrayOf(
        R.color.cardColor,
        R.color.cardColor,
        R.color.cardColor,
        R.color.cardColor,
        R.color.cardColor,
        R.color.cardColor,
        R.color.cardColor,
        R.color.cardColor,
        R.color.cardColor
    )


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): clubsAdapter.clubsViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_clubs, parent, false)
        return clubsViewHolder(v)

    }

    override fun onBindViewHolder(holder: clubsAdapter.clubsViewHolder, position: Int) {

        holder.imgClub.setImageResource(images[position])
    }

    override fun getItemCount(): Int {

        return images.size

    }

    class clubsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imgClub: ImageView

        init {
            imgClub = itemView.findViewById<View>(R.id.club_image) as ImageView

        }

    }


}