package com.kushalsharma.cuian.ui.clubs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kushalsharma.cuian.MainActivity
import com.kushalsharma.cuian.R

class ClubsFragment : Fragment() {

    private lateinit var mAdapter: clubsAdapter
    private var recyclerView: RecyclerView? = null
    private var navIcon: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_clubs, container, false)

        recyclerView = root.findViewById(R.id.recylerView_Clubs)
        navIcon = root.findViewById(R.id.navIconClubs)

        navIcon!!.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_navigation_clubs_to_sideNavFragment2)
        }

        mAdapter = clubsAdapter()

        val layoMang = GridLayoutManager(this.context, 2)
        recyclerView!!.layoutManager = layoMang
        recyclerView!!.adapter = mAdapter


        (activity as MainActivity?)!!.showBanner(
            R.color.card_background,
            "Sorry for trouble. Clubs will be there soon.",5000,
            R.drawable.ic_baseline_insert_emoticon_24)
        return root
    }


}
