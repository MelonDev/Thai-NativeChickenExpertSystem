package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_tab_first.*
import kotlinx.android.synthetic.main.fragment_tab_first.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.MainListVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.MainMenuVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.AddDataActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenBreedData
import th.ac.up.agr.thai_nativechickenexpertsystem.Firebase.FirebaseChicken
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.SearchActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.StringArrayToArrayList


/**
 * A simple [Fragment] subclass.
 */
class TabFirstFragment : Fragment() {

    lateinit var firebase :FirebaseChicken

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_first, container, false)

        var context  = context!!

        firebase = FirebaseChicken(view.context)

        tabMenu(view,true)

        view.main_action_add_btn.setOnClickListener {
            val intent = Intent(context, AddDataActivity::class.java)
            context.startActivity(intent)
        }


        view.tab1_menu_btn.setOnClickListener { tabMenu(view, true) }
        view.tab1_list_btn.setOnClickListener { tabMenu(view, false) }
/*
        val menuRecyclerView = QuickRecyclerView(context
                , view.main_menu_vertical_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()

        //recyclerView.adapter = FeaturesListVerticalAdapter(view.context, ArrayList<String>())

        menuRecyclerView.adapter = MainMenuVerticalAdapter(context, ArrayList<String>())
        firebase.mainLoadChicken(firebase.databaseReference.child("พันธุ์ไก่"),menuRecyclerView,0)

*/
        //val data: ArrayList<ChickenBreedData> = StringArrayToArrayList(context).mainChicken(R.array.Z0)

        //menuRecyclerView.adapter = MainMenuVerticalAdapter(context,data)
/*
        val listRecyclerView = QuickRecyclerView(context
                , view.main_list_vertical_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()

        //listRecyclerView.adapter = MainListVerticalAdapter(context,data,10)
        listRecyclerView.adapter = MainListVerticalAdapter(context,ArrayList<String>())
        //firebase.mainLoadChicken(firebase.databaseReference.child("พันธุ์ไก่"))

        view.main_searchbtn.setOnClickListener {
            val intent = Intent(context,SearchActivity::class.java)
            startActivity(intent) }

*/
        return view
    }

    private fun onFirebase(recyclerView: RecyclerView,id :Int){
        firebase.mainLoadChicken(firebase.databaseReference.child("พันธุ์ไก่"),recyclerView,id,"main","")

    }

    private fun tabMenu(view: View, switch: Boolean) {
        if (switch) {
            view.tab1_menu_on.visibility = View.VISIBLE
            view.tab1_menu_off.visibility = View.GONE
            view.tab1_list_on.visibility = View.GONE
            view.tab1_list_off.visibility = View.VISIBLE
            view.main_menu_vertical_recycler_view.visibility = View.VISIBLE
            view.main_list_vertical_recycler_view.visibility = View.GONE

            val menuRecyclerView = QuickRecyclerView(view.context
                    , view.main_menu_vertical_recycler_view
                    , "linear"
                    , 1
                    , "vertical"
                    , false
                    , "never"
                    , "high")
                    .recyclerView()

            menuRecyclerView.adapter = MainMenuVerticalAdapter(view.context, ArrayList<String>())

            onFirebase(menuRecyclerView,0)

        } else {
            view.tab1_menu_on.visibility = View.GONE
            view.tab1_menu_off.visibility = View.VISIBLE
            view.tab1_list_on.visibility = View.VISIBLE
            view.tab1_list_off.visibility = View.GONE

            view.main_menu_vertical_recycler_view.visibility = View.GONE
            view.main_list_vertical_recycler_view.visibility = View.VISIBLE


            val listRecyclerView = QuickRecyclerView(view.context
                    , view.main_list_vertical_recycler_view
                    , "linear"
                    , 1
                    , "vertical"
                    , false
                    , "never"
                    , "high")
                    .recyclerView()

            listRecyclerView.adapter = MainListVerticalAdapter(view.context, ArrayList<String>(),"other",false,"")
            onFirebase(listRecyclerView,1)


        }
    }

}