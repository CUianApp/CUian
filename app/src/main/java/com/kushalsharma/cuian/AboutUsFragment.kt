package com.kushalsharma.cuian

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_about_us.view.*


class AboutUsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_about_us, container, false)

        root.back_sideNav.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_aboutUsFragment_to_sideNavFragment2)

        }
        root.cancelAboutUS.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_aboutUsFragment_to_navigation_clubs3)
        }
        root.gitImage.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
            startActivity(browserIntent)

        }

        return root
    }


}