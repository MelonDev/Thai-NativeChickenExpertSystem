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
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.R.id.main_menu_horizontal_recycler_view
import th.ac.up.agr.thai_nativechickenexpertsystem.SubMainActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.StringArrayToArrayList
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ChickenBreedViewHolder

class MainMenuVerticalAdapter (val context: Context,val data :ArrayList<ChickenBreedData>,val ID:Int) : RecyclerView.Adapter<ChickenBreedViewHolder>() {

    var views = View(context)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChickenBreedViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.menu_recyclerview,parent,false)

        views = view

        return ChickenBreedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ChickenBreedViewHolder?, position: Int) {

        val slot : ChickenBreedData = data[position]

        holder?.mainBreedTitle?.text = slot.name

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

        val data :ArrayList<ChickenBreedData> = setData(position)

        menu_recyclerView.adapter = MainMenuHorizontalAdapter(context,data,position)

    }

    private fun setData(position: Int) : ArrayList<ChickenBreedData>{

        return when (position){
            0 -> {StringArrayToArrayList(context).mainChicken(R.array.A1)}
            1 -> {StringArrayToArrayList(context).mainChicken(R.array.B1) }
            else -> {StringArrayToArrayList(context).mainChicken(R.array.A1) }
        }
    }
}