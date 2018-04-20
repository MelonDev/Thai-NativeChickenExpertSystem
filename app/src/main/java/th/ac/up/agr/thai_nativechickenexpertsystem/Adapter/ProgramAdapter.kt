package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ProgramViewHolder

open class ProgramAdapter(val context: Context, val data :ArrayList<String>) :RecyclerView.Adapter<ProgramViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.program_card,parent,false)
        return ProgramViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {

        if(position == -1){
            holder.addZone.visibility = View.VISIBLE
            holder.cardZone.visibility = View.GONE
        } else {
            holder.addZone.visibility = View.GONE
            holder.cardZone.visibility = View.VISIBLE

            holder.title.text = "รายการที่ ${position+1}"
        }

    }

    private fun minusOne(pos :Int) :Int{
        return pos - 1
    }
}