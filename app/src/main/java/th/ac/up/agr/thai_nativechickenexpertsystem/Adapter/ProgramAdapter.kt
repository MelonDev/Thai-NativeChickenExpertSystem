package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.DataCard
import th.ac.up.agr.thai_nativechickenexpertsystem.ProgramDetailActivity
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.ConvertCard
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ProgramViewHolder

open class ProgramAdapter(val context: Context, var data :ArrayList<DataCard>) : RecyclerView.Adapter<ProgramViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.program_card,parent,false)
        return ProgramViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (data.size == 0){
            return 0
        } else {
            return data.size - 1
        }

    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {

        val slot = data[position]

        if (!data[position].cardID.contentEquals("0000-00-00-00-00-00")){
            if(position == -1){
                holder.addZone.visibility = View.VISIBLE
                holder.cardZone.visibility = View.GONE
            } else {
                holder.addZone.visibility = View.GONE
                holder.cardZone.visibility = View.VISIBLE

                holder.title.text = slot.cardID
                //holder.title.text = "รายการที่ ${position+1}"

                holder.systems.text = ConvertCard().getSystem(slot.systemFarm)
                holder.objective.text = ConvertCard().getObjective(slot.userObjective)
                holder.amount.text = ((slot.amountMale.toInt())+(slot.amountFemale.toInt())).toString()
                holder.dates.text = "${slot.dateDay} ${ConvertCard().getMonth(slot.dateMonth)} ${((slot.dateYear).toInt() + 543).toString()}"
            }
        }else {
            holder.addZone.visibility = View.GONE
            holder.cardZone.visibility = View.GONE
        }



        holder.cardZone.setOnClickListener {
            var arr = ArrayList<DataCard>()
            arr.add(slot)
            val intent = Intent(context, ProgramDetailActivity::class.java)
            intent.putExtra("STATUS","ACTIVE")

            intent.putExtra("CARDID",slot.cardID)




            context.startActivity(intent)

        }

    }

    private fun minusOne(pos :Int) :Int{
        return pos - 1
    }

    fun update(arr :ArrayList<DataCard>){
        data.clear()
        data = arr
    }
}