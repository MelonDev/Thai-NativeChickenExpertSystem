package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_tab_fifth.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.MoreMenuAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.IconSet

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView

class TabFifthFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_fifth, container, false)
        val arr = ArrayList<IconSet>()
        arr.add(setIconSet("อาหาร",R.drawable.ic_seed_off))
        arr.add(setIconSet("วัคซีน",R.drawable.ic_vaccine))
        arr.add(setIconSet("ลักษณะ",R.drawable.ic_feather_off))
        //arr.add(setIconSet("Privacy Policy",R.drawable.ic_seed_off))



        val recyclerView = QuickRecyclerView(view.context
                , view.more_recyclerview
                , "grid"
                , 3
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()

        recyclerView.adapter = MoreMenuAdapter(view.context,arr)

        return view
    }

    private fun setIconSet(name: String,ID :Int) : IconSet{
        return IconSet(name,ID)
    }

}// Required empty public constructor
