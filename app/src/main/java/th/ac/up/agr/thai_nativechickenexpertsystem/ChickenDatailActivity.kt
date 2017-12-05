package th.ac.up.agr.thai_nativechickenexpertsystem

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_chicken_datail.*
import kotlinx.android.synthetic.main.fragment_tab_first.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.ChickenDetailAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.MainMenuVerticalAdapter
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenBreedData
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.ChickenDetailData
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.DeviceUtills
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.StringArrayToArrayList

class ChickenDatailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chicken_datail)

        val bundle = intent.extras
        val title = bundle.getString("TITLE")

        detail_title_name.text = title
        detail_title_name.maxWidth = DeviceUtills(this).getScreenWidth() - (175 * 2)

        detail_back_btn.setOnClickListener { finish() }

        tabSelection("male")

        leftTab.setOnClickListener { tabSelection("male") }
        rightTab.setOnClickListener { tabSelection("female") }


    }

    private fun fakeMaleData(): ChickenDetailData {
        val data: ChickenDetailData = ChickenDetailData(
                "ไก่ชน"
                , "ไก่เหลืองหางขาว"
                , "เหลืองใหญ่พระเจ้า 5 พระองค์"
                , "ตัวผู้"
                , "น้ำตาลอ่อน"
                , "หงอนหิน"
                , "แดงสด"
                , "ขาวอมเหลืองอ่อน"
                , "ขาวอมเหลือง"
                , "แดงสด"
                , "เหลืองแก่หรือสีทองแท่น"
                , "แดง"
                , "เหลือง"
                , "หางพักสีดำปลายขาว"
                , "หางกระรวยขาวปลายดำ"
                , "เหลืองอ่อน"
                , "ขาวอมเหลือง"
                , "ขาวอมเหลือง"
                , "-"
                , "-"
                , "-"
                , "3 กก. ขึ้นไป"
                , "3.5"
                , "1.5"
                , "-"
                , "-"
                , "-"
                , R.drawable.fake_male
        )
        return data
    }

    private fun fakeFemaleData(): ChickenDetailData {
        val data: ChickenDetailData = ChickenDetailData(
                "ไก่ชน"
                , "ไก่เหลืองหางขาว"
                , "เหลืองใหญ่พระเจ้า 5 พระองค์"
                , "ตัวเมีย"
                , "น้ำตาลอ่อน"
                , "-"
                , "แดงสด"
                , "เหลือง"
                , "ขาวอมเหลืองหรือสีสีงาช้าง"
                , "แดงสด"
                , "ดำ"
                , "-"
                , "-"
                , "หางพัดสีดำ"
                , "ดำ"
                , "เหลืองอ่อน"
                , "เหลือง"
                , "ขาวอมเหลือง"
                , "-"
                , "198"
                , "136"
                , "2 กก. ขึ้นไป"
                , "3.5"
                , "1.5"
                , "-"
                , "-"
                , "-"
                , R.drawable.fake_female
        )
        return data
    }

    private fun loadingRecyclerview() {
        val handler = Handler()

        detail_loading.visibility = View.VISIBLE
        chicken_detail_recycler_view.visibility = View.GONE

        val runnable = Runnable {
            detail_loading.visibility = View.GONE
            chicken_detail_recycler_view.visibility = View.VISIBLE
        }

        var delay_time: Long = 0
        var time = 500L

        delay_time = time
        handler.postDelayed(runnable, delay_time)
    }

    private fun tabSelection(sex: String) {

        when (sex) {
            "male" -> {
                leftLine.visibility = View.VISIBLE
                rightLine.visibility = View.GONE

                loadingRecyclerview()

                val detailMaleRecyclerView = QuickRecyclerView(this
                        , chicken_detail_recycler_view
                        , "linear"
                        , 1
                        , "vertical"
                        , false
                        , "alway"
                        , "high")
                        .recyclerView()

                val data: ArrayList<ChickenDetailData> = ArrayList<ChickenDetailData>()
                data.add(fakeMaleData())

                detailMaleRecyclerView.adapter = ChickenDetailAdapter(this, data)

            }
            "female" -> {
                leftLine.visibility = View.GONE
                rightLine.visibility = View.VISIBLE

                loadingRecyclerview()

                val detailFemaleRecyclerView = QuickRecyclerView(this
                        , chicken_detail_recycler_view
                        , "linear"
                        , 1
                        , "vertical"
                        , false
                        , "alway"
                        , "high")
                        .recyclerView()

                val data: ArrayList<ChickenDetailData> = ArrayList<ChickenDetailData>()
                data.add(fakeFemaleData())

                detailFemaleRecyclerView.adapter = ChickenDetailAdapter(this, data)

            }
        }

    }

}