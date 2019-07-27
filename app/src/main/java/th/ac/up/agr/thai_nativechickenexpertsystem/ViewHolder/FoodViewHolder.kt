package th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_view_table.view.*

class FoodViewHolder(itemView :View) :RecyclerView.ViewHolder(itemView){

    val table = itemView.food_legacy_table_view
    val title = itemView.table_card_view_title_text
    val a = itemView.food_detall_a
    val b = itemView.food_detall_b
    val c = itemView.food_detall_c
    val d = itemView.food_detall_d
    val e = itemView.food_detall_e

}