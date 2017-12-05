package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tab_first.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.MainListVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.MainMenuVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenBreedData
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.StringArrayToArrayList


/**
 * A simple [Fragment] subclass.
 */
class TabFirstFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_tab_first, container, false)

        tabMenu(view,true)

        view.tab1_menu_btn.setOnClickListener { tabMenu(view, true) }
        view.tab1_list_btn.setOnClickListener { tabMenu(view, false) }

        val menuRecyclerView = QuickRecyclerView(context
                , view.main_menu_vertical_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()

        val data: ArrayList<ChickenBreedData> = StringArrayToArrayList(context).mainChicken(R.array.Z0)

        menuRecyclerView.adapter = MainMenuVerticalAdapter(context,data,20)

        val listRecyclerView = QuickRecyclerView(context
                , view.main_list_vertical_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()

        listRecyclerView.adapter = MainListVerticalAdapter(context,data,10)


        return view
    }

    private fun tabMenu(view: View, switch: Boolean) {
        if (switch) {
            view.tab1_menu_on.visibility = View.VISIBLE
            view.tab1_menu_off.visibility = View.GONE
            view.tab1_list_on.visibility = View.GONE
            view.tab1_list_off.visibility = View.VISIBLE
            view.main_menu_vertical_recycler_view.visibility = View.VISIBLE
            view.main_list_vertical_recycler_view.visibility = View.GONE
        } else {
            view.tab1_menu_on.visibility = View.GONE
            view.tab1_menu_off.visibility = View.VISIBLE
            view.tab1_list_on.visibility = View.VISIBLE
            view.tab1_list_off.visibility = View.GONE

            view.main_menu_vertical_recycler_view.visibility = View.GONE
            view.main_list_vertical_recycler_view.visibility = View.VISIBLE
        }
    }

}