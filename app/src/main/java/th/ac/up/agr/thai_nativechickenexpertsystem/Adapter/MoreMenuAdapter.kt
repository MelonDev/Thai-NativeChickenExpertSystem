package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.IconSet
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.MoreMenuViewHolder

class MoreMenuAdapter(val context: Context,val data :ArrayList<IconSet>) : RecyclerView.Adapter<MoreMenuViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_icon_3,parent,false)

        return MoreMenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MoreMenuViewHolder, position: Int) {

        

    }
}