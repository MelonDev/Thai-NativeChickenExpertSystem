package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.Chicken
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenDetailData
import th.ac.up.agr.thai_nativechickenexpertsystem.Firebase.FirebaseLoadDetails
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ChickenDetailViewHolder

class ChickenDetailAdapter(val context: Context,val sex :String,val arr :ArrayList<String>) : RecyclerView.Adapter<ChickenDetailViewHolder>() {

    //val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference


    override fun onBindViewHolder(holder: ChickenDetailViewHolder, position: Int) {


        val firebase = FirebaseLoadDetails(context,holder)
        //val a = firebase.getKey(firebase.getSnapshot(path))



        //firebase.loadData(path,sexThai)


        //val option = BitmapFactory.Options()
        //option.inSampleSize = 2

        firebase.loadData(arr,sex,position)





    }


    override fun getItemCount(): Int {
        return 5
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChickenDetailViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.detail_card_view, parent, false)

        return ChickenDetailViewHolder(view)
    }
}