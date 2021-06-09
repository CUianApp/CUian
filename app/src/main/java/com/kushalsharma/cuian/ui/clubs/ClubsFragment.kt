package com.kushalsharma.cuian.ui.clubs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.kushalsharma.cuian.R
import kotlinx.android.synthetic.main.fragment_clubs.*
import kotlinx.android.synthetic.main.fragment_clubs.view.*

class ClubsFragment : Fragment() {

//    private var layoutManager: RecyclerView.LayoutManager? = null
//    private var recylerViewClubs : RecyclerView? = null
//    private var adapter: RecyclerView.Adapter<clubsAdapter.clubsViewHolder>? = null

    private lateinit var mAdapter: clubsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_clubs, container, false)

        root.navIconClubs.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_navigation_clubs_to_sideNavFragment2)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mAdapter = clubsAdapter()

        settrinRe()

    }

    @SuppressLint("WrongConstant")
    private fun settrinRe() {
        var layoMang = GridLayoutManager(this.context, 2)
        recylerView_Clubs.layoutManager = layoMang
        recylerView_Clubs.adapter = mAdapter


    }

}
