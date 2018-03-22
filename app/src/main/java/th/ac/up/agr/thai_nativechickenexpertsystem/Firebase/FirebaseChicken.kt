package th.ac.up.agr.thai_nativechickenexpertsystem.Firebase

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.google.firebase.database.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenDetailData
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.Path

class FirebaseChicken(val context: Context) {

    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    private var data: ArrayList<String> = ArrayList()

    fun mainLoadChicken(ds: DatabaseReference, recyclerView: RecyclerView, id: Int, mainTitle: String, path: String) {

        data.clear()
        ds.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                data.clear()
                if (id == 100 || id == 101) {
                    onSyncKeyOther(p0)
                    //Log.e("TEST",data.toString())
                } else {
                    onSyncKey(p0)
                }
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

                when (id) {
                    0 -> {
                        recyclerView.adapter = MainMenuVerticalAdapter(context, sortMainData(data, true))
                    }
                    1 -> recyclerView.adapter = MainListVerticalAdapter(context, sortMainData(data, false), mainTitle, false, path)
                    2 -> recyclerView.adapter = MainMenuHorizontalAdapter(context, data, mainTitle, Path().toPath(path, mainTitle))
                    4 -> recyclerView.adapter = MainGridVerticalAdapter(context, data, id, path, mainTitle)
                    5 -> recyclerView.adapter = MainGridVerticalAdapter(context, data, id, path, mainTitle)
                    6 -> recyclerView.adapter = MainGridVerticalAdapter(context, data, id, path, mainTitle)
                    11 -> recyclerView.adapter = MainListVerticalAdapter(context, data, mainTitle, false, path)
                    12 -> recyclerView.adapter = MainListVerticalAdapter(context, data, mainTitle, false, path)
                    20 -> recyclerView.adapter = MainListVerticalAdapter(context, data, mainTitle, true, path)
                    21 -> recyclerView.adapter = MainListVerticalAdapter(context, data, mainTitle, true, path)
                    100 -> recyclerView.adapter = MainMenuHorizontalAdapter(context, data, "พันธุ์อื่นๆ", "")
                    101 -> recyclerView.adapter = MainGridVerticalAdapter(context, data, 100, "","พันธุ์อื่นๆ")
                }

            }
        })

    }

    private fun sortMainData(datas: ArrayList<String>, isMenu: Boolean): ArrayList<String> {
        val arr = ArrayList<String>()
        val arrBack = ArrayList<String>()
        for (i in datas) {
            when (i) {
                "ไก่ชน" -> arr.add(i)
                "ไก่แจ้" -> arr.add(i)
                "ไก่ดำ" -> arr.add(i)
                else -> {
                    if (!isMenu) {
                        arrBack.add(i)
                    }
                }
            }
        }

        if (isMenu) {
            arr.add("ไก่พม่า")
            arrBack.add("พันธุ์อื่นๆ")
        }

        arr += arrBack
        return arr
    }

    private fun onSyncKeyOther(dataSnapshot: DataSnapshot?) {
        val a = ArrayList<String>()
        val b = ArrayList<String>()

        dataSnapshot!!.children.mapNotNullTo(a) {
            it.key
        }

        a.filter {
                    (!it.contentEquals("ไก่ชน")
                            and !it.contentEquals("ไก่แจ้")
                            and !it.contentEquals("ไก่ดำ")
                            and !it.contentEquals("ไก่พม่า"))
                }
                .forEach { data.add(it) }

    }

    private fun onSyncKey(dataSnapshot: DataSnapshot?) {
        dataSnapshot!!.children.mapNotNullTo(data) {
            it.key
        }
    }

    fun getImageToRecyclerView(): String {


        return ""
    }


}