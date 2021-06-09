package com.kushalsharma.cuian.ui.faculty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kushalsharma.cuian.R
import com.kushalsharma.cuian.modals.PersonUtils


class FacultyAdapter(
    private val context: FacultyFragment, personUtils: List<PersonUtils>,
    private val listner: cpyItemClicked
) :
    RecyclerView.Adapter<FacultyAdapter.ViewHolder>() {

    private var personUtils: MutableList<PersonUtils>
    private var utilsFilterList: MutableList<PersonUtils>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_faculty, parent, false)

        val viewHolder = ViewHolder(view)

        viewHolder.cpyMail!!.setOnClickListener {
            listner.oncopyClicked(personUtils[viewHolder.adapterPosition], it)
        }
        viewHolder.cpyph!!.setOnClickListener {
            listner.onPhcopyClicked(personUtils[viewHolder.adapterPosition], it)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tag = personUtils[position]
        val pu = personUtils[position]
        holder.pName.text = pu.personFirstName.toString() + pu.personLastName.toString()
        holder.pJobProfile.text = pu.jobProfile
        holder.Department.text = pu.Department
        holder.email.text = pu.Email
        holder.phN.text = "+91-" + pu.PhoneNo
        Glide.with(this.context).load(pu.imgUrl.toString())
            .transform(RoundedCorners(12))
            .into(holder.img!!)

    }

    fun updateList(temp: MutableList<PersonUtils>) {
        personUtils = temp
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return personUtils.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var pName: TextView
        var pJobProfile: TextView
        var Department: TextView
        var email: TextView
        var phN: TextView
        var img: ImageView? = null
        var cpyph: CardView? = null
        var cpyMail: CardView? = null


        init {
            pName = itemView.findViewById<View>(R.id.pName) as TextView
            pJobProfile = itemView.findViewById<View>(R.id.pJobProfile) as TextView
            Department = itemView.findViewById<View>(R.id.pDepartment) as TextView
            email = itemView.findViewById<View>(R.id.pEmail) as TextView
            phN = itemView.findViewById<View>(R.id.pPhoneNumber) as TextView
            img = itemView.findViewById<View>(R.id.pImage) as ImageView
            cpyph = itemView.findViewById<View>(R.id.phoneCard) as CardView
            cpyMail = itemView.findViewById<View>(R.id.emailCard) as CardView
        }
    }

    init {
        this.personUtils = personUtils as MutableList<PersonUtils>
        utilsFilterList = personUtils.toMutableList()
    }
}

interface cpyItemClicked {
    fun oncopyClicked(cItem: PersonUtils, view: View)
    fun onPhcopyClicked(pItem: PersonUtils, view: View)
}

