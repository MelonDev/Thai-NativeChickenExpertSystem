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
import kotlinx.android.synthetic.main.fragment_vaccine.*
import kotlinx.android.synthetic.main.fragment_vaccine.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.VaccineAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.VaccineData
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.VaccineOnlyImageData
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.VaccineTextImageData
import th.ac.up.agr.thai_nativechickenexpertsystem.Interface.VaccineInterface

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView

class VaccineFragment : Fragment() {

    private lateinit var arrData :ArrayList<VaccineInterface>
    private lateinit var recyclerView :RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_vaccine, container, false)

        arrData = ArrayList()

        recyclerView = QuickRecyclerView(context!!
                , view.vaccine_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()


        recyclerView.adapter = VaccineAdapter(arrData)

        loadData()

        return  view
    }

    private fun loadData(){

        FirebaseDatabase.getInstance().reference.child("วัคซีน").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.e("","")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.value != null){
                    arrData.clear()
                    arrData.add(VaccineOnlyImageData())
                    Log.e("POS",p0.childrenCount.toString())
                    p0.children.forEach {

                        if(it.child("name").getValue(String::class.java)!!.contentEquals("รูป")){
                            val slot = VaccineOnlyImageData()

                            slot.name = it.key.toString()
                            slot.image = it.child("image").getValue(String::class.java)!!
                            it.child("use").children.forEach { its ->
                                slot.use.add(its.getValue(String::class.java)!!)
                            }
                            arrData.add(slot)
                        }else {
                            val slot = VaccineTextImageData()

                            slot.name = it.child("name").getValue(String::class.java)!!
                            slot.image = it.child("image").getValue(String::class.java)!!
                            it.child("use").children.forEach { its ->
                                slot.use.add(its.getValue(String::class.java)!!)
                            }

                            arrData.add(slot)
                        }


                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                }else {
                    arrData.clear()
                }
            }
        })

    }


}
