package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tab_second_one.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.FeaturesListVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Firebase.FirebaseOnFunction

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView

class TabSecondOne : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_tab_second_one, container, false)

        setRecyclerView(view, "ภายนอก")

        return view
    }

    private fun setRecyclerView(view: View, string: String) {

        //val slot : Chicken = FirebaseLoadDetails(applicationContext).loadData(arrPath,sex)

        //Log.e("TEST SLOT",slot.image)


        val recyclerView = QuickRecyclerView(context!!
                , view.feature_one_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()

        val FirebaseFunction = FirebaseOnFunction(view.context)

        recyclerView.adapter = FeaturesListVerticalAdapter(view.context, ArrayList<String>())
        FirebaseFunction.toRecyclerView(FirebaseFunction.databaseReference.child("ลักษณะ").child(string), recyclerView)


        /*
        val detailMaleRecyclerView = QuickRecyclerView(this
                , chicken_detail_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "alway"
                , "high")
                .recyclerView()

        detailMaleRecyclerView.adapter = ChickenDetailAdapter(this,sex,arrPath)
        */
    }

}
