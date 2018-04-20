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


class AddProgramActivity : AppCompatActivity() {

    private lateinit var yearWheel :WheelYearPicker
    private lateinit var monthWheel :WheelMonthPicker
    private lateinit var dayWheel :WheelDayPicker

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

    }

    private fun updateDay(month :Int,year :Int){
        Log.e("YEAR",month.toString() +"/"+year.toString())
        dayWheel.setYearAndMonth(year,month)
    }

}
