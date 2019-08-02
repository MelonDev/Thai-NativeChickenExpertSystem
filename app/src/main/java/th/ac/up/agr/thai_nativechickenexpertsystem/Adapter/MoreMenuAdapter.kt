package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import th.ac.up.agr.thai_nativechickenexpertsystem.ContainerActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.IconSet
import th.ac.up.agr.thai_nativechickenexpertsystem.Fragment.VaccineFragment
import th.ac.up.agr.thai_nativechickenexpertsystem.FullImageActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.SubMainActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.MoreMenuViewHolder

class MoreMenuAdapter(val context: Context, val data: ArrayList<IconSet>) : RecyclerView.Adapter<MoreMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_icon_3, parent, false)

        return MoreMenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MoreMenuViewHolder, position: Int) {

        val slot = data[position]

        holder.textView.text = slot.name
        //Picasso.get().load(slot.image).into(holder.imageView)
        //Picasso.get().load(R.drawable.ic_feather_on).placeholder(R.drawable.ic_feather_on).into(holder.imageView)
        //Picasso.get().load(R.drawable.ic_feather_on).noFade().into(holder.imageView)


        //val option = BitmapFactory.Options()
        //option.inSampleSize =
        //val bitmap = BitmapFactory.decodeResource(context.resources, slot.image)
        //holder.imageView.setImageBitmap(bitmap)

        Glide.with(context).load(slot.image).into(holder.imageView)

        Log.e("TAGSS",slot.activity.toString())



        holder.imageView.setOnClickListener {

            if(position == 1 || position == 3){
                val intent = Intent(context, FullImageActivity::class.java)

                intent.putExtra("ID","MORE")
                intent.putExtra("NAME",slot.name)
                //intent.putExtra("POSITION",position)
                context.startActivity(intent)
            }else {

                var intent: Intent = Intent()
                intent = Intent(context, ContainerActivity::class.java)

                intent.putExtra("ID", "MENU")
                intent.putExtra("NAME", slot.name)
                intent.putExtra("POSITION", position)
                context.startActivity(intent)
            }
        }

    }
}