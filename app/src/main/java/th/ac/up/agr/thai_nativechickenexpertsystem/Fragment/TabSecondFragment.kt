package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tab_first.view.*
import kotlinx.android.synthetic.main.fragment_tab_second.*
import kotlinx.android.synthetic.main.fragment_tab_second.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.FeaturesListVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.MainListVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Firebase.FirebaseClient
import th.ac.up.agr.thai_nativechickenexpertsystem.Firebase.FirebaseOnFunction

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView


/**
 * A simple [Fragment] subclass.
 */
class TabSecondFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_tab_second, container, false)

        var s = FirebaseClient(view.context)
        //s.update()

        val recyclerView = QuickRecyclerView(context!!
                , view.feature_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()

        val FirebaseFunction = FirebaseOnFunction(view.context)

        recyclerView.adapter = FeaturesListVerticalAdapter(view.context, ArrayList<String>())
        FirebaseFunction.toRecyclerView(FirebaseFunction.databaseReference.child("ลักษณะ").child("ภายนอก"),recyclerView,view.second_loading)

        return view
    }
}
