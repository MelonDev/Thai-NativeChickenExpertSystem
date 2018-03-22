package th.ac.up.agr.thai_nativechickenexpertsystem

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.jaredrummler.android.device.DeviceName

import kotlinx.android.synthetic.main.activity_main.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Fragment.*
import android.view.WindowManager
import android.os.Build


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomBar.setOnTabSelectListener { tabId ->
            when (tabId) {
                R.id.nav_tab_1 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFirstFragment()).commit()
                R.id.nav_tab_2 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabSecondFragment()).commit()
                R.id.nav_tab_3 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabThirdFragment()).commit()
                R.id.nav_tab_4 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFourthFragment()).commit()
                else -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFifthFragment()).commit()
            }
        }

        //Log.e("Model",DeviceName.getDeviceName().toString())
        //Redmi 5 Plus
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (!DeviceName.getDeviceName().contentEquals("Redmi 5 Plus")) {
                val w = window
                //w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                w.navigationBarColor = resources.getColor(R.color.colorBNV)
            }
        }

        //bottomNavigationBar = findViewById<BottomNavigationBar>(R.id.bottom_navigation_bar)

    }

}
