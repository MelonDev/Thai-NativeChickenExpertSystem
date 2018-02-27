package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import th.ac.up.agr.thai_nativechickenexpertsystem.ChickenDatailActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenBreedData
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.SubMainActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.Path
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ChickenBreedViewHolder

class MainGridVerticalAdapter(val context: Context, val data: ArrayList<String>,val ID :Int,var path :String) : RecyclerView.Adapter<ChickenBreedViewHolder>() {

    override fun onBindViewHolder(holder: ChickenBreedViewHolder?, position: Int) {

        holder?.gridItemTitle?.text = data[position]

        holder?.gridItem?.setOnClickListener {

            when (ID) {
                4 -> {
                    val intent = Intent(context, SubMainActivity::class.java)
                    intent.putExtra("ID", 2)
                    intent.putExtra("TITLE", data[position])
                    intent.putExtra("PATH", Path().toPath(path,data[position]))
                    context.startActivity(intent)
                }
                5 -> {
                    val intent = Intent(context, ChickenDatailActivity::class.java)
                    intent.putExtra("ID", 2)
                    intent.putExtra("TITLE", data[position])
                    intent.putExtra("PATH", Path().toPath(path,data[position]))
                    context.startActivity(intent)
                }
                6 -> {
                    val intent = Intent(context, ChickenDatailActivity::class.java)
                    intent.putExtra("ID", 2)
                    intent.putExtra("TITLE", data[position])
                    intent.putExtra("PATH", Path().toPath(path,data[position]))
                    context.startActivity(intent)
                }
            }

        }

/*
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
*/
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChickenBreedViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.menu_icon_2, parent, false)


        return ChickenBreedViewHolder(view)
    }
}