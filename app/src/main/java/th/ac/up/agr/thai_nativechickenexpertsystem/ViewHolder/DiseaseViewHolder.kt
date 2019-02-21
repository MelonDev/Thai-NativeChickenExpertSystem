package th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.disease_list_card.view.*

class DiseaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title = itemView.diseaseNameTitle!!
    val detail = itemView.diseaseDetail!!

    val card = itemView.diseaseCard!!

}