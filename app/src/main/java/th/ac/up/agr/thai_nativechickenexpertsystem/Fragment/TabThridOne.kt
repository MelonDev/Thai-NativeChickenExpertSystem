package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_tab_thrid_one.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.ProgramAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.AddProgramActivity

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView

class TabThridOne : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var fab: FloatingActionButton

    lateinit var myParentView: TabThirdFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_tab_thrid_one, container, false)

        recyclerView = QuickRecyclerView(context!!
                , view.program_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()


        //val FirebaseFunction = FirebaseOnFunction(view.context)

        //recyclerView.adapter = FeaturesListVerticalAdapter(view.context, data)

        recyclerView.adapter = ProgramAdapter(view.context, ArrayList<String>())

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {


            var currentScrollPosition = 0
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) {
                    fab.hide()
                } else {
                    fab.show()
                }
                currentScrollPosition += dy

                /*
                if (currentScrollPosition == 0) {
                    Toast.makeText(context, "TOP", Toast.LENGTH_SHORT).show()
                }
             */
                super.onScrolled(recyclerView, dx, dy)
            }

        })
        fab.setOnClickListener { onClick(view) }

        return view
    }

    fun onClick(view: View){
        val intent = Intent(view.context, AddProgramActivity::class.java)
        context!!.startActivity(intent)
    }


}
