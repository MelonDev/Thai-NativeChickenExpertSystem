package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        val view = inflater.inflate(R.layout.fragment_tab_second, container, false)

        //var s = FirebaseClient(view.context)
        //s.update()

        //Toast.makeText(context,"TEST",Toast.LENGTH_SHORT).show()

        tabSelection(view, "ภายนอก")

        //Log.e("TEST","TEST")

        view.secondLeftTab.setOnClickListener {
            tabSelection(view, "ภายนอก")
        }
        view.secondRightTab.setOnClickListener {
            tabSelection(view, "เศรษฐกิจ")
        }

/*
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
*/
        return view
    }


    private fun tabSelection(view: View, string: String) {

        loadingRecyclerview(view)

        //Log.e("ID",id.toString())

        //Toast.makeText(context,string,Toast.LENGTH_SHORT).show()

        when (string) {
            "ภายนอก" -> {
                //Toast.makeText(context,string+"Hellose",Toast.LENGTH_SHORT).show()
                view.secondLeftLine?.visibility = View.VISIBLE
                view.secondRightLine?.visibility = View.GONE
                setRecyclerView(view, "ภายนอก")

            }
            "เศรษฐกิจ" -> {
                //Toast.makeText(context,string+"Hellose",Toast.LENGTH_SHORT).show()
                view.secondLeftLine?.visibility = View.GONE
                view.secondRightLine?.visibility = View.VISIBLE
                setRecyclerView(view, "ทางเศรษฐกิจ")

            }
        }

    }

    private fun loadingRecyclerview(view: View) {
        val handler = Handler()

        view.second_loading?.visibility = View.VISIBLE
        //chicken_detail_recycler_view.visibility = View.GONE
        view.feature_recycler_view?.visibility = View.GONE

        val runnable = Runnable {
            view.second_loading?.visibility = View.GONE
            view.feature_recycler_view?.visibility = View.VISIBLE
            //chicken_detail_recycler_view.visibility = View.VISIBLE
        }

        var delay_time: Long = 0
        var time = 1L

        delay_time = time
        handler.postDelayed(runnable, delay_time)
    }

    private fun setRecyclerView(view: View, string: String) {

        //val slot : Chicken = FirebaseLoadDetails(applicationContext).loadData(arrPath,sex)

        //Log.e("TEST SLOT",slot.image)


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
        FirebaseFunction.toRecyclerView(FirebaseFunction.databaseReference.child("ลักษณะ").child(string), recyclerView, view.second_loading)


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
