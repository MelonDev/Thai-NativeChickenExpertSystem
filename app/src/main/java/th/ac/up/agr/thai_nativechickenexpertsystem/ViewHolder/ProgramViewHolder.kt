package th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.program_card.view.*

class ProgramViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    val title = itemView.program_title_name!!
    val cardZone = itemView.program_card_zone!!
    val addZone = itemView.program_add_zone!!

}