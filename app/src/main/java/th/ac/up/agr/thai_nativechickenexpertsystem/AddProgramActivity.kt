package th.ac.up.agr.thai_nativechickenexpertsystem

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_add_program.*
import java.util.Arrays.asList

import java.util.*
import kotlin.collections.ArrayList
import android.view.Gravity
import android.R.attr.gravity
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import com.aigestudio.wheelpicker.WheelPicker
import com.aigestudio.wheelpicker.widgets.WheelDayPicker
import com.aigestudio.wheelpicker.widgets.WheelMonthPicker
import com.aigestudio.wheelpicker.widgets.WheelYearPicker
import th.ac.up.agr.thai_nativechickenexpertsystem.Data.DataCard
import th.ac.up.agr.thai_nativechickenexpertsystem.Firebase.FirebaseProgram
import java.text.SimpleDateFormat
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_program_plus.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.ConvertCard


class AddProgramActivity : AppCompatActivity(),WheelPicker.OnItemSelectedListener {

    private lateinit var yearWheel :WheelYearPicker
    private lateinit var monthWheel :WheelMonthPicker
    private lateinit var dayWheel :WheelDayPicker

    private var amountMale :String = "0"
    private var amountFemale :String = "0"
    private var ageDay :String = "0"
    private var ageWeek :String = "0"

    private var monthS :String = "0"
    private var yearS :String = "0"

    private lateinit var ID :String

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val userID = "melondev_icloud_com"
    private lateinit var cardID :String
    private lateinit var dataCard: DataCard

    private var systemTab = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_program)


        yearWheel = add_program_year_wheel
        monthWheel = add_program_month_wheel
        dayWheel = add_program_day_wheel

        yearWheel.yearEnd = 2100
        yearWheel.yearStart = 1900

        monthS = monthWheel.selectedMonth.toString()
        yearS = yearWheel.selectedYear.toString()

        ID = intent.extras.getString("ID")

        if (ID.contentEquals("1")){
            add_program_title_name.text = "แก้ไขรายการข้อมูล"
            cardID = intent.extras.getString("CARDID")

            val ref = databaseReference.child("ผู้ใช้").child(userID).child("ข้อมูล").child("ACTIVE").child(cardID)

            ref.addValueEventListener(object :ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot?) {
                    dataCard = p0!!.getValue(DataCard::class.java)!!

                    setData()
                }
            })

        }

        add_program_system_tab.apply {
            addTab(this.newTab().setText("กรงตับ"))
            addTab(this.newTab().setText("ขังคอก"))
            addTab(this.newTab().setText("ปล่อยอิสระ"))
            addTab(this.newTab().setText("อินทรีย์"))
        }



        var a = ArrayList<Int>()
        var i = 0

        while (a.size <= 999){
            a.add(i)
            i += 1
        }

        var b = ArrayList<Int>()
        var j = 2530

        while (j <= 2590){
            b.add(j)
            j += 1
        }



        add_program_back_btn.setOnClickListener { finish() }

        add_program_old_day_wheel.data = a
        add_program_old_week_wheel.data = a

        add_program_count_male_wheel.data = a
        add_program_count_female_wheel.data = a

        add_program_month_wheel_s.data = ConvertCard().getArrMonth()
        add_program_year_wheel_s.data = b

        add_program_month_wheel_s.selectedItemPosition = monthWheel.selectedMonth - 1
        add_program_year_wheel_s.selectedItemPosition = yearWheel.selectedYear - 1987

        resetCount.setOnClickListener {
            add_program_count_male_wheel.selectedItemPosition = 0
            add_program_count_female_wheel.selectedItemPosition = 0
        }

        resetDate.setOnClickListener {
            updateDay(monthWheel.selectedMonth,yearWheel.selectedYear)
            monthWheel.selectedMonth = monthWheel.selectedMonth
            yearWheel.selectedYear = yearWheel.selectedYear
            dayWheel.selectedDay = dayWheel.selectedDay
            add_program_month_wheel_s.selectedItemPosition = monthWheel.selectedMonth - 1
            add_program_year_wheel_s.selectedItemPosition = yearWheel.selectedYear - 1987
            monthS = monthWheel.selectedMonth.toString()
            yearS = yearWheel.selectedYear.toString()
        }

        add_program_old_day_wheel.setOnItemSelectedListener(this)
        add_program_old_week_wheel.setOnItemSelectedListener(this)
        add_program_count_male_wheel.setOnItemSelectedListener(this)
        add_program_count_female_wheel.setOnItemSelectedListener(this)
        add_program_month_wheel_s.setOnItemSelectedListener(this)
        add_program_year_wheel_s.setOnItemSelectedListener(this)

        add_program_system_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val s = 1+1
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                systemTab = add_program_system_tab.selectedTabPosition.toString()
            }
        })

        updateDay(monthWheel.currentMonth,yearWheel.currentYear)


        yearWheel.setOnItemSelectedListener { picker, data, position ->
            //updateDay(monthWheel.currentMonth,yearWheel.currentYear)
            updateDay(monthS.toInt(),yearWheel.currentYear)
        }

        monthWheel.setOnItemSelectedListener { picker, data, position ->
            updateDay(monthWheel.currentMonth,yearWheel.currentYear)
        }

        add_program_save_btn.setOnClickListener {
            saveData()
        }

    }

    private fun setData(){
        add_program_objective_radio.selectedTab = dataCard.userObjective.toInt()
        add_program_count_male_wheel.selectedItemPosition = dataCard.amountMale.toInt()
        add_program_count_female_wheel.selectedItemPosition = dataCard.amountFemale.toInt()
        add_program_system_tab.setScrollPosition(dataCard.systemFarm.toInt(),0f,true)
        add_program_old_day_wheel.selectedItemPosition = dataCard.ageDay.toInt()
        add_program_old_week_wheel.selectedItemPosition = dataCard.ageWeek.toInt()
        yearWheel.selectedItemPosition = dataCard.dateYear.toInt() - 1900
        monthWheel.selectedItemPosition = dataCard.dateMonth.toInt() - 1
        dayWheel.setYearAndMonth(dataCard.dateYear.toInt(),dataCard.dateMonth.toInt())
        dayWheel.selectedItemPosition = dataCard.dateDay.toInt() - 1

        add_program_month_wheel_s.selectedItemPosition = dataCard.dateMonth.toInt() -1
        add_program_year_wheel_s.selectedItemPosition = dataCard.dateYear.toInt() - 1987

        amountMale = dataCard.amountMale
        amountFemale = dataCard.amountFemale
        ageDay = dataCard.ageDay
        ageWeek = dataCard.ageWeek

        monthS = dataCard.dateMonth
        yearS = dataCard.dateYear

        systemTab = dataCard.systemFarm



    }


    override fun onItemSelected(picker: WheelPicker, data: Any, position: Int) {
        when (picker.id) {
            R.id.add_program_old_day_wheel -> ageDay = data.toString()
            R.id.add_program_old_week_wheel -> ageWeek = data.toString()
            R.id.add_program_count_male_wheel -> amountMale = data.toString()
            R.id.add_program_count_female_wheel -> amountFemale = data.toString()
            R.id.add_program_month_wheel_s -> {
                monthS = (position+1).toString()
                updateDay(monthS.toInt(),yearS.toInt())
            }
            R.id.add_program_year_wheel_s -> {
                yearS = (data.toString().toInt() - 543).toString()
                //Log.e("YEAR",yearS)
                updateDay(monthS.toInt(),yearS.toInt())

            }
        }
    }

    private fun saveData(){

        val a = Calendar.getInstance().time
        val df = SimpleDateFormat("yyyy-MM-dd-kk-mm-ss")
        val formattedDate = df.format(a)

        val data = DataCard()

        if (ID.contentEquals("1")){
            data.cardID = cardID
        } else {
            data.cardID = formattedDate
        }



        //"dd-MMM-yyyy"


        data.ageDay = ageDay
        data.ageWeek = ageWeek
        data.amountFemale = amountFemale
        data.amountMale = amountMale
        data.breed = ""
        data.cardName = ""
        data.status = "ACTIVE"
        data.lastUpdate = formattedDate
        data.dateDay = add_program_day_wheel.currentDay.toString()
        //data.dateMonth = add_program_month_wheel.currentMonth.toString()
        data.dateMonth = monthS
        data.dateYear = yearS
        data.systemFarm = systemTab
        data.userObjective = add_program_objective_radio.selectedTab.toString()

        //Log.e("month",monthS)

        FirebaseProgram().update(data)
        finish()
    }

    private fun updateDay(month :Int,year :Int){
        //Log.e("YEAR",month.toString() +"/"+year.toString())
        dayWheel.setYearAndMonth(year,month)
    }

}
