package com.kushalsharma.cuian.ui.faculty

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.kushalsharma.cuian.MainActivity
import com.kushalsharma.cuian.R
import com.kushalsharma.cuian.modals.FireUtils
import kotlinx.android.synthetic.main.fragment_faculty.*
import kotlinx.android.synthetic.main.fragment_faculty.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FacultyFragment : Fragment(), cpyItemClicked {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var adapter: FireAdapter

    private var recyclerView: RecyclerView? = null
    private var swipeRefresh: SwipeRefreshLayout? = null
    private var sideNavBtn: ImageView? = null
    private var searchEditText: EditText? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_faculty, container, false)

        sideNavBtn = root.findViewById(R.id.side_nav_icon)
        swipeRefresh = root.findViewById(R.id.swipeRefresh)
        recyclerView = root.findViewById(R.id.recyclerView_faculty) as RecyclerView
        searchEditText = root.findViewById(R.id.textInputEditText)

        sideNavBtn!!.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_navigation_faculty_to_sideNavFragment2)
        }

            setupRecylcerView()





        swipeRefresh!!.setOnRefreshListener {

                updateoperation()
                Handler(Looper.myLooper()!!).postDelayed({
                    swipeRefresh!!.isRefreshing = false
                }, 1000)

        }

        searchEditText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                val query: Query
                if (s.toString().isEmpty()) {
                    query = db.collection("FacultyData")
                        .orderBy("fName").limit(300)
                } else {
                    query = db.collection("FacultyData")
                        .orderBy("fName").limit(300)
//                         .whereEqualTo("fName",s.toString())
                        .startAt(s.toString()).endAt(s.toString() + "\uf8ff")

                }


                val options = FirestoreRecyclerOptions.Builder<FireUtils>()
                    .setQuery(query, FireUtils::class.java)
                    .setLifecycleOwner(this@FacultyFragment).build()
                adapter.updateOptions(options)

            }
        })



        return root
    }

    private fun setupRecylcerView() {


        val query = db.collection("FacultyData")
            .orderBy("fName").limit(300)

        val options = FirestoreRecyclerOptions.Builder<FireUtils>()
            .setQuery(query, FireUtils::class.java)
            .setLifecycleOwner(this).build()


        adapter = FireAdapter(options, this)
        recyclerView!!.adapter = adapter
        recyclerView!!.layoutManager = LinearLayoutManager(this.context)

    }


    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


    private fun updateoperation() {


            if (!(activity as MainActivity?)!!.netConnect(requireActivity().applicationContext)) {
                (activity as MainActivity?)!!.showBanner(
                    R.color.red,
                    "You are not connected to internet.",
                    5000, R.drawable.ic_baseline_wifi_off_24
                )
            } else {
                    setupRecylcerView()



            }





    }

    override fun oncopyClicked(cItem: String, view: View) {
        (activity as MainActivity?)!!.showBanner(
            R.color.green,
            "Copied to your clipboard.",
            1000,
            R.drawable.ic_baseline_check_circle_24
        )
        val Clipboard =
            view.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val myClip = ClipData.newPlainText(cItem, cItem)
        Clipboard.setPrimaryClip(myClip)
    }

    override fun onPhcopyClicked(pItem: String, view: View) {
        (activity as MainActivity?)!!.showBanner(
            R.color.green,
            "Copied to your clipboard. ",
            1000, R.drawable.ic_baseline_check_circle_24
        )
        val Clipboard =
            view.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val myClip = ClipData.newPlainText(pItem, pItem)
        Clipboard.setPrimaryClip(myClip)
    }
}

