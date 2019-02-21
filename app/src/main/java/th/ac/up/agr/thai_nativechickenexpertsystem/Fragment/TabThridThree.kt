package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_tab_thrid_three.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.ProgramAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.DataCard

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView

class TabThridThree : Fragment() {

    lateinit var recyclerView: RecyclerView

    private var arrData = java.util.ArrayList<DataCard>()




    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    val ID = "melondev_icloud_com"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_tab_thrid_three, container, false)

        val ref = databaseReference.child("ผู้ใช้").child(ID).child("ข้อมูล").child("INACTIVE")

        recyclerView = QuickRecyclerView(context!!
                , view.program_three_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()

        //recyclerView.

        recyclerView.adapter = ProgramAdapter(view.context, ArrayList<DataCard>())

        ref.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                getData(p0)

                if (arrData.size <= 1){
                    view.program_card_three_text.visibility = View.VISIBLE
                } else {
                    view.program_card_three_text.visibility = View.GONE
                }

                val a = arrData
                a.reverse()

                recyclerView.adapter = ProgramAdapter(view.context,a)


            }
        })

        return view
    }

    fun getData(dataSnapshot: DataSnapshot) {
        arrData.clear()
        //arrData = dataSnapshot!!.getValue(DiseaseData::class.java)!!
        dataSnapshot!!.children.mapNotNullTo(arrData){
            it.getValue(DataCard::class.java)
        }
    }


}
