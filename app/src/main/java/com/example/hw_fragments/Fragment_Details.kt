package com.example.hw_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_Details.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_Details : Fragment() {
        var nameInfo :NameInfo? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nameInfo = arguments?.getSerializable("passNameIndo") as NameInfo
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData(nameInfo!!)
    }

    fun setData (nameInfo: NameInfo){
        view?.findViewById<TextView>(R.id.name)?.text = nameInfo.name
        view?.findViewById<TextView>(R.id.namesakedate)?.text = nameInfo.dates
        view?.findViewById<TextView>(R.id.description)?.text = nameInfo.description
    }

}