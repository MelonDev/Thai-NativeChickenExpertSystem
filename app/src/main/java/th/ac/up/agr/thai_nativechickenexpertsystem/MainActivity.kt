package th.ac.up.agr.thai_nativechickenexpertsystem

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import com.jaredrummler.android.device.DeviceName

import kotlinx.android.synthetic.main.activity_main.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Fragment.*
import android.os.Build
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView






@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var currentTab : Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentTab = TabFirstFragment()
        loadCurrentTab()

        /*

        bottomBar.setOnTabSelectListener { tabId ->
//            when (tabId) {
//
//                R.id.nav_tab_1 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFirstFragment()).commit()
//                R.id.nav_tab_2 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabSecondFragment()).commit()
//                R.id.nav_tab_3 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabThirdFragment()).commit()
//                R.id.nav_tab_4 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFourthFragment()).commit()
//                else -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFifthFragment()).commit()
//            }


//Log.e("TEST",tabId.toString())

            Log.e("TE1",bottomBar.currentTabPosition.toString())



            when (bottomBar.currentTabPosition) {
                0 -> currentTab = TabFirstFragment()
                1 -> currentTab = TabSecondFragment()
                2 -> currentTab = TabThirdFragment()
                3 -> currentTab = TabFourthFragment()

                else -> currentTab = TabFifthFragment()
            }


            supportFragmentManager.beginTransaction().replace(R.id.main_frame,currentTab).commit()


        }

*/

        //Log.e("Model",DeviceName.getDeviceName().toString())
        //Redmi 5 Plus
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (!DeviceName.getDeviceName()!!.contentEquals("Redmi 5 Plus")) {
                val w = window
                //w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                w.navigationBarColor = resources.getColor(R.color.colorBNV)
            }
        }
        */
        val w = window

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            w.navigationBarColor = resources.getColor(R.color.colorBNVT)
        }
        else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            w.navigationBarColor = resources.getColor(R.color.colorBNV)
        }


        testBottom.disableShiftMode()
        testBottom.setOnNavigationItemSelectedListener { item ->



            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.tab1 -> {
                    currentTab = TabFirstFragment()
loadCurrentTab()
                    //supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFirstFragment()).commit()
                    true
                }

                R.id.tab2 -> {
                    currentTab = TabThridPlusFragment()
                    loadCurrentTab()

                    //supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabSecondFragment()).commit()
                    true
                }
                R.id.tab3 -> {
                    currentTab = TabFoodFragment()
                    loadCurrentTab()

                    //supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFourthFragment()).commit()
                    true
                }
                R.id.tab4 -> {
                    currentTab = TabFourthFragment()
                    loadCurrentTab()

                    //supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFourthFragment()).commit()
                    true
                }
                R.id.tab5 -> {
                    currentTab = TabFifthFragment()
                    loadCurrentTab()

                    //supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFifthFragment()).commit()
                    true
                }
                else -> {
                    false
                }
            }



        }






/*
        testBottom.setOnNavigationItemSelectedListener { object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when (item.itemId) {
                    R.id.tab1 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFirstFragment()).commit()
                    R.id.tab2 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabSecondFragment()).commit()
                    R.id.tab3 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabThirdFragment()).commit()
                    R.id.tab4 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFourthFragment()).commit()
                    else -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, TabFifthFragment()).commit()
                }

                return false
            }
        }
*/
        //bottomNavigationBar = findViewById<BottomNavigationBar>(R.id.bottom_navigation_bar)

    }

    private fun loadCurrentTab(){
        supportFragmentManager.beginTransaction().replace(R.id.main_frame, currentTab).commit()
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TabFirstFragment(), "ONE")
        adapter.addFragment(TabSecondFragment(), "TWO")
        adapter.addFragment(TabThirdFragment(), "THREE")
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }


    @SuppressLint("RestrictedApi")
    fun BottomNavigationView.disableShiftMode() {
        val menuView = getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView::class.java.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView
                //item.setShiftingMode(false)
                item.setShifting(false)
                // set once again checked value, so view will be updated
                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {
            Log.e(ContentValues.TAG, "Unable to get shift mode field", e)
        } catch (e: IllegalStateException) {
            Log.e(ContentValues.TAG, "Unable to change value of shift mode", e)
        } catch (e: Exception){
            print(e.localizedMessage)
        }
    }

}
