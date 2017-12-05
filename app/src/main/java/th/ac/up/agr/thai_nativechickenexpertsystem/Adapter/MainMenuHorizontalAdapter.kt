package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import android.widget.Toast
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenBreedData
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.SubMainActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ChickenBreedViewHolder

class MainMenuHorizontalAdapter(val context: Context, val data: ArrayList<ChickenBreedData>,val po :Int) : RecyclerView.Adapter<ChickenBreedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChickenBreedViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.menu_icon, parent, false)

        return ChickenBreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChickenBreedViewHolder?, position: Int) {

        val slot: ChickenBreedData = data[position]

        holder?.mainNameTitle?.text = slot.name

        holder?.mainMenuItem?.setOnClickListener {

            if (position == 0 && po == 0){
                val intent = Intent(context, SubMainActivity::class.java)
                intent.putExtra("ID", 21)
                intent.putExtra("TITLE", slot.name)
                intent.putExtra("POSITION", position)
                context.startActivity(intent)
            } else {
                Toast.makeText(context,"จำกัดการใช้งานอยู่ครับผม :D", Toast.LENGTH_SHORT).show()
            }

        }

    }


    override fun getItemCount(): Int {
        return data.size
    }
}