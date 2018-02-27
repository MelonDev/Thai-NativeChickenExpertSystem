package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenDetailData
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ChickenDetailViewHolder

class ChickenDetailAdapter(val context: Context, val data: ArrayList<ChickenDetailData>) : RecyclerView.Adapter<ChickenDetailViewHolder>() {

    override fun onBindViewHolder(holder: ChickenDetailViewHolder?, position: Int) {
/*
        val slot :ChickenDetailData = data[0]

        val option = BitmapFactory.Options()
        option.inSampleSize = 2


        when (position) {
            0 -> {
                allGone(holder)
                holder?.detailA?.visibility = View.VISIBLE

                holder?.A1?.text = slot.secondSubSpecies
            }
            1 -> {
                allGone(holder)
                holder?.detailB?.visibility = View.VISIBLE

                val bitmap = BitmapFactory.decodeResource(context.resources, slot.image, option)
                holder?.B1Image?.setImageBitmap(bitmap)
            }
            2 -> {
                allGone(holder)
                holder?.detailC?.visibility = View.VISIBLE

                holder?.C1?.text = slot.eggShellColor
            }
            3 -> {
                allGone(holder)
                holder?.detailD?.visibility = View.VISIBLE

                holder?.D1?.text = slot.crestStyle
                holder?.D2?.text = slot.faceColor
                holder?.D3?.text = slot.eyeColor
                holder?.D4?.text = slot.mouthColor
                holder?.D5?.text = slot.earringColor
                holder?.D6?.text = slot.necklaceColor
                holder?.D7?.text = slot.wattleColor
                holder?.D8?.text = slot.spurColor
                holder?.D9?.text = slot.tailStyleAndColor
                holder?.D10?.text = slot.bodyFurColor
                holder?.D11?.text = slot.skinColor
                holder?.D12?.text = slot.shinColor
                holder?.D13?.text = slot.nailColor
            }
            4 -> {
                allGone(holder)
                holder?.detailE?.visibility = View.VISIBLE

                holder?.E1?.text = slot.duration
                holder?.E2?.text = slot.ageOfGiveFirstEgg
                holder?.E3?.text = slot.productivityPerYear
                holder?.E4?.text = slot.weight
                holder?.E5?.text = slot.exchangeRateToFood
                holder?.E6?.text = slot.mortalityRate
                holder?.E7?.text = slot.weightForSold
                holder?.E8?.text = slot.birthWeight
            }
            5 -> {
                allGone(holder)
                holder?.detailF?.visibility = View.VISIBLE

                holder?.F1?.text = slot.weightOfGiveFirstEgg
            }
        }
*/
    }

    private fun allGone(holder :ChickenDetailViewHolder?){
        holder?.detailA?.visibility = View.GONE
        holder?.detailB?.visibility = View.GONE
        holder?.detailC?.visibility = View.GONE
        holder?.detailD?.visibility = View.GONE
        holder?.detailE?.visibility = View.GONE
        holder?.detailF?.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChickenDetailViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.detail_card_view,parent,false)

        return  ChickenDetailViewHolder(view)
    }
}