package com.kushalsharma.cuian.ui.faculty

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.kushalsharma.cuian.MainActivity
import com.kushalsharma.cuian.MySingleton
import com.kushalsharma.cuian.R
import com.kushalsharma.cuian.modals.PersonUtils
import kotlinx.android.synthetic.main.fragment_faculty.*
import org.json.JSONException

class FacultyFragment : Fragment(), cpyItemClicked {

    var recyclerView: RecyclerView? = null
    var mAdapter: RecyclerView.Adapter<*>? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var personUtilsList: MutableList<PersonUtils>? = null
    var personUtils: PersonUtils? = null
    var rq: RequestQueue? = null
    var request_url = "https://sheetdb.io/api/v1/xs9vpziuescng"
    private lateinit var adp: FacultyAdapter
    var sideNavBtn : ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_faculty, container, false)

        recyclerView = root.findViewById<View>(R.id.recyclerView_faculty) as RecyclerView
        sideNavBtn = root.findViewById(R.id.side_nav_icon)

           sideNavBtn!!.setOnClickListener {
                    Navigation.findNavController(it).navigate(R.id.action_navigation_faculty_to_sideNavFragment2)
                }

        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpRecyclerView()

        adp = FacultyAdapter(this, personUtilsList!!, this)


        filterBtnLayout.setOnClickListener {

            if (ll_filterBtns.getVisibility() == View.GONE) {
                ll_filterBtns.setVisibility(View.VISIBLE)
                arrowIcon.setImageResource(R.drawable.up_arrow)

            } else {
                ll_filterBtns.setVisibility(View.GONE)
                arrowIcon.setImageResource(R.drawable.down_arrow)
            }

        }

        swipeRefresh.setOnRefreshListener {

            updateoperation()
            swipeRefresh.isRefreshing = false

        }


    }


    private fun updateoperation() {
        setUpRecyclerView()
    }


    private fun setUpRecyclerView() {
        rq = Volley.newRequestQueue(this.context)
        recyclerView!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this.context)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = layoutManager
        personUtilsList = java.util.ArrayList()
        sendRequest()


    }

    fun sendRequest() {
        progress_Bar.visibility = View.VISIBLE
        val jsonArrayRequest =
            JsonArrayRequest(Request.Method.GET, request_url, null, { response ->

                for (i in 0 until response.length()) {
                    val personUtils = PersonUtils()
                    try {
                        val jsonObject = response.getJSONObject(i)
                        personUtils.personFirstName = (jsonObject.getString("firstname"))
                        personUtils.personLastName = (jsonObject.getString("lastname"))
                        personUtils.jobProfile = (jsonObject.getString("jobprofile"))
                        personUtils.Department = (jsonObject.getString("Department"))
                        personUtils.Email = (jsonObject.getString("Email"))
                        personUtils.PhoneNo = (jsonObject.getString("Phone No."))
                        personUtils.imgUrl = (jsonObject.getString("Image Link"))
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    personUtilsList!!.add(personUtils)
                }
                progress_Bar.visibility = View.INVISIBLE

                mAdapter = FacultyAdapter(this, personUtilsList!!, this)
                (mAdapter as FacultyAdapter).notifyDataSetChanged()
                recyclerView!!.adapter = mAdapter

            })


            { error ->

                (activity as MainActivity?)!!.showBanner(
                    R.color.red,
                    "Looks like you are not connected to the Internet.",
                    5000
                )

            }
        MySingleton.getInstance(this.context)!!.addToRequestQueue(jsonArrayRequest)


    }


    override fun oncopyClicked(cItem: PersonUtils, view: View) {

        (activity as MainActivity?)!!.showBanner(
            R.color.green,
            "${cItem.Email.toString()} " +
                    "is copied to your clipboard, " +
                    "now you can paste it anywhere you want.",
            1500
        )
        val Clipboard =
            view.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val myClip = ClipData.newPlainText(cItem.Email.toString(), cItem.Email.toString())
        Clipboard.setPrimaryClip(myClip)


    }

    override fun onPhcopyClicked(pItem: PersonUtils, view: View) {
        (activity as MainActivity?)!!.showBanner(
            R.color.green,
            "${pItem.Email.toString()} " +
                    "copied to your clipboard, " +
                    "now you can paste it anywhere you want.",
            1500
        )
        val Clipboard =
            view.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val myClip = ClipData.newPlainText(pItem.PhoneNo.toString(), pItem.PhoneNo.toString())
        Clipboard.setPrimaryClip(myClip)


    }


}

