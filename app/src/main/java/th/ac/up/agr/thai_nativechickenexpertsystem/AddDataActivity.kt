package th.ac.up.agr.thai_nativechickenexpertsystem

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.WindowManager

import kotlinx.android.synthetic.main.activity_add_data.*
import android.widget.ArrayAdapter
import android.widget.Toast
import th.ac.up.agr.thai_nativechickenexpertsystem.Firebase.FirebaseChicken
import th.ac.up.agr.thai_nativechickenexpertsystem.Firebase.FirebaseSpinner


class AddDataActivity : AppCompatActivity() {

    val data = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        add_back_btn.setOnClickListener { finish() }

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)




    }

    override fun onResume() {
        super.onResume()
        data.clear()
        data.add("พันธุ์ไก่")

        //val view = LayoutInflater.from(this.applicationContext).inflate(R.layout.detail_card_view,parent,false)

        val view = window.decorView

        FirebaseSpinner(this,view).toSpinner(data,add_spinner_a,add_spinner_b,add_spinner_c)

    }
}
