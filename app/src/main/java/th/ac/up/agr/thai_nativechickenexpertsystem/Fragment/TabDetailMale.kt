package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_tab_detail_male.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.ChickenDetailAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.ChickenDatailActivity

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.Path
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView

class TabDetailMale : Fragment() {


    companion object {

        fun newInstance(arr :String): TabDetailMale {
            val args = Bundle()
            args.putString("PATH",arr)
            //args.putInt("value", value)
            //args.putSerializable("testData", testData)
            val fragment = TabDetailMale()
            fragment.arguments = args
            return fragment
        }
    }
  /*
    fun ins(arr: String) :TabDetailMale{
        val args = Bundle()
        args.putString("PATH",arr)
        //args.putInt("value", value)
        //args.putSerializable("testData", testData)
        val fragment = TabDetailMale()
        fragment.arguments = args
        return fragment
    }
*/
    var path = ""
    var arrPath = ArrayList<String>()

    //lateinit var myParentView: ChickenDatailActivity


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_tab_detail_male, container, false)

        path = arguments!!.getString("PATH")
        arrPath = Path().fromPathToArray(path)


        Log.e("asda",arrPath.toString())
        setRecyclerView(view,"เพศผู้")

        return view
    }

    private fun setRecyclerView(view :View,sex: String){

        //val slot : Chicken = FirebaseLoadDetails(applicationContext).loadData(arrPath,sex)

        //Log.e("TEST SLOT",slot.image)

        //Toast.makeText(applicationContext,arrPath.toString(),Toast.LENGTH_SHORT).show()

        val detailMaleRecyclerView = QuickRecyclerView(context!!
                , view.detail_male_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "alway"
                , "high")
                .recyclerView()

        detailMaleRecyclerView.adapter = ChickenDetailAdapter(view.context,sex,arrPath)
    }

}
