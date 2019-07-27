package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.VaccineData
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.VaccineViewHolder

class VaccineAdapter(val array :ArrayList<VaccineData>) :RecyclerView.Adapter<VaccineViewHolder>(){

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_with_image,parent,false)

        context = parent.context

        return VaccineViewHolder(view)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: VaccineViewHolder, position: Int) {

        val slot = array[position]
        holder.title.text = slot.name
        var des = ""
        var count = 0

        Picasso.get().load(slot.image).into(holder.image)

        slot.use.forEach {
            if(count == slot.use.size - 1){
                des += "- $it"
            }else {
                des += "- $it \n"
            }
            count+=1
        }
        holder.description.text = des

    }
}