package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.VaccineData
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.VaccineOnlyImageData
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.VaccineTextImageData
import th.ac.up.agr.thai_nativechickenexpertsystem.FullImageActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Interface.VaccineInterface
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.VaccineViewHolder

class VaccineAdapter(val array: ArrayList<VaccineInterface>) : RecyclerView.Adapter<VaccineViewHolder>() {

    private lateinit var context: Context

    companion object {
        private const val TYPE_TEXT = 0
        private const val TYPE_IMAGE = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccineViewHolder {

        context = parent.context

        /*
        val view = when(viewType){
            TYPE_TEXT -> LayoutInflater.from(parent.context).inflate(R.layout.card_view_with_image,parent,false)
            TYPE_IMAGE -> LayoutInflater.from(parent.context).inflate(R.layout.card_image,parent,false)
        }
*/
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_with_image, parent, false)

        //context = parent.context

        return VaccineViewHolder(view)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: VaccineViewHolder, position: Int) {



        val slot = array[position]

        holder.imageLayout.visibility = View.GONE
        holder.normalLayout.visibility = View.GONE

        Log.e("NAME",slot.name)
        Log.e("IMAGE",slot.image)


        if(slot.name.contentEquals("Image")){
            holder.imageLayout.visibility = View.VISIBLE
            Picasso.get().load(slot.image).into(holder.image2)

            holder.imageLayout.setOnClickListener {
                val intent = Intent(context, FullImageActivity::class.java)

                intent.putExtra("ID","VACCINE")

                intent.putExtra("NAME",slot.name)
                context.startActivity(intent)
            }


        }else if(slot.name.isNotEmpty()) {


            holder.normalLayout.visibility = View.VISIBLE


            holder.title.text = slot.name



            var des = ""
            var count = 0

            Picasso.get().load(slot.image).into(holder.image)

            slot.use.forEach {
                if (count == slot.use.size - 1) {
                    des += "- $it"
                } else {
                    des += "- $it \n"
                }
                count += 1
            }
            holder.description.text = des


        }




    }

}