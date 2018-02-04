package th.ac.up.agr.thai_nativechickenexpertsystem

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Fragment.TabFirstFragment
import th.ac.up.agr.thai_nativechickenexpertsystem.Fragment.TabFourthFragment
import th.ac.up.agr.thai_nativechickenexpertsystem.Fragment.TabSecondFragment
import th.ac.up.agr.thai_nativechickenexpertsystem.Fragment.TabThirdFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomBar.setOnTabSelectListener { tabId ->
            when (tabId) {
                R.id.nav_tab_1 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFirstFragment()).commit()
                R.id.nav_tab_2 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabSecondFragment()).commit()
                R.id.nav_tab_3 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabThirdFragment()).commit()
                else -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFourthFragment()).commit()
            }
        }

        //bottomNavigationBar = findViewById<BottomNavigationBar>(R.id.bottom_navigation_bar)

    }

}
