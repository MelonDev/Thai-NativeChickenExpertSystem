package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_tab_second_two.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.FeaturesListVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Firebase.FirebaseOnFunction

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView

class TabSecondTwo : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_tab_second_two, container, false)

        setRecyclerView(view, "ทางเศรษฐกิจ")

        return view
    }

    private fun setRecyclerView(view: View, string: String) {

        //val slot : Chicken = FirebaseLoadDetails(applicationContext).loadData(arrPath,sex)

        //Log.e("TEST SLOT",slot.image)


        val recyclerView = QuickRecyclerView(context!!
                , view.feature_two_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()

        val FirebaseFunction = FirebaseOnFunction(view.context)

        recyclerView.adapter = FeaturesListVerticalAdapter(view.context, ArrayList<String>())
        FirebaseFunction.toRecyclerView(FirebaseFunction.databaseReference.child("ลักษณะ").child(string), recyclerView
        )


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
