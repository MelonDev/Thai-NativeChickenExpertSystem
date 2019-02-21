package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_tab_thrid_one.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.ProgramAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.AddProgramActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.DataCard

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView
import java.util.*

class TabThridOne : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var fab: FloatingActionButton

    lateinit var myParentView: TabThirdFragment
    private var arrData = ArrayList<DataCard>()


    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    val ID = "melondev_icloud_com"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_thrid_one, container, false)

        val ref = databaseReference.child("ผู้ใช้").child(ID).child("ข้อมูล").child("ACTIVE")

        recyclerView = QuickRecyclerView(context!!
                , view.program_one_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()

        //recyclerView.

        recyclerView.adapter = ProgramAdapter(view.context, ArrayList<DataCard>())
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            var currentScrollPosition = 0
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
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

        ref.addValueEventListener(object : ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                getData(p0)

                if (arrData.size <= 1){
                    view.program_card_one_text.visibility = View.VISIBLE
                } else {
                    view.program_card_one_text.visibility = View.GONE
                }

                val a = arrData
                a.reverse()

                recyclerView.adapter = ProgramAdapter(view.context,a)


            }
        })




        //val FirebaseFunction = FirebaseOnFunction(view.context)

        //recyclerView.adapter = FeaturesListVerticalAdapter(view.context, data)




        return view
    }

    fun onClick(view: View){
        val intent = Intent(view.context, AddProgramActivity::class.java)
        intent.putExtra("ID","0")
        context!!.startActivity(intent)
    }

    fun getData(dataSnapshot: DataSnapshot?) {
        arrData.clear()
        //arrData = dataSnapshot!!.getValue(DiseaseData::class.java)!!
        dataSnapshot!!.children.mapNotNullTo(arrData){
            it.getValue(DataCard::class.java)
        }
    }



}
