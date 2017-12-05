package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import th.ac.up.agr.thai_nativechickenexpertsystem.ChickenDatailActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenBreedData
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.SubMainActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ChickenBreedViewHolder

class MainGridVerticalAdapter(val context: Context, val data: ArrayList<ChickenBreedData>, val ID: Int) : RecyclerView.Adapter<ChickenBreedViewHolder>() {

    override fun onBindViewHolder(holder: ChickenBreedViewHolder?, position: Int) {

        val slot = data[position]

        holder?.gridItemTitle?.text = slot.name

        holder?.gridItem?.setOnClickListener {

            if (position == 0){
                if (ID == 21) {
                    val intent = Intent(context, SubMainActivity::class.java)
                    intent.putExtra("ID", 21)
                    intent.putExtra("TITLE", slot.name)
                    intent.putExtra("POSITION", position)
                    context.startActivity(intent)
                } else if (ID == 22) {
                    val intent = Intent(context, ChickenDatailActivity::class.java)
                    //intent.putExtra("ID", 22)
                    intent.putExtra("TITLE", slot.name)
                    //intent.putExtra("POSITION", position)
                    context.startActivity(intent)
                }

            }else {
                Toast.makeText(context,"จำกัดการใช้งานอยู่ครับผม :D", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChickenBreedViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.menu_icon_2, parent, false)


        return ChickenBreedViewHolder(view)
    }
}