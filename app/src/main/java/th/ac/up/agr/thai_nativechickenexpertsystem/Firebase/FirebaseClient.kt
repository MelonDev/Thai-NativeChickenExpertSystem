package th.ac.up.agr.thai_nativechickenexpertsystem.Firebase

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.firebase.database.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenBreedData
import java.util.ArrayList

open class FirebaseClient(val context: Context) {

    private val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var childRef: DatabaseReference = databaseRef.child("ลักษณะ").child("ภายนอก").child("ลักษณะหงอน")

    var dataSet = ArrayList<DataBlock>()
    var dataKey = ArrayList<String>()

    fun update() {
        dataSet.clear()
        dataKey.clear()

        childRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                onSync(p0)
            }
        })
    }

    private fun onSync(dataSnapshot: DataSnapshot?) {
        dataSnapshot!!.children.mapNotNullTo(dataSet){
            it.getValue(DataBlock::class.java)
        }
        dataSnapshot!!.children.mapNotNullTo(dataKey){
            it.key
        }
        Log.e("Co",dataSet.size.toString())
        for (i in dataSet){
            Log.e("name",i.name)
            Log.e("key",i.key)
        }
        for (i in dataKey){
            Log.e("main key",i)
        }
    }

    class DataBlock {
        var name: String = ""
        var key: String = ""
    }


}