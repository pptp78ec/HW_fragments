package com.example.hw_fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity(), Fragment_gridview.IDataPass {

    var bundleForBack: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, Fragment_gridview::class.java, null).commit()
    }

    override fun onPassData(bundle: Bundle) {
        bundleForBack = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, Fragment_Details::class.java, bundle).commit()

    }

    override fun onBackPressed() {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container_view,
            Fragment_gridview::class.java, bundleForBack
        ).commit()
    }

}