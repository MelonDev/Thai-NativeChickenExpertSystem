package th.ac.up.agr.thai_nativechickenexpertsystem.Firebase

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.google.firebase.database.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.Path

class FirebaseChicken(val context: Context) {

    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    private var data: ArrayList<String> = ArrayList()

    fun mainLoadChicken(ds: DatabaseReference, recyclerView: RecyclerView, id: Int,mainTitle :String,path :String) {

        data.clear()
        ds.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                data.clear()
                onSyncKey(p0)
                if (data.size > 0) {
                    //progress.visibility = View.GONE
                }
                /*
                when (id) {
                    0 -> recyclerView.adapter = MainMenuVerticalAdapter(context, data)
                    1 -> recyclerView.adapter = MainListVerticalAdapter(context,data,mainTitle,false)
                    2 -> recyclerView.adapter = MainMenuHorizontalAdapter(context,data,mainTitle,)
                    4 -> recyclerView.adapter = MainGridVerticalAdapter(context,data,id)
                    5 -> recyclerView.adapter = MainGridVerticalAdapter(context,data,id)
                    6 -> recyclerView.adapter = MainGridVerticalAdapter(context,data,id)
                    11 -> recyclerView.adapter = MainListVerticalAdapter(context,data,mainTitle,false)
                    12 -> recyclerView.adapter = MainListVerticalAdapter(context,data,mainTitle,false)
                    20 -> recyclerView.adapter = MainListVerticalAdapter(context,data,mainTitle,true)
                    21 -> recyclerView.adapter = MainListVerticalAdapter(context,data,mainTitle,true)
                }*/

                when (id){
                    0 -> { recyclerView.adapter = MainMenuVerticalAdapter(context,data) }
                    1 -> recyclerView.adapter = MainListVerticalAdapter(context,data,mainTitle,false,path)
                    2 -> recyclerView.adapter = MainMenuHorizontalAdapter(context,data,mainTitle,Path().toPath(path,mainTitle))
                    4 -> recyclerView.adapter = MainGridVerticalAdapter(context,data,id,path)
                    5 -> recyclerView.adapter = MainGridVerticalAdapter(context,data,id,path)
                    6 -> recyclerView.adapter = MainGridVerticalAdapter(context,data,id,path)
                    11 -> recyclerView.adapter = MainListVerticalAdapter(context,data,mainTitle,false,path)
                    12 -> recyclerView.adapter = MainListVerticalAdapter(context,data,mainTitle,false,path)
                    20 -> recyclerView.adapter = MainListVerticalAdapter(context,data,mainTitle,true,path)
                    21 -> recyclerView.adapter = MainListVerticalAdapter(context,data,mainTitle,true,path)
                }

            }
        })

    }

    private fun onSyncKey(dataSnapshot: DataSnapshot?) {
        dataSnapshot!!.children.mapNotNullTo(data) {
            it.key
        }
    }




}