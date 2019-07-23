package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import th.ac.up.agr.thai_nativechickenexpertsystem.R

class TabFoodFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_food, container, false)



        return view
    }


}
