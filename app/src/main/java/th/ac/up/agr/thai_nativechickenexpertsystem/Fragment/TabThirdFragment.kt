package th.ac.up.agr.thai_nativechickenexpertsystem.Fragment


import android.content.ContentValues.TAG
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tab_third.view.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Adapter.ProgramAdapter

import th.ac.up.agr.thai_nativechickenexpertsystem.R
import th.ac.up.agr.thai_nativechickenexpertsystem.Tools.QuickRecyclerView
import th.ac.up.agr.thai_nativechickenexpertsystem.ViewHolder.ProgramViewHolder
import kotlinx.android.synthetic.main.fragment_tab_third.*
import android.os.AsyncTask
import android.util.Log
import kotlin.math.max
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager


/**
 * A simple [Fragment] subclass.
 */
class TabThirdFragment : Fragment() {

    private lateinit var myContext: FragmentActivity
    private var currentPage: Int = 0

    private var tabThirdOne: TabThridOne = TabThridOne()

    private  var myview : View? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_third, container, false)
        myview = view

        myContext = activity as FragmentActivity

        val tab = view.viewpager
        setupViewPager(tab)
        view.tabs.setupWithViewPager(tab)

       val pageListener = PageListener()
        view.viewpager.setOnPageChangeListener(pageListener)

        tabThirdOne.myParentView = this
        tabThirdOne.fab = view.fab_A

        return view
    }

    private fun setupViewPager(viewPager: ViewPager) {
        //val adapter = ViewPagerAdapter(myContext.supportFragmentManager)
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(tabThirdOne, "หน้าหลัก")
        adapter.addFragment(TabThridTwo(), "แจ้งเตือน")
        adapter.addFragment(TabThridThree(), "ประวัติ")
        viewPager.adapter = adapter




    }

    private inner class PageListener : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            //Log.e(TAG, "page selected $position")
            if (position == 0){
                tabThirdOne.fab.show()
            } else {
                tabThirdOne.fab.hide()
            }
            currentPage = position
        }
    }


    private inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
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
            //Log.e("Test", title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }



}
