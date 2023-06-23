package com.example.hw_fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment


/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_gridview.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_gridview : Fragment() {

    var names = mutableListOf<NameInfo>()

    interface IDataPass {
        fun onPassData(bundle: Bundle)
    }

    var dataPasser: IDataPass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments?.containsKey("savedarray") == true && !(arguments?.getSerializable("savedarray") as Array<NameInfo>).isNullOrEmpty()){
            names = (arguments?.getSerializable("savedarray") as Array<NameInfo>).toMutableList()
        }
        else {
            NameInfo.buildArray().also {
                it.shuffle()
                names = it.take(16).toMutableList()
            }
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as IDataPass

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gridview, container, false)
        val grid = view.findViewById<GridView>(R.id.grid)
        val adapter = GridAdapter(this.requireContext(), R.layout.grid_item, names)

        grid.adapter = adapter
        grid.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val name = parent?.getItemAtPosition(position)
                val namenfo =
                    names.find { nameInfo -> nameInfo.name.toLowerCase() == (name as NameInfo).name.toLowerCase() }
                if (namenfo != null) {
                    val bundle = Bundle().also {
                        it.putSerializable("passNameIndo", namenfo)
                        it.putSerializable("savedarray", names.toTypedArray())
                    }
                    dataPasser?.onPassData(bundle)
                }
            }

        }


        return view

    }


    class GridAdapter(
        val context1: Context,
        private val resource: Int,
        var objects: MutableList<NameInfo>
    ) : ArrayAdapter<NameInfo>(context1, resource, objects) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var localConvertView = convertView
            localConvertView = LayoutInflater.from(context1).inflate(resource, parent, false)
            val model = objects[position]
            localConvertView?.findViewById<TextView>(R.id.griditem_name)?.text = model?.name
            return localConvertView!!
        }
    }


}
