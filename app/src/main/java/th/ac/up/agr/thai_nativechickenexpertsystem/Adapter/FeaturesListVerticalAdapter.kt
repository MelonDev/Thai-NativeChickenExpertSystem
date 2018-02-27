package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import at.wirecube.additiveanimations.additive_animator.AdditiveAnimator
import th.ac.up.agr.thai_nativechickenexpertsystem.ChickenDatailActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenBreedData
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.SubMainActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.Animation
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.DeviceUtills
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ChickenBreedViewHolder

class FeaturesListVerticalAdapter(val context: Context, val data: ArrayList<String>) : RecyclerView.Adapter<ChickenBreedViewHolder>() {

    override fun onBindViewHolder(holder: ChickenBreedViewHolder?, position: Int) {


        val slot = data[position]
        holder?.mainListTitle?.text = slot

        Animation().animation(holder?.itemView!!)

        holder?.listItem?.setOnClickListener {


        }


    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChickenBreedViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.new_list_recyclerview, parent, false)


        return ChickenBreedViewHolder(view)
    }
}