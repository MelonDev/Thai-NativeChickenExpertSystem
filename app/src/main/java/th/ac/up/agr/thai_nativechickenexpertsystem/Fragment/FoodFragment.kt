package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_food.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.FoodAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.FoodData

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView


class FoodFragment : Fragment() {

    private lateinit var arrData :ArrayList<FoodData>
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_food, container, false)

        arrData = ArrayList()

        recyclerView = QuickRecyclerView(context!!
                , view.food_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()


        recyclerView.adapter = FoodAdapter(arrData)


        return view
    }


}
