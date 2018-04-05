package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tab_fourth.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.FeaturesListVerticalAdapter

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView


/**
 * A simple [Fragment] subclass.
 */
class TabFourthFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_fourth, container, false)


        val data = ArrayList<String>()
        data.add("โรคนิวคลาสเซิล")
        data.add("โรคฝีดาษ")
        data.add("โรคอหิวาต์ไก่")
        data.add("โรคหลอดลมอักเสบ")
        data.add("โรคนิวคลาสเซิล")
        data.add("โรคฝีดาษ")
        data.add("โรคอหิวาต์ไก่")
        data.add("โรคหลอดลมอักเสบ")
        data.add("โรคนิวคลาสเซิล")
        data.add("โรคฝีดาษ")
        data.add("โรคอหิวาต์ไก่")
        data.add("โรคหลอดลมอักเสบ")

        val recyclerView = QuickRecyclerView(context!!
                , view.disease_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()

        //val FirebaseFunction = FirebaseOnFunction(view.context)

        recyclerView.adapter = FeaturesListVerticalAdapter(view.context, data)
        //FirebaseFunction.toRecyclerView(FirebaseFunction.databaseReference.child("ลักษณะ").child(string), recyclerView, view.second_loading)


        return view
    }
}
