package th.ac.up.agr.thai_nativechickenexpertsystem

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_sub_main.*
import kotlinx.android.synthetic.main.fragment_tab_first.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.MainGridVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.MainListVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenBreedData
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.DeviceUtills
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.StringArrayToArrayList

class SubMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_main)

        val bundle = intent.extras
        val ID = bundle.getInt("ID")
        val title = bundle.getString("TITLE")

        sub_title_name.text = title
        sub_title_name.maxWidth = DeviceUtills(this).getScreenWidth() - (175 * 2)


        sub_back_btn.setOnClickListener { finish() }

        when(ID){
            11 -> {
                val data: ArrayList<ChickenBreedData> = StringArrayToArrayList(this).mainChicken(R.array.A1)

                recyclerview("LINEAR",data,ID)

            }
            12 -> {
                val data: ArrayList<ChickenBreedData> = StringArrayToArrayList(this).mainChicken(R.array.A2)

                recyclerview("LINEAR",data,ID)
            }
            20 -> {
                val data: ArrayList<ChickenBreedData> = StringArrayToArrayList(this).mainChicken(R.array.A1)

                recyclerview("GRID",data,ID)

            }
            21 -> {
                val data: ArrayList<ChickenBreedData> = StringArrayToArrayList(this).mainChicken(R.array.A2)

                recyclerview("GRID",data,ID)
            }
        }

    }

    private fun recyclerview(orientation: String,data :ArrayList<ChickenBreedData>,ID :Int){

        if (orientation.contentEquals("LINEAR")){

            val listRecyclerView = QuickRecyclerView(this
                    , sub_list_vertical_recycler_view
                    , "linear"
                    , 1
                    , "vertical"
                    , false
                    , "never"
                    , "high")
                    .recyclerView()

            if (ID == 11) {
                listRecyclerView.adapter = MainListVerticalAdapter(this,data,11)
            } else {
                listRecyclerView.adapter = MainListVerticalAdapter(this,data,12)

            }


            sub_list_vertical_recycler_view.visibility = View.VISIBLE
            sub_menu_vertical_recycler_view.visibility = View.GONE

        } else if (orientation.contentEquals("GRID")){

            val listRecyclerView = QuickRecyclerView(this
                    , sub_menu_vertical_recycler_view
                    , "grid"
                    , 3
                    , "vertical"
                    , false
                    , "never"
                    , "high")
                    .recyclerView()


            if (ID == 20) {
                listRecyclerView.adapter = MainGridVerticalAdapter(this,data,21)

            }else {
                listRecyclerView.adapter = MainGridVerticalAdapter(this,data,22)

            }

            sub_list_vertical_recycler_view.visibility = View.GONE
            sub_menu_vertical_recycler_view.visibility = View.VISIBLE
        }

    }

}
