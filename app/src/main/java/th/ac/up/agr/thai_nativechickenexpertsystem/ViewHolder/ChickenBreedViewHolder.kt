package th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.list_recyclerview.view.*
import kotlinx.android.synthetic.main.menu_icon.view.*
import kotlinx.android.synthetic.main.menu_icon_2.view.*
import kotlinx.android.synthetic.main.menu_recyclerview.view.*

class ChickenBreedViewHolder(val itemview:View) :RecyclerView.ViewHolder(itemview){

    val mainBreedTitle = itemview.mainChickenBreedTitle

    val mainNameTitle = itemview.mainChickenNameTitle

    val mainListTitle = itemview.mainListChickenNameTitle

    val listItem = itemView.main_list_item

    val gridItemTitle = itemView.subChickenNameTitle
    val mainMore = itemview.mainMore

    val gridItem = itemView.subGridItem
    val mainMenuItem = itemView.mainMenuItem

    val itemImageView = itemView.item_chicken_image
    val itemImageCardView = itemView.item_chicken_image_cardView

    val itemImageView2 = itemView.item_chicken_image2
    val itemImageCardView2 = itemView.item_chicken_image2_cardView

}