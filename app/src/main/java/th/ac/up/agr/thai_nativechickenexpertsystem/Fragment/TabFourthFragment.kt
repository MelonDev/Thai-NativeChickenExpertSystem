package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.disease_list_card.*
import kotlinx.android.synthetic.main.fragment_tab_fourth.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.DiseaseAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.FeaturesListVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.ContainerActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.DiseaseData

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.DiseaseViewHolder


/**
 * A simple [Fragment] subclass.
 */
class TabFourthFragment : Fragment() {

    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var key: ArrayList<String> = ArrayList()

    private var arrData = ArrayList<DiseaseData>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_fourth, container, false)

        val pathRef = databaseReference.child("โรค")
        pathRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {

                getData(p0)

                val recyclerView = QuickRecyclerView(context!!
                        , view.disease_recycler_view
                        , "linear"
                        , 1
                        , "vertical"
                        , false
                        , "never"
                        , "high")
                        .recyclerView()

                recyclerView.adapter = object : DiseaseAdapter(view.context,arrData){

                    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {
                        super.onBindViewHolder(holder, position)

                        val slot = data[position]
                        holder.title.text = slot.name
                        holder.detail.text = slot.detail

                        holder.card.setOnClickListener {
                            val intent = Intent(context, ContainerActivity::class.java)
                            //intent.putExtra("TITLE",slot)
                            intent.putExtra("ID","DISEASE")
                            intent.putExtra("TITLE",slot.name)
                            intent.putExtra("DISEASE_CAUSE",slot.cause)
                            intent.putExtra("DISEASE_DETAIL",slot.detail)
                            intent.putExtra("DISEASE_PREVENT",slot.prevent)
                            intent.putExtra("DISEASE_SYMPTOM",slot.symptom)
                            context.startActivity(intent)
                        }

                    }

                }


            }
        })


/*
        val data = ArrayList<String>()
        data.add("โรคนิวคลาสเซิล")
        data.add("โรคฝีดาษ")
        data.add("โรคอหิวาต์ไก่")
        data.add("โรคหลอดลมอักเสบ")
        data.add("โรคนิวคลาสเซิล")
        data.add("โรคฝีดาษ")
        data.add("โรคอหิวาต์ไก่")
        data.add("โรคหลอดลมอักเสบ")
        data.add("โรคนิวคลาสเซิล")
        data.add("โรคฝีดาษ")
        data.add("โรคอหิวาต์ไก่")
        data.add("โรคหลอดลมอักเสบ")

        val recyclerView = QuickRecyclerView(context!!
                , view.disease_recycler_view
                , "linear"
                , 1
                , "vertical"
                , false
                , "never"
                , "high")
                .recyclerView()

        //val FirebaseFunction = FirebaseOnFunction(view.context)

        //recyclerView.adapter = FeaturesListVerticalAdapter(view.context, data)

        recyclerView.adapter = object : DiseaseAdapter(view.context,data){

            override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)

                holder.title.text = data[position]
            }

        }

        //FirebaseFunction.toRecyclerView(FirebaseFunction.databaseReference.child("ลักษณะ").child(string), recyclerView, view.second_loading)

*/
        return view
    }

    fun getData(dataSnapshot: DataSnapshot?) {
        //arrData = dataSnapshot!!.getValue(DiseaseData::class.java)!!
        dataSnapshot!!.children.mapNotNullTo(arrData){
            it.getValue(DiseaseData::class.java)
        }
    }

    private fun onSyncKey(dataSnapshot: DataSnapshot?) {
        dataSnapshot!!.children.mapNotNullTo(key) {
            it.key
        }
    }

}
