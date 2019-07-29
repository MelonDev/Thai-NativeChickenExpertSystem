package th.ac.up.agr.thai_nativechickenexpertsystem

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

import kotlinx.android.synthetic.main.activity_sub_main.*
import kotlinx.android.synthetic.main.fragment_tab_first.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.MainGridVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.MainListVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenBreedData
import th.ac.up.agr.thai_nativechickenexpertsystem.Firebase.FirebaseChicken
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.DeviceUtills
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.StringArrayToArrayList

class SubMainActivity : AppCompatActivity() {

    lateinit var firebase: FirebaseChicken

    private var data: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_main)

        val bundle = intent.extras
        val ID = bundle.getInt("ID")
        val title = bundle.getString("TITLE")
        val path = bundle.getString("PATH")

        firebase = FirebaseChicken(this)

        sub_back_btn.setOnClickListener { finish() }

        sub_title_name.text = title
        sub_title_name.maxWidth = DeviceUtills(this).getScreenWidth() - (175 * 2)

        val w = window

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            w.navigationBarColor = resources.getColor(R.color.colorBNVT)
        }
        else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            w.navigationBarColor = resources.getColor(R.color.colorBNV)
        }

        //Toast.makeText(this,ID.toString(),Toast.LENGTH_SHORT).show()
        Toast.makeText(this,path.toString(),Toast.LENGTH_SHORT).show()


        when (ID) {
            0 -> {

                sub_list_vertical_recycler_view.visibility = View.GONE
                sub_menu_vertical_recycler_view.visibility = View.VISIBLE

                val listRecyclerView = QuickRecyclerView(this
                        ,
                        sub_menu_vertical_recycler_view
                        ,
                        "grid"
                        ,
                        3
                        ,
                        "vertical"
                        ,
                        false
                        ,
                        "never"
                        ,
                        "high")
                        .recyclerView()

                listRecyclerView.adapter = MainGridVerticalAdapter(this, ArrayList<String>(), 4, "", "'")
                firebase.mainLoadChicken(firebase.databaseReference.child("พันธุ์ไก่").child(title), listRecyclerView, 4, title, path)

            }
            1 -> {
                sub_list_vertical_recycler_view.visibility = View.GONE
                sub_menu_vertical_recycler_view.visibility = View.VISIBLE
                val listRecyclerView = QuickRecyclerView(this
                        ,
                        sub_menu_vertical_recycler_view
                        ,
                        "grid"
                        ,
                        3
                        ,
                        "vertical"
                        ,
                        false
                        ,
                        "never"
                        ,
                        "high")
                        .recyclerView()

                listRecyclerView.adapter = MainGridVerticalAdapter(this, ArrayList<String>(), 5, "", "")
                firebase.mainLoadChicken(firebase.databaseReference.child("พันธุ์ไก่").child(title), listRecyclerView, 5, title, path)
            }
            2 -> {

                //Log.e("PA",path)
                sub_list_vertical_recycler_view.visibility = View.GONE
                sub_menu_vertical_recycler_view.visibility = View.VISIBLE

                //Toast.makeText(this,"ID 1",Toast.LENGTH_SHORT).show()
                val listRecyclerView = QuickRecyclerView(this
                        ,
                        sub_menu_vertical_recycler_view
                        ,
                        "grid"
                        ,
                        3
                        ,
                        "vertical"
                        ,
                        false
                        ,
                        "never"
                        ,
                        "high")
                        .recyclerView()

                listRecyclerView.adapter = MainGridVerticalAdapter(this, ArrayList<String>(), 6, "", "")
                firebase.mainLoadChicken(firebase.databaseReference.child("พันธุ์ไก่").child("ไก่ชน").child(title), listRecyclerView, 6, title, path)
            }
            10 -> {
                sub_list_vertical_recycler_view.visibility = View.VISIBLE
                sub_menu_vertical_recycler_view.visibility = View.GONE

                val listRecyclerView = QuickRecyclerView(this
                        ,
                        sub_list_vertical_recycler_view
                        ,
                        "linear"
                        ,
                        1
                        ,
                        "vertical"
                        ,
                        false
                        ,
                        "never"
                        ,
                        "high")
                        .recyclerView()

                listRecyclerView.adapter = MainListVerticalAdapter(this, ArrayList<String>(), title, false, "")
                firebase.mainLoadChicken(firebase.databaseReference.child("พันธุ์ไก่").child(title), listRecyclerView, 11, title, path)

            }
            11 -> {
                sub_list_vertical_recycler_view.visibility = View.VISIBLE
                sub_menu_vertical_recycler_view.visibility = View.GONE

                val listRecyclerView = QuickRecyclerView(this
                        ,
                        sub_list_vertical_recycler_view
                        ,
                        "linear"
                        ,
                        1
                        ,
                        "vertical"
                        ,
                        false
                        ,
                        "never"
                        ,
                        "high")
                        .recyclerView()


                listRecyclerView.adapter = MainListVerticalAdapter(this, ArrayList<String>(), title, false, "")
                firebase.mainLoadChicken(firebase.databaseReference.child("พันธุ์ไก่").child("ไก่ชน").child(title), listRecyclerView, 20, title, path)

            }
            12 -> {
                sub_list_vertical_recycler_view.visibility = View.VISIBLE
                sub_menu_vertical_recycler_view.visibility = View.GONE

                val listRecyclerView = QuickRecyclerView(this
                        ,
                        sub_list_vertical_recycler_view
                        ,
                        "linear"
                        ,
                        1
                        ,
                        "vertical"
                        ,
                        false
                        ,
                        "never"
                        ,
                        "high")
                        .recyclerView()


                listRecyclerView.adapter = MainListVerticalAdapter(this, ArrayList<String>(), title, false, "")
                firebase.mainLoadChicken(firebase.databaseReference.child("พันธุ์ไก่").child(title), listRecyclerView, 21, title, path)

            }
            100 -> {
                abcMode(path)

            }

/*
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
*/
        }

/*
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
*/
    }

    private fun abcMode(path :String) {
        val s = firebase.databaseReference.child("พันธุ์ไก่")
        s.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                onSyncKeyOther(p0)

                sub_list_vertical_recycler_view.visibility = View.GONE
                sub_menu_vertical_recycler_view.visibility = View.VISIBLE

                //Toast.makeText(this,"ID 1",Toast.LENGTH_SHORT).show()
                val listRecyclerView = QuickRecyclerView(applicationContext
                        ,
                        sub_menu_vertical_recycler_view
                        ,
                        "grid"
                        ,
                        3
                        ,
                        "vertical"
                        ,
                        false
                        ,
                        "never"
                        ,
                        "high")
                        .recyclerView()

                listRecyclerView.adapter = MainGridVerticalAdapter(applicationContext, ArrayList<String>(), 100, "", "")
                firebase.mainLoadChicken(firebase.databaseReference.child("พันธุ์ไก่"), listRecyclerView, 101, "พันธุ์อื่นๆ", "")
            }
        })
    }

    private fun onSyncKeyOther(dataSnapshot: DataSnapshot?) {
        val a = ArrayList<String>()
        //val b = ArrayList<String>()

        dataSnapshot!!.children.mapNotNullTo(a) {
            it.key
        }

        a.filter {
            (!it.contentEquals("ไก่ชน")
                    and !it.contentEquals("ไก่แจ้")
                    and !it.contentEquals("ไก่ดำ")
                    and !it.contentEquals("ไก่พม่า"))
        }
                .forEach { data.add(it) }

    }

    private fun recyclerview(orientation: String, data: ArrayList<ChickenBreedData>, ID: Int) {

        /*

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
*/
    }

}
