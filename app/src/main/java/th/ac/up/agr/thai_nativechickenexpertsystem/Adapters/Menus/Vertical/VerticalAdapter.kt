package th.ac.up.agr.thai_nativechickenexpertsystem.Adapters.MenuAdapters.Vertical

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapters.Tools.OnFirebaseSync
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ChickenBreedViewHolder

class VerticalAdapter(val context:Context,val data: ArrayList<String>) : RecyclerView.Adapter<ChickenBreedViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChickenBreedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_recyclerview,parent,false)
        return ChickenBreedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ChickenBreedViewHolder, position: Int) {

        //OnSync.Key(this,)

        //val firebase = OnFirebaseSync.Vertical(this)
        //val s = OnFirebaseSync.A(this).key()
        //firebase.key(this)


    }
}