package th.ac.up.agr.thai_nativechickenexpertsystem.Adapters.MenuAdapters.Horizontal

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ChickenBreedViewHolder

class HorizontalAdapter(val context: Context,val data:ArrayList<String>) : RecyclerView.Adapter<ChickenBreedViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChickenBreedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_icon,parent,false)
        return ChickenBreedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ChickenBreedViewHolder, position: Int) {

       // val a :String = RecyclerView.Adapter<ChickenBreedViewHolder>
    }
}

