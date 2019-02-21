package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_tab_detail_female.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.ChickenDetailAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.ChickenDatailActivity

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.Path
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView

class TabDetailFemale : Fragment() {

    companion object {

        fun newInstance(arr :String): TabDetailFemale {
            val args = Bundle()

            args.putString("PATH",arr)
            //args.putInt("value", value)
            //args.putSerializable("testData", testData)
            val fragment = TabDetailFemale()
            fragment.arguments = args
            return fragment
        }
    }
    /*
    fun ins(arr: String) :TabDetailFemale{
        val args = Bundle()

        args.putString("PATH",arr)
        //args.putInt("value", value)
        //args.putSerializable("testData", testData)
        val fragment = TabDetailFemale()
        fragment.arguments = args
        return fragment
    }
*/
    var path = ""
    var arrPath = ArrayList<String>()

    //lateinit var myParentView: ChickenDatailActivity



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_tab_detail_female, container, false)

        path = arguments!!.getString("PATH")
        arrPath = Path().fromPathToArray(path)


        setRecyclerView(view,"เพศเมีย")


        return view
    }

    private fun setRecyclerView(view :View,sex: String){

        //val slot : Chicken = FirebaseLoadDetails(applicationContext).loadData(arrPath,sex)

        //Log.e("TEST SLOT",slot.image)

        //Toast.makeText(applicationContext,arrPath.toString(),Toast.LENGTH_SHORT).show()

        val detailFemaleRecyclerView = QuickRecyclerView(context!!
                , view.detail_female_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "alway"
                , "high")
                .recyclerView()

        detailFemaleRecyclerView.adapter = ChickenDetailAdapter(view.context,sex,arrPath)
    }

}
