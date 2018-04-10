package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.DiseaseViewHolder

open class DiseaseAdapter(val context: Context, val data: ArrayList<String>) :RecyclerView.Adapter<DiseaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.disease_list_card,parent,false)
        return DiseaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {


    }
}