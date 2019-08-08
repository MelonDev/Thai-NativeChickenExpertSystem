package th.ac.up.agr.thai_nativechickenexpertsystem

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_container.*
import th.ac.up.agr.thai_nativechickenexpertsystem.Fragment.*

class ContainerActivity : AppCompatActivity() {

    lateinit var ID: String
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        container_back_btn.setOnClickListener {
            finish()
        }

        val bundle = intent.extras
        ID = bundle.getString("ID")
        //val title = bundle.getString("TITLE")
        name = bundle.getString("NAME")
        //Snackbar.make(this.findViewById(android.R.id.content),name,Snackbar.LENGTH_SHORT)
        //Log.e("NAME",name)

        var name_c = name.substring(3)

        container_title_name.text = name

        val w = window

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            w.navigationBarColor = resources.getColor(R.color.colorBNVT)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            w.navigationBarColor = resources.getColor(R.color.colorBNV)
        }

        Log.e("ID", ID.toString())

        when (ID) {
            "DISEASE" -> {
                //val DISEASE_CAUSE = bundle.getString("DISEASE_CAUSE")
                //val DISEASE_PREVENT = bundle.getString("DISEASE_PREVENT")
                //val DISEASE_DETAIL = bundle.getString("DISEASE_DETAIL")
                //val DISEASE_SYMPTOM = bundle.getString("DISEASE_SYMPTOM")

                //val fragment = DiseaseDetail.newInstance(title,DISEASE_CAUSE,DISEASE_DETAIL,DISEASE_PREVENT,DISEASE_SYMPTOM)
                //Log.e("NAME_C",name_c)

                val fragment = DiseaseDetail.newInstance(name_c)


                setFragment(fragment)
            }
            "MENU" -> {
                //val fragment = vaccineFragment.newInstance(name_c)

                when (bundle.getInt("POSITION")) {
                    0 -> {
                        val fragment = FarmManagerFragment.newInstance(0)


                        setFragment(fragment)
                    }
                    1 -> {

                    }
                    5 -> {
                        val fragment = VaccineFragment()


                        setFragment(fragment)
                    }
                    2 -> {
                        val fragment = TabSecondFragment()


                        setFragment(fragment)
                    }
                    3 -> {

                    }
                    4 -> {

                        val fragment = FarmManagerFragment.newInstance(1)


                        setFragment(fragment)

                    }
                }


            }
            "FOOD" -> {
                val fragment = FoodFragment()


                setFragment(fragment)
            }
        }

        //setFragment(TabFirstFragment())


    }

    private fun setFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().replace(R.id.container_frame, fragment).commit()

}
