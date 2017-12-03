package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tab_first.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.R


/**
 * A simple [Fragment] subclass.
 */
class TabFirstFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_tab_first, container, false)

        view.tab1_menu_btn.setOnClickListener { tabMenu(view,true) }
        view.tab1_list_btn.setOnClickListener { tabMenu(view,false) }

        return view
    }

    private fun tabMenu(view: View ,switch: Boolean){
        if (switch){
            view.tab1_menu_on.visibility = View.VISIBLE
            view.tab1_menu_off.visibility = View.GONE
            view.tab1_list_on.visibility = View.GONE
            view.tab1_list_off.visibility = View.VISIBLE
        } else {
            view.tab1_menu_on.visibility = View.GONE
            view.tab1_menu_off.visibility = View.VISIBLE
            view.tab1_list_on.visibility = View.VISIBLE
            view.tab1_list_off.visibility = View.GONE
        }
    }

}