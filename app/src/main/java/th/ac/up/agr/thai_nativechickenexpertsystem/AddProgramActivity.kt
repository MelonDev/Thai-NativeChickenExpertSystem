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




class AddProgramActivity : AppCompatActivity(),WheelPicker.OnItemSelectedListener {

    private lateinit var yearWheel :WheelYearPicker
    private lateinit var monthWheel :WheelMonthPicker
    private lateinit var dayWheel :WheelDayPicker

    private var amountMale :String = "0"
    private var amountFemale :String = "0"
    private var ageDay :String = "0"
    private var ageWeek :String = "0"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_program)

        yearWheel = add_program_year_wheel
        monthWheel = add_program_month_wheel
        dayWheel = add_program_day_wheel

        yearWheel.yearEnd = 2100
        yearWheel.yearStart = 1900

        resetDate.setOnClickListener {
            updateDay(monthWheel.selectedMonth,yearWheel.selectedYear)
            monthWheel.selectedMonth = monthWheel.selectedMonth
            yearWheel.selectedYear = yearWheel.selectedYear
            dayWheel.selectedDay = dayWheel.selectedDay

        }

        add_program_save_btn.setOnClickListener {
            saveData()
        }

        add_program_system_tab.apply {
            addTab(this.newTab().setText("กรงตับ"))
            addTab(this.newTab().setText("ขังคอก"))
            addTab(this.newTab().setText("ปล่อยอิสระ"))
            addTab(this.newTab().setText("อินทรีย์"))
        }

        yearWheel.setOnItemSelectedListener { picker, data, position ->
            updateDay(monthWheel.currentMonth,yearWheel.currentYear)
        }

        monthWheel.setOnItemSelectedListener { picker, data, position ->
            updateDay(monthWheel.currentMonth,yearWheel.currentYear)
        }

        var a = ArrayList<Int>()
        var i = 0

        while (a.size <= 999){
            a.add(i)
            i += 1
        }

        add_program_back_btn.setOnClickListener { finish() }

        add_program_old_day_wheel.data = a
        add_program_old_week_wheel.data = a

        add_program_count_male_wheel.data = a
        add_program_count_female_wheel.data = a

        resetCount.setOnClickListener {
            add_program_count_male_wheel.selectedItemPosition = 0
            add_program_count_female_wheel.selectedItemPosition = 0
        }

        add_program_old_day_wheel.setOnItemSelectedListener(this)
        add_program_old_week_wheel.setOnItemSelectedListener(this)
        add_program_count_male_wheel.setOnItemSelectedListener(this)
        add_program_count_female_wheel.setOnItemSelectedListener(this)

    }


    override fun onItemSelected(picker: WheelPicker, data: Any, position: Int) {
        when (picker.id) {
            R.id.add_program_old_day_wheel -> ageDay = data.toString()
            R.id.add_program_old_week_wheel -> ageWeek = data.toString()
            R.id.add_program_count_male_wheel -> amountMale = data.toString()
            R.id.add_program_count_female_wheel -> amountFemale = data.toString()
        }
    }

    private fun saveData(){

        val a = Calendar.getInstance().time
        val df = SimpleDateFormat("yyyy-MM-dd-kk-mm-ss")
        val formattedDate = df.format(a)

        //"dd-MMM-yyyy"

        val data = DataCard()
        data.ageDay = ageDay
        data.ageWeek = ageWeek
        data.amountFemale = amountFemale
        data.amountMale = amountMale
        data.breed = ""
        data.cardID = formattedDate
        data.cardName = ""
        data.status = "ACTIVE"
        data.lastUpdate = formattedDate
        data.dateDay = add_program_day_wheel.currentDay.toString()
        data.dateMonth = add_program_month_wheel.currentMonth.toString()
        data.dateYear = add_program_year_wheel.currentYear.toString()
        data.systemFarm = add_program_system_tab.selectedTabPosition.toString()
        data.userObjective = add_program_objective_radio.selectedTab.toString()

        FirebaseProgram().update(data)
        finish()
    }

    private fun updateDay(month :Int,year :Int){
        Log.e("YEAR",month.toString() +"/"+year.toString())
        dayWheel.setYearAndMonth(year,month)
    }

}
