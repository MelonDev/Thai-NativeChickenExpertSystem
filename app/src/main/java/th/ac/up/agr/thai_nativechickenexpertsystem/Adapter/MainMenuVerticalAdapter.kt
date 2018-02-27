package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_tab_first.view.*
import kotlinx.android.synthetic.main.menu_recyclerview.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenBreedData
import th.ac.up.agr.thai_nativechickenexpertsystem.Firebase.FirebaseChicken
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.R.id.main_menu_horizontal_recycler_view
import th.ac.up.agr.thai_nativechickenexpertsystem.SubMainActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.Animation
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.Path
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.StringArrayToArrayList
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ChickenBreedViewHolder

class MainMenuVerticalAdapter(val context: Context, val data: ArrayList<String>) : RecyclerView.Adapter<ChickenBreedViewHolder>() {

    var views = View(context)
    lateinit var firebase : FirebaseChicken

    var path = String()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChickenBreedViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.menu_recyclerview, parent, false)

        views = view

        return ChickenBreedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ChickenBreedViewHolder?, position: Int) {

        holder?.mainBreedTitle?.text = data[position]

        Animation().animation(holder?.itemView!!)

        firebase = FirebaseChicken(context)

        val menuRecyclerView = QuickRecyclerView(context
                , views.main_menu_horizontal_recycler_view
                , "linear"
                , 1
                , "horizontal"
                , false
                , "never"
                , "high")
                .recyclerView()

        menuRecyclerView.adapter = MainMenuHorizontalAdapter(context, ArrayList<String>(),data[position],"")
        firebase.mainLoadChicken(firebase.databaseReference.child("พันธุ์ไก่").child(data[position]),menuRecyclerView,2,data[position],path)


        if (data[position].contentEquals("ไก่ชน")){
            holder?.mainMore?.setOnClickListener {
                val intent = Intent(context, SubMainActivity::class.java)
                intent.putExtra("ID", 0)
                intent.putExtra("TITLE", data[position])
                intent.putExtra("PATH", Path().toPath(path,data[position]))
                context.startActivity(intent)
            }
        } else {
            holder?.mainMore?.setOnClickListener {
                val intent = Intent(context, SubMainActivity::class.java)
                intent.putExtra("ID", 1)
                intent.putExtra("TITLE", data[position])
                intent.putExtra("PATH", Path().toPath(path,data[position]))
                context.startActivity(intent)
            }
        }



        //val data :ArrayList<ChickenBreedData> = setData(position)
        //menu_recyclerView.adapter = MainMenuHorizontalAdapter(context,data,position)


        //val slot : ChickenBreedData = data[position]

        //holder?.mainBreedTitle?.text = slot.name
/*
        holder?.mainMore?.setOnClickListener {

           if (position == 0){
               if(ID == 20) {
                   val intent = Intent(context, SubMainActivity::class.java)
                   intent.putExtra("ID", 20)
                   intent.putExtra("TITLE", slot.name)
                   intent.putExtra("POSITION",position)
                   context.startActivity(intent)
               }
           }else {
               Toast.makeText(context,"จำกัดการใช้งานอยู่ครับผม :D", Toast.LENGTH_SHORT).show()
           }

        }


        val menu_recyclerView = QuickRecyclerView(context
                , views.main_menu_horizontal_recycler_view
                , "linear"
                , 1
                , "horizontal"
                , false
                , "never"
                , "high")
                .recyclerView()

        //val data :ArrayList<ChickenBreedData> = setData(position)

        //menu_recyclerView.adapter = MainMenuHorizontalAdapter(context,data,position)
*/
    }

}