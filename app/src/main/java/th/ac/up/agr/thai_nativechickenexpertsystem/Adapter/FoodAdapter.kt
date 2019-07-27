package th.ac.up.agr.thai_nativechickenexpertsystem.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.levitnudi.legacytableview.LegacyTableView
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.FoodData
import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.FoodViewHolder

class FoodAdapter(val arrData :ArrayList<FoodData>) :RecyclerView.Adapter<FoodViewHolder>(){

    private lateinit var con : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_table,parent,false)

        con = parent.context

        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
        //return arrData.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        //val slot = arrData[position]

        val titleRow = TableRow(con)
        val tA = TextView(con)
        tA.text = "Hello"
        val tb = TextView(con)
        tb.text = "Hello"
        titleRow.addView(tA)
        titleRow.addView(tb)


        holder.table.addView(titleRow)

    }
}