package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.mylhyl.circledialog.CircleDialog
import com.mylhyl.circledialog.callback.ConfigButton
import com.mylhyl.circledialog.callback.ConfigDialog
import com.mylhyl.circledialog.params.ButtonParams
import com.mylhyl.circledialog.params.DialogParams
import kotlinx.android.synthetic.main.fragment_tab_food.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.ContainerActivity

import th.ac.up.agr.thai_nativechickenexpertsystem.R

class TabFoodFragment : Fragment() {

    private lateinit var v: View
    private var type: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_food, container, false)
        v = view

        view.tab_food_detall.visibility = View.VISIBLE
        showDetail(-1)

        view.tab_food_edittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //val text = s.toString()
                onTextChange(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.e("", "")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.e("", "")
            }
        })


        view.tab_food_change_type.setOnClickListener {
            showDialog(arrayOf("วัน", "สัปดาห์"))
        }

        view.tab_food_detall_more.setOnClickListener {
            val intent = Intent(context, ContainerActivity::class.java)
            //intent.putExtra("TITLE",slot)
            intent.putExtra("ID","FOOD")
            intent.putExtra("NAME","โภชนาการที่ต้องการ")
            context!!.startActivity(intent)
        }


        return view
    }

    private fun dayToWeek(day: Int): Int {
        return day / 7
    }

    private fun dayToWeekWithPlusOne(day: Int): Int {
        return if (day == 0) {
            1
        } else {
            ((day - 1) / 7) + 1
        }

    }

    fun showDialog(arr: Array<String>) {
        CircleDialog.Builder(this.activity!!
        )
                .configDialog { params -> params.animStyle = R.style.dialogWindowAnim }

                .setItems(arr) { parent, view, position, id ->
                    if (type != position) {
                        v.tab_food_edittext.setText("")
                        onTextChange("")
                    }
                    this.type = position
                    v.tab_food_type.text = arr[position]

                }
                .setNegative("ยกเลิก", null)
                .configNegative { params ->
                    params.textSize = 50
                    params.textColor = ContextCompat.getColor(this@TabFoodFragment.context!!, R.color.colorText)
                }
                .show()
    }

    private fun foodMap(): HashMap<Int, Int> {
        val map = HashMap<Int, Int>()
        map.addMap(1, 7)
        map.addMap(2, 11)
        map.addMap(3, 21)
        map.addMap(4, 30)
        map.addMap(5, 32)
        map.addMap(6, 33)
        map.addMap(7, 38)
        map.addMap(8, 55)
        map.addMap(9, 50)
        map.addMap(10, 55)
        map.addMap(11, 57)
        map.addMap(12, 64)
        map.addMap(13, 66)
        map.addMap(14, 69)
        map.addMap(15, 73)
        map.addMap(16, 80)
        map.addMap(17, 68)
        map.addMap(18, 70)
        map.addMap(19, 70)
        map.addMap(20, 70)
        map.addMap(21, 70)
        map.addMap(22, 70)
        map.addMap(23, 80)
        map.addMap(24, 80)
        map.addMap(25, 80)
        map.addMap(26, 80)

        return map
    }


    private fun HashMap<Int, Int>.addMap(week: Int, value: Int) {
        this[week - 1] = value
    }

    private fun HashMap<Int, Int>.getMap(week: Int): Int {
        return this[week - 1]!!
    }

    private fun onTextChange(text: String) {
        if (text.isNotEmpty()) {

            if (type == 0) {

                val week = dayToWeekWithPlusOne(text.toInt())
                showDetail(week)
                if (week > 26) {
                    if (week in 13..27) {
                        v.tab_food_textOutput.text = "80"
                    } else {
                        if (week >= 200) {
                            v.tab_food_textOutput.text = "อายุเกิน"
                        } else {
                            v.tab_food_textOutput.text = "70"
                        }
                    }
                } else {
                    v.tab_food_textOutput.text = foodMap().getMap(week).toString()
                }
            } else {
                val week = text.toInt()
                showDetail(week)

                if (week == 0) {
                    v.tab_food_textOutput.text = foodMap()[0].toString()
                } else if (week > 26) {
                    if (week in 13..27) {
                        v.tab_food_textOutput.text = "80"
                    } else {
                        if (week >= 200) {
                            v.tab_food_textOutput.text = "อายุเกิน"
                        } else {
                            v.tab_food_textOutput.text = "70"
                        }
                    }
                } else {
                    v.tab_food_textOutput.text = foodMap().getMap(week).toString()
                }
            }
            v.tab_food_detall.visibility = View.VISIBLE
        } else {
            v.tab_food_textOutput.text = "0"
            //v.tab_food_detall.visibility = View.GONE
            showDetail(-1)
        }
    }

    private fun showDetail(week: Int) {
        if (week < 0) {
            v.tab_food_detall_a.text = "0%"
            v.tab_food_detall_b.text = "0 kcal/kg"
            v.tab_food_detall_c.text = "0%"
            v.tab_food_detall_d.text = "0%"
            v.tab_food_detall_e.text = "0%"
        } else {
            if (week in 0..6) {
                v.tab_food_detall_a.text = "18%"
                v.tab_food_detall_b.text = "2,900 kcal/kg"
                v.tab_food_detall_c.text = "0.8%"
                v.tab_food_detall_d.text = "0.4%"
                v.tab_food_detall_e.text = "0.5%"
            } else if (week in 7..16) {
                v.tab_food_detall_a.text = "14.4%"
                v.tab_food_detall_b.text = "2,900-3,000 kcal/kg"
                v.tab_food_detall_c.text = "0.85%"
                v.tab_food_detall_d.text = "0.53%"
                v.tab_food_detall_e.text = "0.5%"
            } else if (week in 17..26) {
                v.tab_food_detall_a.text = "12%"
                v.tab_food_detall_b.text = "2,900 kcal/kg"
                v.tab_food_detall_c.text = "0.9%"
                v.tab_food_detall_d.text = "0.45%"
                v.tab_food_detall_e.text = "0.55%"
            } else {
                v.tab_food_detall_a.text = "15-16%"
                v.tab_food_detall_b.text = "2,900 kcal/kg"
                v.tab_food_detall_c.text = "แม่ไก่ให้ไข่ 3.75%\nไก่พ่อพันธุ์ 0.9%"

                v.tab_food_detall_d.text = "แม่ไก่ให้ไข่ 0.35%\nไก่พ่อพันธุ์ 0.45%"
                v.tab_food_detall_e.text = "-"
            }
        }

    }

    inner class Detail {
        var protein: Double = 0.0
        var energy: Int = 0
        var calcium: Double = 0.0
        var phosphorus: Double = 0.0
        var salt: Double = 0.0

        fun setDetail(protein: Double, energy: Int, calcium: Double, phosphorus: Double, salt: Double) {
            this.protein = protein
            this.energy = energy
            this.calcium = calcium
            this.phosphorus = phosphorus
            this.salt = salt
        }
    }


}
