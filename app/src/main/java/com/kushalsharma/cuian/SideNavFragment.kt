package com.kushalsharma.cuian

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_side_nav.view.*


class SideNavFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root =  inflater.inflate(R.layout.fragment_side_nav, container, false)

        root.aboutUs.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_sideNavFragment_to_aboutUsFragment2)

        }
        root.cancelSideNav.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_sideNavFragment_to_navigation_faculty)
        }
        root.rateUs.setOnClickListener {

            toPlayStore()

        }
        root.shareCuian.setOnClickListener {
            share()
        }
        root.contactUs.setOnClickListener {
            composeEmail()

        }


        return root
    }

    private fun share() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Hey check out CUian at: https://play.google.com/store/apps/details?id=com.kushalsharma.trust"
        )
        sendIntent.type = "text/plain"
        startActivity(sendIntent)

    }

    private fun toPlayStore() {

        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=com.kushalsharma.trust")
                )
            )
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.kushalsharma.trust")
                )
            )
        }

    }

    @SuppressLint("QueryPermissionsNeeded")
    fun composeEmail(){

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("some@email.address"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject")
        intent.putExtra(Intent.EXTRA_TEXT, "mail body")
        startActivity(Intent.createChooser(intent, ""))

    }


}