package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.FarmData
import th.ac.up.agr.thai_nativechickenexpertsystem.FullImageActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.FarmManagerViewHolder
import java.util.zip.Inflater

class FarmManagerAdapter(val arrData: ArrayList<FarmData>) : RecyclerView.Adapter<FarmManagerViewHolder>() {

    private lateinit var con: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmManagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.disease_layout, parent, false)

        con = parent.context

        return FarmManagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrData.size
    }

    override fun onBindViewHolder(holder: FarmManagerViewHolder, position: Int) {
        val slot = arrData[position]
        holder.title.text = slot.name

        holder.layout.setOnClickListener {
            val intent = Intent(con, FullImageActivity::class.java)

                    intent.putExtra("ID","FARM")
            intent.putExtra("NAME",slot.name)
            //intent.putExtra("POSITION",position)
            con.startActivity(intent)
        }
    }
}