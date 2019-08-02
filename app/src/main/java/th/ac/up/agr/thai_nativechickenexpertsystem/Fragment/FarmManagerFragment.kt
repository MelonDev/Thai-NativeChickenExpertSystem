package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_farm_manager.view.*
import kotlinx.android.synthetic.main.fragment_vaccine.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.FarmManagerAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.VaccineAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.FarmData

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView

class FarmManagerFragment : Fragment() {

    private lateinit var arrData: ArrayList<FarmData>
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_farm_manager, container, false)

        arrData = ArrayList()

        recyclerView = QuickRecyclerView(context!!
                , view.farm_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()


        recyclerView.adapter = FarmManagerAdapter(arrData)


        loadData()

        return view
    }

    private fun loadData() {


        FirebaseDatabase.getInstance().reference.child("เพิ่มเติม").child("การจัดการแต่ละระยะ").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e("", "")
            }

            override fun onDataChange(p0: DataSnapshot) {

                if (p0.value != null) {
                    arrData.clear()

                    Log.e("FMPOS", p0.childrenCount.toString())
                    p0.children.forEach {
                        arrData.add(it.getValue(FarmData::class.java)!!)
                    }
                    val arr = arrData.sortedBy { it.position }
                    arrData.clear()
                    arrData.addAll(arr)
                    recyclerView.adapter!!.notifyDataSetChanged()

                } else {
                    arrData.clear()
                }
            }
        })

    }




}
