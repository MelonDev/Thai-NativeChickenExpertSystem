package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.DiseaseData
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.DiseaseViewHolder

open class DiseaseAdapter(val context: Context, val data: ArrayList<DiseaseData>) : RecyclerView.Adapter<DiseaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.disease_list_card,parent,false)
        return DiseaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {

        //val slot = data[position]
        //holder.title.text = slot.name

    }
}