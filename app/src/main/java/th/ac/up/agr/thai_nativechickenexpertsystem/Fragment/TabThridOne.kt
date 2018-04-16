package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tab_thrid_one.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.ProgramAdapter

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView

class TabThridOne : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_tab_thrid_one, container, false)

        val fab = view.fab_A

        val recyclerView = QuickRecyclerView(context!!
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

        recyclerView.adapter = ProgramAdapter(view.context,ArrayList<String>())

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if(dy > 0){
                    fab.hide()
                } else{
                    fab.show()
                }
                super.onScrolled(recyclerView, dx, dy)
            }

        })

        return view
    }


}
