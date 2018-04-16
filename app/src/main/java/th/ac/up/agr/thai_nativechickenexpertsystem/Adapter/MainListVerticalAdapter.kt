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
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.Path
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ChickenBreedViewHolder

class MainListVerticalAdapter (val context: Context,val data :ArrayList<String>,val mainTitle:String,val finish :Boolean,val path :String) : RecyclerView.Adapter<ChickenBreedViewHolder>() {

    override fun onBindViewHolder(holder: ChickenBreedViewHolder, position: Int) {

        holder.mainListTitle?.text = data[position]


        if (data[position].contentEquals("ไก่ชน")){
            holder.listItem?.setOnClickListener {
                val intent = Intent(context, SubMainActivity::class.java)
                intent.putExtra("ID", 10)
                intent.putExtra("TITLE", data[position])
                intent.putExtra("PATH", Path().toPath(path,data[position]))
                context.startActivity(intent)
            }
        } else if (mainTitle.contentEquals("ไก่ชน")) {
            holder.listItem?.setOnClickListener {
                val intent = Intent(context, SubMainActivity::class.java)
                intent.putExtra("ID", 11)
                intent.putExtra("TITLE", data[position])
                intent.putExtra("PATH", Path().toPath(path,data[position]))
                context.startActivity(intent)
            }
        } else if (!finish) {
            holder.listItem?.setOnClickListener {
                val intent = Intent(context, SubMainActivity::class.java)
                intent.putExtra("ID", 12)
                intent.putExtra("TITLE", data[position])
                intent.putExtra("PATH", Path().toPath(path,data[position]))
                context.startActivity(intent)
            }
        } else if (finish) {
            holder.listItem?.setOnClickListener {
                val intent = Intent(context, ChickenDatailActivity::class.java)
                intent.putExtra("ID", 12)
                intent.putExtra("TITLE", data[position])
                intent.putExtra("PATH", Path().toPath(path, data[position]))
                context.startActivity(intent)
            }

        }


/*
        val slot = data[position]

        holder?.mainListTitle?.text = slot.name

        holder?.listItem?.setOnClickListener {

           if (position == 0){
               if(ID == 10) {
                   val intent = Intent(context, SubMainActivity::class.java)
                   intent.putExtra("ID", 11)
                   intent.putExtra("TITLE", slot.name)
                   intent.putExtra("POSITION",position)
                   context.startActivity(intent)
               } else if (ID == 11) {
                   val intent = Intent(context, SubMainActivity::class.java)
                   intent.putExtra("ID", 12)
                   intent.putExtra("TITLE", slot.name)
                   intent.putExtra("POSITION",position)
                   context.startActivity(intent)
               } else if (ID == 12) {
                   val intent = Intent(context, ChickenDatailActivity::class.java)
                   intent.putExtra("TITLE", slot.name)
                   context.startActivity(intent)
               }
           } else {
               Toast.makeText(context,"จำกัดการใช้งานอยู่ครับผม :D", Toast.LENGTH_SHORT).show()

           }


        }
*/
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChickenBreedViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_recyclerview,parent,false)


        return ChickenBreedViewHolder(view)
    }
}