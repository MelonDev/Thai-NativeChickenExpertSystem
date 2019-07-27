package th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_view_with_image.view.*

class VaccineViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    val title = itemView.image_card_view_title_text
    val description = itemView.image_card_view_des_text
    val image = itemView.image_card_view_image
}