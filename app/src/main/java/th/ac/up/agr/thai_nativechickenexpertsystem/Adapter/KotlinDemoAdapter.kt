package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import th.ac.up.agr.thai_nativechickenexpertsystem.R

open class KotlinDemoAdapter : RecyclerView.Adapter<KotlinDemoAdapter.ViewHolder>() {

    private val arr: ArrayList<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.program_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arr?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //do something
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //do something
    }

}