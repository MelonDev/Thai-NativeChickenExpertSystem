package th.ac.up.agr.thai_nativechickenexpertsystem.Firebase

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.google.firebase.database.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.FeaturesListVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.MainListVerticalAdapter

class FirebaseOnFunction(val context: Context) {

    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference


    private var data :ArrayList<String> = ArrayList()

    fun loadAllKey(ds :DatabaseReference) {
        data.clear()
        ds.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                onSyncKey(p0)
                showStringArrayByLog("key",data)
            }
        })
    }

    fun singleLoadAllKey(ds :DatabaseReference) {
        data.clear()
        ds.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                onSyncKey(p0)
                showStringArrayByLog("key",data)
            }
        })

    }

    fun toRecyclerView(ds: DatabaseReference,recyclerView: RecyclerView){
        //progress.visibility = View.VISIBLE

        data.clear()
        ds.addValueEventListener(object  :ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                data.clear()
                onSyncKey(p0)
                if (data.size > 0){
                    //progress.visibility = View.GONE
                }
                recyclerView.adapter = FeaturesListVerticalAdapter(context,data)            }
        })
    }

    private fun onSyncKey(dataSnapshot: DataSnapshot?) {
        dataSnapshot!!.children.mapNotNullTo(data){
            it.key
        }
    }

    private fun showStringArrayByLog(nameTag :String,arr :ArrayList<String>){
        for (item in arr){
            Log.e(nameTag,item)
        }
    }

    class DataBlock {
        var name: String = ""
        var key: String = ""
    }

}